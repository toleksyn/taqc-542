package viktorkuksenko.task_02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class EcoNewsClickabilityOfSearchResultsTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/news";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long EXPLICITLY_WAIT_SECONDS = 20L;
    private final Long ONE_SECOND_DELAY = 1000L;
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
        wait = (new WebDriverWait(driver, EXPLICITLY_WAIT_SECONDS));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.close();
        driver.quit();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        unclickActiveFilter();
        if (!result.isSuccess()) {
            com.softserve.edu.Util.takePageSource(driver);
            com.softserve.edu.Util.takeScreenShot(driver);
        }
    }

    private void unclickActiveFilter() {
        driver.findElements(By.xpath("//div[@class='global-tag-close-icon']")).get(0).click();
    }

    /**
     *Test design technique: Smoke Testing
     *This test case verifies that after click on the filter buttons first result of search will be clickable.
     * @param container - XPath expression
     */
    private void verifyIsClickableResultOfSearchUsingFilterButton(String container) {
        boolean isClickableResultOfSearch = false;
        driver.get(BASE_URL);
        WebElement filterElement = driver.findElement(By.xpath(container));
        filterElement.click();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        //find gallery first search result
        WebElement firstSearchResult = driver.findElement(By
                .xpath("//li[@class='gallery-view-li-active ng-star-inserted'][1]//div[@class='list-gallery']//p"));
        //wait until the element changed
        wait.until(ExpectedConditions.stalenessOf(firstSearchResult));
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
        //click first result of search
        driver.findElement(By
                .xpath("//li[@class='gallery-view-li-active ng-star-inserted'][1]//div[@class='list-gallery']//p"))
                .click();
        if ("Back to news".equals(driver.findElement(By.xpath("//div[@class='button-text']")).getText())) {
            isClickableResultOfSearch = true;
        }
        //click "Back to news" button
        driver.findElements(By.xpath("//div[@class='button-text']")).get(0).click();
        Assert.assertTrue(isClickableResultOfSearch);
    }

    @Test
    public void verifyIsClickableResultsOfSearchUsingAdsFilterButton() {
        verifyIsClickableResultOfSearchUsingFilterButton("//li[@class='custom-chip global-tag'][" +
                "contains(text(), ' Ads ')]");
    }

    @Test
    public void verifyIsClickableResultsOfSearchUsingEventsFilterButton() {
        verifyIsClickableResultOfSearchUsingFilterButton("//li[@class='custom-chip global-tag']" +
                "[contains(text(), ' Events ')]");
    }

    @Test
    public void verifyIsClickableResultsOfSearchUsingNewsFilterButton() {
        verifyIsClickableResultOfSearchUsingFilterButton("//li[@class='custom-chip global-tag']" +
                "[contains(text(), ' News ')]");
    }

    @Test
    public void verifyIsClickableResultsOfSearchUsingEducationFilterButton() {
        verifyIsClickableResultOfSearchUsingFilterButton("//li[@class='custom-chip global-tag']" +
                "[contains(text(), ' Education ')]");
    }

    @Test
    public void verifyIsClickableResultsOfSearchUsingInitiativesFilterButton() {
        verifyIsClickableResultOfSearchUsingFilterButton("//li[@class='custom-chip global-tag']" +
                "[contains(text(), ' Initiatives ')]");
    }

    @Test
    public void verifyIsClickableResultsOfSearchUsingLifehacksFilterButton() {
        verifyIsClickableResultOfSearchUsingFilterButton("//li[@class='custom-chip global-tag'][" +
                "contains(text(), ' Lifehacks ')]");
    }
}

