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

public class NumberOfItemsInGridTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long EXPLICITLY_WAIT_SECONDS = 10L;
    private final int ITEM_SCROLLING_STEP = 3;
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
     * Test verifies that displayed number of found items equals number of items in grid
     */
    @Test
    public void verifyNumberOfEcoNewsItemsInGrid() {
        int actualNumberOfGridItems;
        int expectedNumberOfGridItems;

        driver.get(BASE_URL);
        driver.findElement(By.cssSelector(".navigation-menu-left a[href *= '/news']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".btn-tiles-active")).isDisplayed());

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        wait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.xpath("//app-remaining-count/p[contains(text(),' 0 ')]")));

        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);

        expectedNumberOfGridItems = Integer.parseInt(driver
                .findElement(By.cssSelector("app-remaining-count p"))
                .getText()
                .split(" ")[0]);

        WebElement BottomOfItemToScroll;
        int itemIndex = 0;

        while (itemIndex < expectedNumberOfGridItems) {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

            BottomOfItemToScroll = wait.until(ExpectedConditions
                    .presenceOfNestedElementLocatedBy(
                            By.cssSelector(".list li:nth-child(" + (itemIndex + 1) + ")"),
                            By.cssSelector(".user-data-added-news")));

            driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", BottomOfItemToScroll);

            itemIndex += ITEM_SCROLLING_STEP;
        }

        actualNumberOfGridItems = driver.findElements(By.cssSelector(".list li")).size();

        Assert.assertEquals(actualNumberOfGridItems, expectedNumberOfGridItems);
    }
}
