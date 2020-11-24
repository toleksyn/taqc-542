package achornytc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GreenCityMenuTestSuit {
    final static String TEST_SITE_URL = "https://ita-social-projects.github.io/GreenCityClient/";
    final static Long IMPLICIT_WAIT = 10L;
    final static String LOGIN_ENVIRONMENTAL_VARIABLE_NAME = "AUTO_TESTING_EMAIL";
    final static String PASSWD_ENVIRONMENTAL_VARIABLE_NAME = "AUTO_TESTING_PASSWORD";
    final static String SIGN_IN_LINK_CSS_SELECTOR = ".sign-in-link.tertiary-global-button a";
    final static String LOGIN_FORM_CSS_SELECTOR = "#email";
    final static String PASSWD_FORM_CSS_SELECTOR = "#password";
    final static String SUBMIT_BUTTON_CSS_SELECTOR = "app-sign-in > form > button";
    final static String TARGET_LOGIN_OBJECT_CSS_SELECTOR = "div.profile__details";
    final static String LOG_OUT_LINK_XPATH = "//*[normalize-space(text()) = 'Sign out']";
    final static String USER_AVATAR_WRAPPER_LINK_XPATH = "//*[@class='tertiary-global-button']//*[@class='arrow']/..";
    private static WebDriver driver;
    private static Dimension resolution;
    private static Actions action;

    @BeforeAll
    public static void BeforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.get(TEST_SITE_URL);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void refreshWindow() {
        driver.navigate().refresh();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menuFunctionalTestWithTargetTitlesNotLoggedOn.csv")
    @Order(1)
    void verifyMenuTargetTitleCompliance_positive_targetTitleEqualsExpected(String menuItem, String pageTitle) {
        driver.manage().window().maximize();
        driver.findElement(By.linkText(menuItem)).click();
        Assertions.assertEquals(driver.getTitle(), pageTitle);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menuFunctionalTestWithTargetObjectsNotLoggedOn.csv")
    @Order(2)
    void verifyMenuTargetObjectCompliance_positive_targetObjectAppearsAfterClick(String menuItem, String target) {
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector(menuItem)).click();
        Assertions.assertTrue(driver.findElement(By.cssSelector(target)).isDisplayed(),
                "A target element could not be found");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menuResolutionAppearanceTestDataPositiveNotLoggedOn.csv", numLinesToSkip = 1)
    @Order(3)
    void verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(String windowWidth, String shouldBeClicked,
                                                                   String elementCSSSelector) {
        resolution = new Dimension(Integer.valueOf(windowWidth), driver.manage().window().getSize().height);
        driver.manage().window().setSize(resolution);
        if (shouldBeClicked != null && !shouldBeClicked.isEmpty()) {
            driver.findElement(By.cssSelector(shouldBeClicked)).click();
        }
        Assertions.assertTrue(driver.findElement(By.cssSelector(elementCSSSelector)).isDisplayed(),
                elementCSSSelector + " appearance fail");

    }

    @Test
    @Order(4)
    void verifyLoginFunctionality_positive_optionalTest() {
        Assertions.assertTrue(logInToSite(), "Login failed");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menuFunctionalTestWithTargetObjectsLoggedOn.csv")
    @Order(5)
    void verifyMenuTargetObjectComplianceLoggedOn_positive_targetObjectAppearsAfterClick(String menuItem, String target) {
        driver.manage().window().maximize();
        driver.findElement(By.xpath(menuItem)).click();
        Assertions.assertTrue(driver.findElement(By.xpath(target)).isDisplayed(),
                "A target element could not be found");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menuResolutionAppearanceTestDataPositiveLoggedOn.csv", numLinesToSkip = 1)
    @Order(6)
    void verifyMenuResizeWindowAppearanceLoggedOn_positive_shouldBeDisplayed(String windowWidth, String shouldBeClicked,
                                                                           String elementXPath) {
        resolution = new Dimension(Integer.valueOf(windowWidth), driver.manage().window().getSize().height);
        driver.manage().window().setSize(resolution);
        if (shouldBeClicked != null && !shouldBeClicked.isEmpty()) {
            driver.findElement(By.xpath(shouldBeClicked)).click();
        }
        Assertions.assertTrue(driver.findElement(By.xpath(elementXPath)).isDisplayed(),
                elementXPath + " appearance fail");

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/anyPageSmokeTestDataPositiveLoggedOn.csv", numLinesToSkip = 1)
    @Order(7)
    void verifyAnyPageLoggedOn_positive_shouldBeDisplayed(String pageXPath, String shouldBeClicked,
                                                        String elementXPath) {
        driver.manage().window().maximize();
        driver.findElement(By.xpath(pageXPath)).click();
        if (shouldBeClicked != null && !shouldBeClicked.isEmpty()) {
            driver.findElement(By.xpath(shouldBeClicked)).click();
        }
        Assertions.assertTrue(driver.findElement(By.xpath(elementXPath)).isDisplayed(),
                elementXPath + " appearance fail");

    }

    @AfterAll
    public static void afterClass() {
        logOutOfSite();
        Actions action = new Actions(driver);
        action.pause(5000);
        driver.close();
    }

    private static boolean logInToSite() {
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector(SIGN_IN_LINK_CSS_SELECTOR)).click();

        driver.findElement(By.cssSelector(LOGIN_FORM_CSS_SELECTOR)).clear();

        driver.findElement(By.cssSelector(LOGIN_FORM_CSS_SELECTOR))
                .sendKeys(System.getenv().get(LOGIN_ENVIRONMENTAL_VARIABLE_NAME));

        driver.findElement(By.cssSelector(PASSWD_FORM_CSS_SELECTOR)).clear();

        driver.findElement(By.cssSelector(PASSWD_FORM_CSS_SELECTOR))
                .sendKeys(System.getenv().get(PASSWD_ENVIRONMENTAL_VARIABLE_NAME));

        driver.findElement(By.cssSelector(SUBMIT_BUTTON_CSS_SELECTOR)).click();

        return driver.findElement(By.cssSelector(TARGET_LOGIN_OBJECT_CSS_SELECTOR)).isDisplayed();

    }

    private static boolean logOutOfSite() {
        driver.manage().window().maximize();
        driver.navigate().refresh();
        driver.findElement(By.xpath(USER_AVATAR_WRAPPER_LINK_XPATH)).click();
        List<WebElement> signOutButtons = driver.findElements(By.xpath(LOG_OUT_LINK_XPATH));
        for (WebElement signOutButton : signOutButtons) {
            if (signOutButton.isDisplayed()) {
                signOutButton.click();
                return true;
            }
        }
        return false;
    }

}
