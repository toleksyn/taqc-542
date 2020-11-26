package dganushkeevych.task_02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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

public class EcoNewsPageTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/news";
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
        //to unclick active filters
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.cssSelector(".main-header"))).perform();
        presentationSleep(); // For Presentation ONLY
        List<WebElement> filters = driver.findElements(By.cssSelector(".custom-chip.global-tag"));
        for (WebElement currentFilter : filters) {
            if (currentFilter.getAttribute("class").contains("global-tag-clicked")) {
                currentFilter.click();
                presentationSleep(); // For Presentation ONLY
            }
        }

        if (!result.isSuccess()) {
            String testName = result.getName();
            System.out.println("TC error, name = " + testName + " ERROR");
            driver.manage().deleteAllCookies();
        }
    }

    @DataProvider
    public Object[][] getFilterTestData() {
        return new Object[][]{
                {"EVENTS", "ADS"},
                {"ADS", "NEWS"},
                {"ADS", "EDUCATION"},
                {"ADS", "INITIATIVES"},
                {"ADS", "LIFEHACKS"},
                {"EVENTS", "NEWS"},
                {"EVENTS", "EDUCATION"},
                {"EVENTS", "INITIATIVES"},
                {"EVENTS", "LIFEHACKS"},
                {"NEWS", "EDUCATION"},
                {"NEWS", "INITIATIVES"},
                {"NEWS", "LIFEHACKS"},
                {"EDUCATION", "INITIATIVES"},
                {"EDUCATION", "LIFEHACKS"},
                {"INITIATIVES", "LIFEHACKS"},
        };
    }

    /**
     * Test design technique: Pairwise Testing
     * Pair combination of 6 filters = 15 tests
     * Steps:
     * 1) Choose Filters
     * 2) Find all News
     * 4) Check labels
     */
    @Test(dataProvider = "getFilterTestData")
    public void verifyFilterCoupleTest(String expected1, String expected2) throws Exception {
        List<WebElement> webElements = driver.findElements(By.cssSelector(".custom-chip.global-tag"));
        for (WebElement element : webElements) {
            if (element.getText().toUpperCase().contains(expected1) || element.getText().toUpperCase().contains(expected2)) {
                element.click();
                presentationSleep(); // For Presentation ONLY
            }
        }

        List<WebElement> linkProjects = null;
        Actions action = new Actions(driver);
        do { //scrolling
            action.moveToElement(driver.findElement(By.cssSelector(".links"))).perform();
            presentationSleep(2); // For Presentation ONLY
            linkProjects = driver.findElements(By.className("description"));
        } while (linkProjects.size() == 0);

        boolean result = true;
        List<WebElement> listNews = driver.findElements(By.className("list-gallery"));
        for (int i = 0; i < listNews.size(); i++) {
            List<WebElement> listLabels = listNews.get(i).findElements(By.cssSelector(".ul-eco-buttons.ng-star-inserted"));
            List<String> elementsLabels = new ArrayList<>();
            for (int j = 0; j < listLabels.size(); j++) {
                elementsLabels.add(listLabels.get(j).getText().toUpperCase());
            }
            if (!elementsLabels.contains(expected1) && !elementsLabels.contains(expected2)) {
                result = false;
            }
        }
        Assert.assertTrue(result);
    }
}