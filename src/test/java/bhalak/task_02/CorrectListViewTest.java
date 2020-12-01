package bhalak.task_02;

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

public class CorrectListViewTest {
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
    public void afterMethod(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            Util.takePageSource(driver);
            Util.takeScreenShot(driver);
        }
    }

    /**
     * Test design technique: State Transition
     * Test verifies that eco news items are displayed correct in list view
     */
    @Test
    public void verifyEcoNewsItemListView() {
        driver.get(BASE_URL);

        WebElement listItem;
        int newsContentLeftMargin;
        Point listImagePosition;
        Point newsContentPosition;

        driver.findElement(By.cssSelector(".navigation-menu-left a[href *= '/news']")).click();

        if (driver.findElement(By.xpath("//span[contains(@class, 'btn-tiles-active')]")).isDisplayed()) {
            driver.findElement(By.xpath("//i[@class ='fa fa-bars']")).click();
        }

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class = 'btn-bars btn-bars-active']")).isDisplayed());

        listItem = driver.findElement(By.cssSelector(".list li:first-child"));
        newsContentLeftMargin = Integer.parseInt(
                listItem.findElement(By.className("news-content"))
                        .getCssValue("margin-top")
                        .replaceAll("[^0-9]", ""));

        listImagePosition = listItem.findElement(By.className("list-image")).getLocation();
        newsContentPosition = listItem.findElement(By.className("news-content")).getLocation();

        Assert.assertEquals(listImagePosition.getY(), newsContentPosition.getY() - newsContentLeftMargin);
    }
}
