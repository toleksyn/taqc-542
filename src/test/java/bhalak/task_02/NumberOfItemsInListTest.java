package bhalak.task_02;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NumberOfItemsInListTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long EXPLICITLY_WAIT_SECONDS = 10L;
    private final int ITEM_SCROLLING_STEP = 2;
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
     * Test verifies that displayed number of found items equals number of items in list
     */
    @Test
    public void verifyNumberOfEcoNewsItemsInList() {
        int actualNumberOfListItems = 0;
        int expectedNumberOfListItems = 0;

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//div[@class = 'navigation-menu-left']//a[contains(@href, '/news')]")).click();

        if (driver.findElement(By.xpath("//span[contains(@class, 'btn-tiles-active')]")).isDisplayed()) {
            driver.findElement(By.xpath("//i[@class ='fa fa-bars']")).click();
        }

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class = 'btn-bars btn-bars-active']")).isDisplayed());

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        wait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.xpath("//app-remaining-count/p[contains(text(),' 0 ')]")));

        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);

        expectedNumberOfListItems = Integer.parseInt(driver
                .findElement(By.xpath("//app-remaining-count/p"))
                .getText()
                .split(" ")[0]);
        WebElement itemToScroll;
        int itemIndex = 0;

        while (itemIndex < expectedNumberOfListItems) {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

            itemToScroll = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By
                            .xpath("//ul[contains(@class, 'list')]/li[" + (itemIndex + 1) + "]")));

            driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", itemToScroll);

            itemIndex += ITEM_SCROLLING_STEP;
        }

        actualNumberOfListItems = driver.findElements(By
                .xpath("//ul[contains(@class, 'list ng-star-inserted')]/li")).size();

        Assert.assertEquals(actualNumberOfListItems, expectedNumberOfListItems);
    }
}
