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

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GreenCityMenuTestJUnit5 {
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

    /**
     * Initializing method. Is running before all testing methods.
     */
    @BeforeAll
    public static void BeforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.get(TEST_SITE_URL);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    /**
     * A method for window refreshing. Is running before each test method.
     */
    @BeforeEach
    public void refreshWindow() {
        driver.navigate().refresh();
    }

    /**
     * This test case is based on Links text and Target pages names compliance analysis.
     * Testing technique : ad-hoc
     * @param menuItem - Link text of the menu item being tested.
     * @param pageTitle - A title of target page.
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/menuFunctionalTestWithTargetTitlesNotLoggedOn.csv")
    @Order(1)
    void verifyMenuTargetTitleCompliance_positive_targetTitleEqualsExpected(String menuItem, String pageTitle) {
        driver.manage().window().maximize();
        driver.findElement(By.linkText(menuItem)).click();
        Assertions.assertEquals(driver.getTitle(), pageTitle);
    }

    /**
     * Test case implements tests based on CSSSelector of links and targets compliance.
     * Testing technique : ad-hoc
     * @param menuItem - CSSSelector of the menu item being tested
     * @param target - CSSSelector of a unique element on the target page
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/menuFunctionalTestWithTargetObjectsNotLoggedOn.csv")
    @Order(2)
    void verifyMenuTargetObjectCompliance_positive_targetObjectAppearsAfterClick(String menuItem, String target) {
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector(menuItem)).click();
        Assertions.assertTrue(driver.findElement(By.cssSelector(target)).isDisplayed(),
                "A target element could not be found");
    }

    /**
     *  This test case is checking if all menu blocks change their positions correctly while resizing window
     *  Testing technique : EQUIVALENCE PARTITIONING  &&  STATE TRANSITION
     * @param windowWidth - the width of a browser window according to requirements and the testing technique
     * @param shouldBeClicked - a CSSSelector of the object (link, button) which is required to be clicked in
     *                           the test case (applicable for pup-ups)
     * @param elementCSSSelector - a CSSSelector of the target element which should be displayed in the test case
     */
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

    /**
     * An optional test case for log in function as for other test cases to be logged in is needed
     * Testing technique : ad-hoc
     * loginOnSite() method returns true if the TARGET_LOGIN_OBJECT_CSS_SELECTOR is displayed after log in
     */

    @Test
    @Order(4)
    void verifyLoginFunctionality_positive_optionalTest() {
        Assertions.assertTrue(loginOnSite(), "Login failed");
    }

    /**
     * Test case implements tests based on XPath of links and targets compliance.
     * Testing technique : ad-hoc
     * @param menuItem - XPath of the menu item being tested
     * @param target - XPath of a unique element on the target page
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/menuFunctionalTestWithTargetObjectsLoggedOn.csv")
    @Order(5)
    void verifyMenuTargetObjectComplianceLoggedOn_positive_targetObjectAppearsAfterClick(String menuItem, String target) {
        driver.manage().window().maximize();
        driver.findElement(By.xpath(menuItem)).click();
        Assertions.assertTrue(driver.findElement(By.xpath(target)).isDisplayed(),
                "A target element could not be found");
    }

    /**
     * This test case is checking if all menu blocks change their positions correctly while resizing window
     * Testing technique : EQUIVALENCE PARTITIONING  &&  STATE TRANSITION
     * @param windowWidth - the width of a browser window according to requirements and the testing technique
     * @param shouldBeClicked - a XPath of the object (link, button) which is required to be clicked in
     * 	 *                      the test case (applicable for pup-ups)
     * @param elementXPath - a XPath of the target element which should be displayed in the test case
     */
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

    /**
     * A closing method. Logs out from the site, closes connection to the WEBDriver.
     * Is running after all test methods.
     */
    @AfterAll
    public static void afterClass() {
        logOutFromSite();
        Actions action = new Actions(driver);
        action.pause(5000);
        driver.close();
    }

    //======================================================================================================

    /**
     * Not a test method. This is a service method for logging in.
     * @return - returns true if the TARGET_LOGIN_OBJECT_CSS_SELECTOR is displayed after log in.
     */
    private static boolean loginOnSite() {
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

    /**
     * Not a test method. This is a service method for logging out.
     * A for loop in this method is a crutch against log out link ambiguity
     * @return - returns true if a log out link was found and clicked on.
     */
    private static boolean logOutFromSite() {
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
