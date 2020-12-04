package rkozyk.task_02;

import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PlacesPageTest {
    private final String PLACES_PAGE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/map";
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private WebDriver chromeDriver;

    private void takeScreenShot(String testName) {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        File sourceFile = ((TakesScreenshot) chromeDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File("./screenshots/" + currentTime + "_TC_" + testName + "_screenshot.png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void takePageSource(String testName) {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String pageSource = chromeDriver.getPageSource();
        byte[] stringToBytes = pageSource.getBytes();
        Path path = Paths.get("./screenshots/" + currentTime + "_TC_" + testName + "_source.html");
        try {
            Files.write(path, stringToBytes, StandardOpenOption.CREATE);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @BeforeSuite()
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass()
    public void beforeClass() {
        Map<String, Object> preferencesForChromeDriver = new HashMap<>();
        preferencesForChromeDriver.put("profile.default_content_setting_values.geolocation", 2);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", preferencesForChromeDriver);
        chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        chromeDriver.quit();
    }

    @BeforeMethod()
    public void beforeMethod() {
        chromeDriver.get(PLACES_PAGE_URL);
    }

    @AfterMethod()
    public void afterMethod(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            String testName = testResult.getName();
            takeScreenShot(testName);
            takePageSource(testName);
        }
        chromeDriver.manage().deleteAllCookies();
    }

    /**
     * This test verifies search field
     *
     * @param textForSearchField
     * @param expectedResult
     * @throws Exception
     */
    public void verifySearchingPlacesInSearchField(String textForSearchField, List<String> expectedResult) throws Exception {
        List<String> actualResult = new ArrayList<>();
        chromeDriver.findElement(By.cssSelector("#control_panel > input")).click(); // Search Field
        chromeDriver.findElement(By.cssSelector("#control_panel > input")).clear(); // Search Field
        chromeDriver.findElement(By.cssSelector("#control_panel > input")).sendKeys(textForSearchField); // Search Field
        List<WebElement> foundPlaces = chromeDriver.findElements(By.cssSelector("table.table-striped td"));
        for (WebElement currentPlace : foundPlaces) {
            actualResult.add(currentPlace.getText());
        }
        List<WebElement> foundMarkersOnMap = chromeDriver.findElements(By.cssSelector("map"));
        for (WebElement currentMarker : foundMarkersOnMap) {
            actualResult.add(currentMarker.getAttribute("id"));
        }
        AssertJUnit.assertEquals(expectedResult, actualResult);
    }

    /**
     * This test verifies distance field
     *
     * @param numberForDistanceField
     * @param expectedResult
     * @throws Exception
     */
    public void verifySettingDistanceInDistanceField(String numberForDistanceField, List<String> expectedResult) throws Exception {
        List<String> actualResult = new ArrayList<>();
        chromeDriver.findElement(By.id("filter_btn")).click(); // Filter By
        chromeDriver.findElement(By.cssSelector("#filtering_layout > input")).sendKeys(numberForDistanceField); // Distance
        chromeDriver.findElement(By.id("apply_filter_btn")).click(); // Apply
        List<WebElement> foundPlaces = chromeDriver.findElements(By.cssSelector("table.table-striped td"));
        for (WebElement currentPlace : foundPlaces) {
            actualResult.add(currentPlace.getText());
        }
        List<WebElement> foundMarkersOnMap = chromeDriver.findElements(By.cssSelector("map"));
        for (WebElement currentMarker : foundMarkersOnMap) {
            actualResult.add(currentMarker.getAttribute("id"));
        }
        AssertJUnit.assertEquals(expectedResult, actualResult);
    }

    /**
     * Provides data for method verifySearchField()
     *
     * @throws Exception
     * @Test
     */
    @Test()
    public void verifySearchingPlacesInSearchFieldWithFullNameOfPlace() throws Exception {
        verifySearchingPlacesInSearchField("Malevych", Arrays.asList("Malevych", "gmimap0", "gmimap1", "gmimap2",
                "gmimap3", "gmimap4"));
    }

    /**
     * Provides data for method verifySearchField()
     *
     * @throws Exception
     * @Test
     */
    @Test()
    public void verifyThatSearchingPlacesInSearchFieldIsNotCaseSensetive() throws Exception {
        verifySearchingPlacesInSearchField("mALEVYCH", Arrays.asList("Malevych", "gmimap0", "gmimap1", "gmimap2",
                "gmimap3", "gmimap4"));
    }

    /**
     * Provides data for method verifySearchField()
     *
     * @throws Exception
     * @Test
     */
    @Test()
    public void verifySearchingPlacesInSearchFieldWithHalfOfNameOfPlace() throws Exception {
        verifySearchingPlacesInSearchField("mal", Arrays.asList("Malevych", "gmimap0", "gmimap1", "gmimap2", "gmimap3",
                "gmimap4"));
    }

    /**
     * Provides data for method verifySearchField()
     *
     * @throws Exception
     * @Test
     */
    @Test()
    public void verifySearchingPlacesInSearchFieldWithOneLetter() throws Exception {
        verifySearchingPlacesInSearchField("m", Arrays.asList("Forum", "Malevych", "gmimap0", "gmimap1", "gmimap2",
                "gmimap3", "gmimap4"));
    }

    /**
     * Provides data for method verifySearchField()
     *
     * @throws Exception
     * @Test
     */
    @Test()
    public void verifySearchingPlacesInSearchFieldWithWrongOneLetter() throws Exception {
        verifySearchingPlacesInSearchField("o", Arrays.asList("Forum", "Victoria Gardens", "gmimap0", "gmimap1", "gmimap2",
                "gmimap3", "gmimap4"));
    }

    /**
     * Provides data for method verifyDistanceField()
     *
     * @throws Exception
     * @Test
     */
    @Test()
    public void verifySettingDistanceInDistanceFieldWithNumberThatDoesNotFindAnyPlace() throws Exception {
        verifySettingDistanceInDistanceField("0,05", Arrays.asList());
    }

    /**
     * Provides data for method verifyDistanceField()
     *
     * @throws Exception
     * @Test
     */
    @Test()
    public void verifySettingDistanceInDistanceFieldWithNumberThatFindsTwoPlaces() throws Exception {
        verifySettingDistanceInDistanceField("0,5", Arrays.asList("Pravda", "Kryivka", "gmimap5", "gmimap6"));
    }

    /**
     * Provides data for method verifyDistanceField()
     *
     * @throws Exception
     * @Test
     */
    @Test()
    public void verifySettingDistanceInDistanceFieldWithNumberThatFindsAllPlaces() throws Exception {
        verifySettingDistanceInDistanceField("10", Arrays.asList("Forum", "Victoria Gardens", "Pravda", "Malevych",
                "Kryivka", "gmimap5", "gmimap6", "gmimap7", "gmimap8", "gmimap9"));
    }

    /**
     * This test verifies click on places
     *
     * @throws Exception
     * @Test
     */
    @Test()
    public void verifyClickOnPlaces() throws Exception {
        List<String> expectedResult = Arrays.asList("gmimap0", "gmimap8");
        List<String> actualResult = new ArrayList<>();
        chromeDriver.findElement(By.xpath("//td[.='Forum ']")).click(); // Forum
        List<WebElement> foundMarkersOnMap = chromeDriver.findElements(By.cssSelector("map"));
        for (WebElement currentMarker : foundMarkersOnMap) {
            actualResult.add(currentMarker.getAttribute("id"));
        }
        chromeDriver.findElement(By.xpath("//button[.='Show all']")).click(); // Show All
        chromeDriver.findElement(By.xpath("//td[.='Malevych ']")).click(); // Malevych
        foundMarkersOnMap = chromeDriver.findElements(By.cssSelector("map"));
        for (WebElement currentMarker : foundMarkersOnMap) {
            actualResult.add(currentMarker.getAttribute("id"));
        }
        AssertJUnit.assertEquals(expectedResult, actualResult);
    }

    /**
     * This test verifies dragging discount rate
     *
     * @throws Exception
     * @Test
     */
    @Test()
    public void verifyDraggingDiscountRate() throws Exception {
        List<String> expectedResult = Arrays.asList("Victoria Gardens", "gmimap5");
        List<String> actualResult = new ArrayList<>();
        chromeDriver.findElement(By.id("filter_btn")).click(); // Filter By
        WebElement discountRate = chromeDriver.findElement(By.cssSelector("span[class*='pointer-max']")); // Discount Rate
        Actions action = new Actions(chromeDriver);
        action.dragAndDropBy(discountRate, -148, 0).perform();
        chromeDriver.findElement(By.id("apply_filter_btn")).click(); // Apply
        List<WebElement> foundPlaces = chromeDriver.findElements(By.cssSelector("table.table-striped td"));
        for (WebElement currentPlace : foundPlaces) {
            actualResult.add(currentPlace.getText());
        }
        List<WebElement> foundMarkersOnMap = chromeDriver.findElements(By.cssSelector("map"));
        for (WebElement currentMarker : foundMarkersOnMap) {
            actualResult.add(currentMarker.getAttribute("id"));
        }
        AssertJUnit.assertEquals(expectedResult, actualResult);
    }

    /**
     * This test verifies is now open checkbox
     *
     * @throws Exception
     * @Test
     */
    @Test()
    public void verifyIsNowOpenCheckbox() throws Exception {
        List<String> expectedResult = Arrays.asList("Forum", "Victoria Gardens", "Pravda", "Malevych", "Kryivka",
                "gmimap5", "gmimap6", "gmimap7", "gmimap8", "gmimap9");
        List<String> actualResult = new ArrayList<>();
        chromeDriver.findElement(By.id("filter_btn")).click(); // Filter By
        chromeDriver.findElement(By.id("is_open_checkbox")).click(); // Is Now Open
        chromeDriver.findElement(By.id("apply_filter_btn")).click(); // Apply
        List<WebElement> foundPlaces = chromeDriver.findElements(By.cssSelector("table.table-striped td"));
        for (WebElement currentPlace : foundPlaces) {
            actualResult.add(currentPlace.getText());
        }
        List<WebElement> foundMarkersOnMap = chromeDriver.findElements(By.cssSelector("map"));
        for (WebElement currentMarker : foundMarkersOnMap) {
            actualResult.add(currentMarker.getAttribute("id"));
        }
        AssertJUnit.assertEquals(expectedResult, actualResult);
    }
}
