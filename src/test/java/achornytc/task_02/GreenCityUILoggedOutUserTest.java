package achornytc.task_02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GreenCityUILoggedOutUserTest {
    private final static String TEST_SITE_URL = "https://ita-social-projects.github.io/GreenCityClient/";
    private final static String INITIAL_POINT_LINK_XPATH = "//app-header//a[@href='#/welcome']";
    private final static Long IMPLICIT_WAIT = 10L;
    private static WebDriver driver;
    private static Dimension resolution;

    @BeforeAll
    public static void BeforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(TEST_SITE_URL);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private static void verifyMenuTargetTitleCompliance_targetTitleEqualsExpected(String menuItem, String pageTitle) {
        driver.manage().window().maximize();
        driver.findElement(By.linkText(menuItem)).click();
        Assertions.assertEquals(driver.getTitle(), pageTitle);
    }

    @Test
    void verifyEcoNewsMenuItem_forTargetTitleCompliance() {
        verifyMenuTargetTitleCompliance_targetTitleEqualsExpected("Eco news", "Eco news");
    }

    @Test
    void verifyTipsAndTricksMenuItem_forTargetTitleCompliance() {
        verifyMenuTargetTitleCompliance_targetTitleEqualsExpected("Tips & Tricks", "Tips & Tricks");
    }

    @Test
    void verifyPlacesMenuItem_forTargetTitleCompliance() {
        verifyMenuTargetTitleCompliance_targetTitleEqualsExpected("Places", "Places");
    }

    @Test
    void verifyAboutUsMenuItem_forTargetTitleCompliance() {
        verifyMenuTargetTitleCompliance_targetTitleEqualsExpected("About us", "About us");
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/menuFunctionalTestWithTargetObjectsNotLoggedOn.csv")
    void verifyMenuTargetObjectCompliance_positive_targetObjectAppearsAfterClick(String menuItem, String target) {
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector(menuItem)).click();
        Assertions.assertTrue(driver.findElement(By.cssSelector(target)).isDisplayed(),
                "A target element could not be found");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menuResolutionAppearanceTestDataPositiveNotLoggedOn.csv", numLinesToSkip = 1)
    void verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(String windowWidth, String shouldBeClicked,
                                                                     String elementCSSSelector) {
        driver.navigate().to(TEST_SITE_URL);
        resolution = new Dimension(Integer.parseInt(windowWidth), driver.manage().window().getSize().height);
        driver.manage().window().setSize(resolution);
        if (shouldBeClicked != null && !shouldBeClicked.isEmpty()) {
            driver.findElement(By.cssSelector(shouldBeClicked)).click();
        }
        Assertions.assertTrue(driver.findElement(By.cssSelector(elementCSSSelector)).isDisplayed(),
                elementCSSSelector + " appearance fail");
    }

}
