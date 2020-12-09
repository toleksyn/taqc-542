package bhalak.task_02;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CorrectGridViewTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
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
        driver.quit();
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult) throws IOException {
        if (!testResult.isSuccess()) {
            Util.takePageSource(driver);
            Util.takeScreenShot(driver);
        }
    }

    /**
     * Test design technique: State Transition
     * Test verifies that eco news items are displayed correct in grid view
     */
    @Test
    public void verifyEcoNewsItemGridView() {
        driver.get(BASE_URL);

        WebElement gridItem;
        int newsContentLeftMargin;
        Point listImagePosition;
        Point newsContentPosition;

        driver.findElement(By.cssSelector(".navigation-menu-left a[href *= '/news']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".btn-tiles-active")).isDisplayed());

        gridItem = driver.findElement(By.cssSelector(".list li:first-child"));
        newsContentLeftMargin = Integer.parseInt(
                gridItem.findElement(By.className("list-gallery-content"))
                        .getCssValue("margin-left")
                        .replaceAll("[^0-9]", "")
        );

        listImagePosition = gridItem.findElement(By.className("list-image")).getLocation();
        newsContentPosition = gridItem.findElement(By.className("list-gallery-content")).getLocation();

        Assert.assertEquals(listImagePosition.getX(), newsContentPosition.getX() - newsContentLeftMargin);
    }
}
