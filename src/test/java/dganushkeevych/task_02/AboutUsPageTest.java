package dganushkeevych.task_02;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AboutUsPageTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/about";
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
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            driver.manage().deleteAllCookies();
        }
    }


    /**
     * Test design technique: Smoke Testing
     * General test to check current "About Us" Page
     */
    @Test
    public void verifyPageMainText() throws Exception {
        driver.get(BASE_URL);
        boolean isTextAboutUsDisplayed = driver.findElement(By.cssSelector(".container-about"))
                .getText().contains("About Us");
        Assert.assertTrue(isTextAboutUsDisplayed);
    }


    /**
     * Test design technique: Smoke Testing
     * Checking buttons that lead to "Sign In" window - 2 tests
     *
     * @param path - path to button
     */
    public void verifyThatButtonOpensSignInWindow(String path) throws Exception {
        driver.get(BASE_URL);
        boolean isForgotPasswordButtonDisplayed = true;
        driver.findElement(By.cssSelector(path)).click();
        if (driver.findElements(By.cssSelector(".forgot-password")).size() == 0) {
            isForgotPasswordButtonDisplayed = false;
        }
        driver.findElement(By.cssSelector("img[alt='close button']")).click();
        Assert.assertTrue(isForgotPasswordButtonDisplayed);
    }

    @Test
    public void verifyOpenSignInFromContainerAbout() throws Exception {
        verifyThatButtonOpensSignInWindow(".container-about .full-text-block > button");
    }

    @Test
    public void verifyOpenSignInFromContainerVision() throws Exception {
        verifyThatButtonOpensSignInWindow(".container-vision .full-text-block > button");
    }


    /**
     * Test design technique: Smoke Testing
     * Checking buttons that lead to Main page (Welcome Page) - 4 tests
     *
     * @param path - path to button
     */
    public void verifyThatButtonOpensMainPage(String path) throws Exception {
        driver.get(BASE_URL);
        boolean isButtonOnWelcomePageDisplayed = true;
        driver.findElement(By.id(path)).click();
        if (driver.findElements(By.cssSelector("#header-left > div > button")).size() == 0) {
            isButtonOnWelcomePageDisplayed = false;
        }
        driver.findElement(By.cssSelector(".navigation-menu ul > li > a[href='#/about']")).click();
        Assert.assertTrue(isButtonOnWelcomePageDisplayed);
    }

    @Test
    public void verifyThatSecondCardOpensMainPage() throws Exception {
        verifyThatButtonOpensMainPage("second-card-link");
    }

    @Test
    public void verifyThatThirdCardOpensMainPage() throws Exception {
        verifyThatButtonOpensMainPage("third-card-link");
    }

    @Test
    public void verifyThatFourthCardOpensMainPage() throws Exception {
        verifyThatButtonOpensMainPage("fourth-card-link");
    }

    @Test
    public void verifyThatFifthCardOpensMainPage() throws Exception {
        verifyThatButtonOpensMainPage("fifth-card-link");
    }


    /**
     * Test design technique: Smoke Testing
     * Checking buttons that lead to "Places" page (Welcome Page)
     */
    @Test
    public void verifyThatButtonOpensPlacesPage() throws Exception {
        driver.get(BASE_URL);
        boolean isFilterButtonDisplayed = true;
        driver.findElement(By.cssSelector(".card-holder-odd.first-card-holder a[href='#/map']")).click();
        if (driver.findElements(By.id("filter_btn")).size() == 0) {
            isFilterButtonDisplayed = false;
        }
        driver.findElement(By.cssSelector(".navigation-menu ul > li > a[href='#/about']")).click();
        Assert.assertTrue(isFilterButtonDisplayed);
    }
}
