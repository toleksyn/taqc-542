package viktorkuksenko.task_02;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EcoNewsPresenceOfElementsTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/news";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long ONE_SECOND_DELAY = 1000L;
    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
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
            Util.takePageSource(driver);
            Util.takeScreenShot(driver);
        }
    }

    @Test
    public void verifyPageTitleIsPresent() {
        String title = driver.getTitle();
        Assert.assertTrue(title.length() > 0);
    }

    @Test
    public void verifyPageTitleName() {
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Eco news"));
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present header on the Eco news page.
     */

    @Test
    public void verifyEcoNewsPageHeaderIsPresent() {
        //get header by class name
        WebElement actualEcoNewsHeader = driver.findElement(By.className("main-header"));
        Assert.assertTrue(actualEcoNewsHeader.getText().length() > 0);
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present "Filter by" element on the Eco news page.
     */

    @Test
    public void verifyFilterByElementIsPresent() {
        String filterByElement = driver.findElement(By.xpath("//div[@class='wrapper']/span[. ='Filter by']")).getText();
        Assert.assertEquals(filterByElement, "Filter by");
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present filter buttons on the Eco news page.
     * @param expectedFilterElement - name of filter element
     */

    private void verifyFilterButtonsArePresent(String expectedFilterElement) {
        boolean isButtonPresent = false;
        List<WebElement> actualListOfFilterElements = driver.findElements(By
                .xpath("//li[@class='custom-chip global-tag']"));
        for (WebElement element : actualListOfFilterElements) {
            if (element.getText().contains(expectedFilterElement)) {
                isButtonPresent = true;
            }
        }
        Assert.assertTrue(isButtonPresent);
    }

    @Test
    public void verifyAdsFilterButtonIsPresent() {
        verifyFilterButtonsArePresent("Ads");
    }

    @Test
    public void verifyEventsFilterButtonIsPresent() {
        verifyFilterButtonsArePresent("Events");
    }

    @Test
    public void verifyNewsFilterButtonIsPresent() {
        verifyFilterButtonsArePresent("News");
    }

    @Test
    public void verifyEducationFilterButtonIsPresent() {
        verifyFilterButtonsArePresent("Education");
    }

    @Test
    public void verifyInitiativesFilterButtonIsPresent() {
        verifyFilterButtonsArePresent("Initiatives");
    }

    @Test
    public void verifyLifehacksFilterButtonIsPresent() {
        verifyFilterButtonsArePresent("Lifehacks");
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present "items found" paragraph on the Eco news page.
     */

    @Test
    public void verifyItemsFoundParagraphIsPresent() {
        String actualItemsFoundParagraph = driver.findElement(By
                .xpath("//div[@class='main-wrapper']//p")).getText().replaceAll("[^A-z]", " ").trim();
        Assert.assertEquals(actualItemsFoundParagraph, "items found");
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present grid button on the Eco news page.
     */

    @Test
    public void verifyGridButtonIsPresent() {
        boolean isButtonPresent = !driver.findElements(By.xpath("//i[@class='fa fa-th-large']")).isEmpty();
        Assert.assertTrue(isButtonPresent);
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present list button on the Eco news page.
     */

    @Test
    public void verifyListButtonIsPresent() {
        boolean isButtonPresent = !driver.findElements(By.xpath("//i[@class='fa fa-bars']")).isEmpty();
        Assert.assertTrue(isButtonPresent);
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present bottom menu on the Eco news page.
     * @param expectedMenuElement - name of menu element
     */

    private void verifyBottomMenuItemsArePresent(String expectedMenuElement) {
        boolean isMenuItemPresent = false;
        List<WebElement> actualListOfMenuItems = driver.findElements(By.xpath("//li/a[@class='router-links']"));
        for (WebElement menuElement : actualListOfMenuItems) {
            if (menuElement.getText().contains(expectedMenuElement)) {
                isMenuItemPresent = true;
            }
        }
        Assert.assertTrue(isMenuItemPresent);
    }

    @Test
    public void verifyEcoNewsBottomMenuItemIsPresent() {
        verifyBottomMenuItemsArePresent("Eco news");
    }

    @Test
    public void verifyTipsAndTricksBottomMenuItemIsPresent() {
        verifyBottomMenuItemsArePresent("Tips & Tricks");
    }

    @Test
    public void verifyPlacesBottomMenuItemIsPresent() {
        verifyBottomMenuItemsArePresent("Places");
    }

    @Test
    public void verifyAboutUsBottomMenuItemIsPresent() {
        verifyBottomMenuItemsArePresent("About us");
    }

    @Test
    public void verifyMyHabitsBottomMenuItemIsPresent() {
        verifyBottomMenuItemsArePresent("My habits");
    }
}
