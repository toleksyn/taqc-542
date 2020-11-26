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
import org.testng.annotations.BeforeMethod;
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

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            String testName = result.getName();
            System.out.println("TC error, name = " + testName + " ERROR");
            driver.manage().deleteAllCookies();
        }
    }


    /**
     * Test design technique: Smoke Testing
     * General test to check current "About Us" Page
     */
    @Test
    public void verifyPageMainText() throws Exception {
        boolean result = driver.findElement(By.cssSelector(".container-about")).getText().contains("About Us");
        Assert.assertTrue(result);
    }


    /**
     * Test design technique: Smoke Testing
     * Checking buttons that lead to "Sign In" window - 2 tests
     *
     * @param path - path to button
     */
    public void verifyOpenSignInWindowButton(String path) throws Exception {
        boolean isFound = true;
        driver.findElement(By.cssSelector(path)).click();
        if (driver.findElements(By.cssSelector(".forgot-password")).size() == 0) {
            isFound = false;
        }
        driver.findElement(By.cssSelector("img[alt='close button']")).click();
        Assert.assertTrue(isFound);
    }

    @Test
    public void verifyOpenSignInFromContainerAbout() throws Exception {
        verifyOpenSignInWindowButton(".container-about .full-text-block > button");
    }

    @Test
    public void verifyOpenSignInFromContainerVision() throws Exception {
        verifyOpenSignInWindowButton(".container-vision .full-text-block > button");
    }


    /**
     * Test design technique: Smoke Testing
     * Checking buttons that lead to Main page (Welcome Page) - 4 tests
     *
     * @param path - path to button
     */
    public void verifyOpenMainPageButton(String path) throws Exception {
        boolean isFound = true;
        driver.findElement(By.id(path)).click();
        if (driver.findElements(By.cssSelector("#header-left > div > button")).size() == 0) {
            isFound = false;
        }
        driver.findElement(By.cssSelector(".navigation-menu ul > li > a[href='#/about']")).click();
        Assert.assertTrue(isFound);
    }

    @Test
    public void verifyOpenMainPageSecondCard() throws Exception {
        verifyOpenMainPageButton("second-card-link");
    }

    @Test
    public void verifyOpenMainPageThirdCard() throws Exception {
        verifyOpenMainPageButton("third-card-link");
    }

    @Test
    public void verifyOpenMainPageFourthCard() throws Exception {
        verifyOpenMainPageButton("fourth-card-link");
    }

    @Test
    public void verifyOpenMainPageFifthCard() throws Exception {
        verifyOpenMainPageButton("fifth-card-link");
    }


    /**
     * Test design technique: Smoke Testing
     * Checking buttons that lead to "Places" page (Welcome Page)
     */
    @Test
    public void verifyOpenPlacesButton() throws Exception {
        boolean isFound = true;
        driver.findElement(By.cssSelector(".card-holder-odd.first-card-holder a[href='#/map']")).click();
        if (driver.findElements(By.id("filter_btn")).size() == 0) {
            isFound = false;
        }
        driver.findElement(By.cssSelector(".navigation-menu ul > li > a[href='#/about']")).click();
        Assert.assertTrue(isFound);
    }
}