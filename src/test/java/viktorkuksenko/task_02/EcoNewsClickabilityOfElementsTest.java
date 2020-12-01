package viktorkuksenko.task_02;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class EcoNewsClickabilityOfElementsTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/news";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long EXPLICITLY_WAIT_SECONDS = 4L;
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
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = (new WebDriverWait(driver, EXPLICITLY_WAIT_SECONDS));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.close();
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            com.softserve.edu.Util.takePageSource(driver);
            com.softserve.edu.Util.takeScreenShot(driver);
        }
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that filter buttons are clickable on the Eco news page.
     * @param filterElement - XPath expression
     */

    private void verifyIsFilterButtonsClickable(String filterElement) {
        boolean isFilterButtonClickable;
        WebElement filterButton = driver.findElement(By.xpath(filterElement));
        filterButton.click();
        //check if classname changed on "custom-chip global-tag global-tag-clicked"
        isFilterButtonClickable = filterButton.getAttribute("class")
                .equals("custom-chip global-tag global-tag-clicked");
        Assert.assertTrue(isFilterButtonClickable);
    }

    @Test
    public void verifyIsAdsFilterButtonClickable() {
        verifyIsFilterButtonsClickable("//li[@class='custom-chip global-tag'][" +
                "contains(text(), ' Ads ')]");
    }

    @Test
    public void verifyIsEventsFilterButtonClickable() {
        verifyIsFilterButtonsClickable("//li[@class='custom-chip global-tag']" +
                "[contains(text(), ' Events ')]");
    }

    @Test
    public void verifyIsNewsFilterButtonClickable() {
        verifyIsFilterButtonsClickable("//li[@class='custom-chip global-tag']" +
                "[contains(text(), ' News ')]");
    }

    @Test
    public void verifyIsEducationFilterButtonClickable() {
        verifyIsFilterButtonsClickable("//li[@class='custom-chip global-tag']" +
                "[contains(text(), ' Education ')]");
    }

    @Test
    public void verifyIsInitiativesFilterButtonClickable() {
        verifyIsFilterButtonsClickable("//li[@class='custom-chip global-tag']" +
                "[contains(text(), ' Initiatives ')]");
    }

    @Test
    public void verifyIsLifehacksFilterButtonClickable() {
        verifyIsFilterButtonsClickable("//li[@class='custom-chip global-tag'][" +
                "contains(text(), ' Lifehacks ')]");
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that grid button is clickable on the Eco news page.
     */

    @Test
    public void verifyIsClickableGridButton() {
        WebElement listButton = driver.findElements(By.xpath("//div[@class='wrapper']//span[@class='btn-bars']")).get(0);
        listButton.click();
        WebElement gridButton = driver.findElements(By.xpath("//div[@class='wrapper']/span[@class='btn-tiles']")).get(0);
        gridButton.click();
        String actualNameOfGridButtonClass = gridButton.getAttribute("class");
        //check if class of grid button changed after click
        Assert.assertTrue("btn-tiles btn-tiles-active".equals(actualNameOfGridButtonClass));
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that list button is clickable on the Eco news page.
     */

    @Test
    public void verifyIsClickableListButton() {
        WebElement listButton = driver.findElements(By.xpath("//div[@class='wrapper']//span[@class='btn-bars']")).get(0);
        listButton.click();
        String actualNameOfListButtonClass = listButton.getAttribute("class");
        //check if class of list button changed after click
        Assert.assertTrue("btn-bars btn-bars-active".equals(actualNameOfListButtonClass));
    }
}

