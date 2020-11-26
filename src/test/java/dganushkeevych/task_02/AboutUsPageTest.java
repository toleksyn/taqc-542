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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AboutUsPageTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/about";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long ONE_SECOND_DELAY = 1000L;
    private WebDriver driver;

    private void presentationSleep() {
        presentationSleep(1);
    }

    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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
        presentationSleep(); // For Presentation ONLY
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
        presentationSleep(); // For Presentation ONLY
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


    @DataProvider
    public Object[][] getDataToOpenSignInWindow() {
        return new Object[][]{
                {".container-about .full-text-block > button"},
                {".container-vision .full-text-block > button"},
        };
    }

    /**
     * Test design technique: Smoke Testing
     * Checking buttons that lead to "Sign In" window
     */
    @Test(dataProvider = "getDataToOpenSignInWindow")
    public void verifyOpenSignInWindowButton(String path) throws Exception {
        boolean result = true;
        driver.findElement(By.cssSelector(path)).click();
        presentationSleep(); // For Presentation ONLY
        if (driver.findElements(By.cssSelector(".forgot-password")).size() == 0) {
            result = false;
        }
        driver.findElement(By.cssSelector("img[alt='close button']")).click();
        presentationSleep(2); // For Presentation ONLY
        Assert.assertTrue(result);
    }

    @DataProvider
    public Object[][] getDataToOpenMainPage() {
        return new Object[][]{
                {"fifth-card-link"},
                {"fourth-card-link"},
                {"third-card-link"},
                {"second-card-link"},
        };
    }

    /**
     * Test design technique: Smoke Testing
     * Checking buttons that lead to Main page (Welcome Page)
     */
    @Test(dataProvider = "getDataToOpenMainPage")
    public void verifyOpenMainPageButton(String path) throws Exception {
        boolean result = true;
        driver.findElement(By.id(path)).click();
        presentationSleep(); // For Presentation ONLY
        if (driver.findElements(By.cssSelector("#header-left > div > button")).size() == 0) {
            result = false;
        }
        driver.findElement(By.cssSelector(".navigation-menu ul > li > a[href='#/about']")).click();
        presentationSleep(); // For Presentation ONLY
        Assert.assertTrue(result);
    }

    /**
     * Test design technique: Smoke Testing
     * Checking buttons that lead to "Places" page (Welcome Page)
     */
    @Test
    public void verifyOpenPlacesButton() throws Exception {
        boolean result = true;
        driver.findElement(By.cssSelector(".card-holder-odd.first-card-holder a[href='#/map']")).click();
        presentationSleep(); // For Presentation ONLY
        //driver.findElement(By.id("filter_btn")).isDisplayed();
        if (driver.findElements(By.id("filter_btn")).size() == 0) {
            result = false;
        }
        driver.findElement(By.cssSelector(".navigation-menu ul > li > a[href='#/about']")).click();
        presentationSleep(); // For Presentation ONLY
        Assert.assertTrue(result);
    }
}
