package viktorkuksenko.task_02;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
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
    public void verifyIsPresentPageTitle() {
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
    public void verifyIsPresentEcoNewsPageHeader() {
        //get header by class name
        WebElement actualEcoNewsHeader = driver.findElement(By.className("main-header"));
        Assert.assertTrue(actualEcoNewsHeader.getText().length() > 0);
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present "Filter by" element on the Eco news page.
     */

    @Test
    public void verifyIsPresentFilterByElement() {
        String filterByElement = driver.findElement(By.xpath("//div[@class='wrapper']/span[. ='Filter by']")).getText();
        Assert.assertEquals(filterByElement, "Filter by");
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present filter buttons on the Eco news page.
     * @param expectedFilterElement - name of filter element
     */

    private void verifyIsPresentFilterButtons(String expectedFilterElement) {
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
    public void verifyIsPresentFilterButtons1() {
        verifyIsPresentFilterButtons("Ads");
    }

    @Test
    public void verifyIsPresentFilterButtons2() {
        verifyIsPresentFilterButtons("Events");
    }

    @Test
    public void verifyIsPresentFilterButtons3() {
        verifyIsPresentFilterButtons("News");
    }

    @Test
    public void verifyIsPresentFilterButtons4() {
        verifyIsPresentFilterButtons("Education");
    }

    @Test
    public void verifyIsPresentFilterButtons5() {
        verifyIsPresentFilterButtons("Initiatives");
    }

    @Test
    public void verifyIsPresentFilterButtons6() {
        verifyIsPresentFilterButtons("Lifehacks");
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present "items found" paragraph on the Eco news page.
     */

    @Test
    public void verifyIsPresentItemsFoundParagraph() {
        String actualItemsFoundParagraph = driver.findElement(By
                .xpath("//div[@class='main-wrapper']//p")).getText().replaceAll("[^A-z]", " ").trim();
        Assert.assertEquals(actualItemsFoundParagraph, "items found");
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present grid button on the Eco news page.
     */

    @Test
    public void verifyIsPresentGridButton() {
        boolean isButtonPresent = !driver.findElements(By.xpath("//i[@class='fa fa-th-large']")).isEmpty();
        Assert.assertTrue(isButtonPresent);
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present list button on the Eco news page.
     */

    @Test
    public void verifyIsPresentListButton() {
        boolean isButtonPresent = !driver.findElements(By.xpath("//i[@class='fa fa-bars']")).isEmpty();
        Assert.assertTrue(isButtonPresent);
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that present bottom menu on the Eco news page.
     *
     * @param expectedMenuElement - name of menu element
     */

    private void verifyIsPresentBottomMenuItems(String expectedMenuElement) {
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
    public void verifyIsPresentBottomMenuItems1() {
        verifyIsPresentBottomMenuItems("Eco news");
    }

    @Test
    public void verifyIsPresentBottomMenuItems2() {
        verifyIsPresentBottomMenuItems("Tips & Tricks");
    }

    @Test
    public void verifyIsPresentBottomMenuItems3() {
        verifyIsPresentBottomMenuItems("Places");
    }

    @Test
    public void verifyIsPresentBottomMenuItems4() {
        verifyIsPresentBottomMenuItems("About us");
    }

    @Test
    public void verifyIsPresentBottomMenuItems5() {
        verifyIsPresentBottomMenuItems("My habits");
    }
}
