package dganushkeevych.task_02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EcoNewsPageTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/news";
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
            String testName = result.getName();
            System.out.println("TC error, name = " + testName + " ERROR");
            driver.manage().deleteAllCookies();
        }
    }


    /**
     * Test design technique: Pairwise Testing
     *
     * @param filter1 - the first filter
     * @param filter2 - the second filter
     *                Pair combination of 6 filters = 15 tests
     *                Steps:
     *                * 1) Choose Filters
     *                * 2) Find all News
     *                * 4) Check labels
     */
    public void verifyFilterCoupleTest(String filter1, String filter2) throws Exception {
        Thread.sleep(1000); //working on it - cannot load element correct
        List<WebElement> webElements = driver.findElements(By.cssSelector(".custom-chip.global-tag"));
        for (WebElement element : webElements) {
            if (element.getText().toUpperCase().contains(filter1) || element.getText().toUpperCase().contains(filter2)) {
                element.click();
                Thread.sleep(1000); //working on it - cannot load element correct
            }
        }

        /*-
        List<WebElement> linkProjects = null;
        Actions action = new Actions(driver);
        do { //scrolling
            action.moveToElement(driver.findElement(By.cssSelector(".links"))).perform();
            //presentationSleep(2); // For Presentation ONLY
            linkProjects = driver.findElements(By.className("description"));
        } while (linkProjects.size() == 0);
        */

        int countOfFoundItems = Integer.parseInt(
                driver.findElement(By.xpath("//app-remaining-count//p")).getText().replaceAll("[^0-9]", ""));
        List<WebElement> actualNews = driver.findElements(By.className("list-gallery"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        while (actualNews.size() < countOfFoundItems) {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualNews.get(actualNews.size() - 1));
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
            actualNews = driver.findElements(By.className("list-gallery"));
        }
        System.out.println("AFTER Actual news = " + actualNews.size());


        boolean isLabelsCorrect = true;
        List<WebElement> listNews = driver.findElements(By.className("list-gallery"));
        for (int i = 0; i < listNews.size(); i++) {
            List<WebElement> listLabels = listNews.get(i).findElements(By.cssSelector(".ul-eco-buttons.ng-star-inserted"));
            List<String> elementsLabels = new ArrayList<>();
            for (int k = 0; k < listLabels.size(); k++) {
                elementsLabels.add(listLabels.get(k).getText().toUpperCase());
            }
            if (!elementsLabels.contains(filter1) && !elementsLabels.contains(filter2)) {
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