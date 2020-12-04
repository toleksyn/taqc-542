package dganushkeevych.task_02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EcoNewsPageTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/news";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final int MAX_SCROLL_COUNT = 60;
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
        /* to unclick active filters */
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.cssSelector(".main-header"))).perform();
        List<WebElement> filters = driver.findElements(By.cssSelector(".custom-chip.global-tag"));
        for (WebElement currentFilter : filters) {
            if (currentFilter.getAttribute("class").contains("global-tag-clicked")) {
                currentFilter.click();
            }
        }

        if (!result.isSuccess()) {
            driver.manage().deleteAllCookies();
        }
    }


    /**
     * Test design technique: Pairwise Testing
     *
     * @param firstFilter - the first filter
     * @param secondFilter - the second filter
     *                Pair combination of 6 filters = 15 tests
     *                Steps:
     *                * 1) Choose Filters
     *                * 2) Find all News
     *                * 3) Check labels
     */
    public void verifyFilterCoupleTest(String firstFilter, String secondFilter) throws Exception {
        driver.get(BASE_URL);

        //choosing filters
        List<WebElement> webElements = driver.findElements(By.cssSelector(".custom-chip.global-tag"));
        for (WebElement element : webElements) {
            if (element.getText().toUpperCase().contains(firstFilter) || element.getText().toUpperCase().contains(secondFilter)) {
                element.click();
                driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='title-list word-wrap']")));
                driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
            }
        }

        int countOfFoundItems = Integer.parseInt(
                driver.findElement(By.xpath("//app-remaining-count//p")).getText().replaceAll("[^0-9]", ""));

        //scrolling till all news will be found
        List<WebElement> actualNews = driver.findElements(By.className("list-gallery"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        int currentScrollIndex = 0;
        while (actualNews.size() < countOfFoundItems && currentScrollIndex < MAX_SCROLL_COUNT) {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualNews.get(actualNews.size() - 1));
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
            actualNews = driver.findElements(By.className("list-gallery"));
            currentScrollIndex++;
        }

        //checking news labels
        boolean isLabelsCorrect = true;
        List<WebElement> listNews = driver.findElements(By.className("list-gallery"));
        for (int i = 0; i < listNews.size(); i++) {
            List<WebElement> listLabels = listNews.get(i).findElements(By.cssSelector(".ul-eco-buttons.ng-star-inserted"));
            List<String> elementsLabels = new ArrayList<>();
            for (int k = 0; k < listLabels.size(); k++) {
                elementsLabels.add(listLabels.get(k).getText().toUpperCase());
            }
            if (!elementsLabels.contains(firstFilter) && !elementsLabels.contains(secondFilter)) {
                isLabelsCorrect = false;
            }
        }
        Assert.assertTrue(isLabelsCorrect);
    }

    @Test
    public void verifyFilterEventsAdsTest() throws Exception {
        verifyFilterCoupleTest("EVENTS", "ADS");
    }

    @Test
    public void verifyFilterEventsLifehacksTest() throws Exception {
        verifyFilterCoupleTest("EVENTS", "LIFEHACKS");
    }

    @Test
    public void verifyFilterEventsInitiativesTest() throws Exception {
        verifyFilterCoupleTest("EVENTS", "INITIATIVES");
    }

    @Test
    public void verifyFilterEventsEducationTest() throws Exception {
        verifyFilterCoupleTest("EVENTS", "EDUCATION");
    }

    @Test
    public void verifyFilterEventsNewsTest() throws Exception {
        verifyFilterCoupleTest("EVENTS", "NEWS");
    }

    @Test
    public void verifyFilterAdsLifeHacksTest() throws Exception {
        verifyFilterCoupleTest("ADS", "LIFEHACKS");
    }

    @Test
    public void verifyFilterAdsInitiativesTest() throws Exception {
        verifyFilterCoupleTest("ADS", "INITIATIVES");
    }

    @Test
    public void verifyFilterEducationAdsTest() throws Exception {
        verifyFilterCoupleTest("ADS", "EDUCATION");
    }

    @Test
    public void verifyFilterNewsAdsTest() throws Exception {
        verifyFilterCoupleTest("ADS", "NEWS");
    }

    @Test
    public void verifyFilterNewsEducationTest() throws Exception {
        verifyFilterCoupleTest("NEWS", "EDUCATION");
    }

    @Test
    public void verifyFilterNewsInitiativesTest() throws Exception {
        verifyFilterCoupleTest("NEWS", "INITIATIVES");
    }

    @Test
    public void verifyFilterNewsLifehacksTest() throws Exception {
        verifyFilterCoupleTest("NEWS", "LIFEHACKS");
    }

    @Test
    public void verifyFilterEducationInitiativesTest() throws Exception {
        verifyFilterCoupleTest("EDUCATION", "INITIATIVES");
    }

    @Test
    public void verifyFilterEducationLifehacksTest() throws Exception {
        verifyFilterCoupleTest("EDUCATION", "LIFEHACKS");
    }

    @Test
    public void verifyFilterInitiativesLifehacksTest() throws Exception {
        verifyFilterCoupleTest("INITIATIVES", "LIFEHACKS");
    }

}