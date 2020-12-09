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

public class TipsAndTricksPresenceAndClickabilityOfElementsTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/tips";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long EXPLICITLY_WAIT_SECONDS = 10L;
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
            Util.takePageSource(driver);
            Util.takeScreenShot(driver);
        }
    }

    @Test
    public void verifyPageTitleIsPresent() {
        Assert.assertTrue(driver.getTitle().length() > 0);
    }

    @Test
    public void verifyPageTitleName() {
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Tips & Tricks"));
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that header is present on the Tips & Trick page.
     */

    @Test
    public void verifyMainHeaderIsPresent() {
        Assert.assertTrue(driver.findElements(By.xpath("//h2[@class='section-caption']"))
                .get(0).getText().length() > 0);
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that previous and next buttons are present on the Tips & Trick page.
     */

    @Test
    public void verifyPreviousAndNextButtonsArePresent() {
        boolean isPresentPreviousButton = !driver
                .findElements(By
                        .xpath("//button[@class='button-prev tips-navigation primary-global-button']")).isEmpty();
        boolean isPresentNextButton = !driver
                .findElements(By
                        .xpath("//button[@class='button-next tips-navigation primary-global-button']")).isEmpty();
        Assert.assertTrue(isPresentNextButton && isPresentPreviousButton);
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that after press on next button gallery items are scrolling on the Tips & Trick page.
     */

    @Test
    public void verifyScrollingUsingNextButton() {
        boolean isScrollingUsingNextButton = false;
        String centralElementParagraphBeforeClick = driver.findElement(By
                .xpath("//app-tips-card[@class='swiper-slide ng-star-inserted swiper-slide-active']//p"))
                .getText();
        WebElement nextButton = driver.findElement(By
                .xpath("//button[@class='button-next tips-navigation primary-global-button']"));
        nextButton.click();
        String centralElementParagraphAfterClick = driver.findElement(By
                .xpath("//app-tips-card[@class='swiper-slide ng-star-inserted swiper-slide-active']//p"))
                .getText();
        if (!centralElementParagraphBeforeClick.equals(centralElementParagraphAfterClick)) {
            isScrollingUsingNextButton = true;
        }
        Assert.assertTrue(isScrollingUsingNextButton);
    }

    /**
     * Test design technique: Smoke Testing
     * This test case verifies that after press on previous button gallery items are scrolling on the Tips & Trick page.
     */

    @Test
    public void verifyScrollingUsingPreviousButton() {
        boolean isScrollingUsingPreviousButton = false;
        WebElement previousButton = driver.findElement(By
                .xpath("//button[@class='button-prev tips-navigation primary-global-button']"));
        String centralElementParagraphBeforeClick = driver.findElement(By
                .xpath("//app-tips-card[@class='swiper-slide ng-star-inserted swiper-slide-active']//p"))
                .getText();
        previousButton.click();
        String centralElementParagraphAfterClick = driver.findElement(By
                .xpath("//app-tips-card[@class='swiper-slide swiper-slide-duplicate swiper-slide-active']//p"))
                .getText();
        if (!centralElementParagraphAfterClick.equals(centralElementParagraphBeforeClick)) {
            isScrollingUsingPreviousButton = true;
        }
        Assert.assertTrue(isScrollingUsingPreviousButton);
    }
}

