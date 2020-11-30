package achornytc.task_02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GreenCityUILoggedInUserTest {
    private final static String TEST_SITE_URL = "https://ita-social-projects.github.io/GreenCityClient/";
    private final static Long IMPLICIT_WAIT = 10L;
    private final static String LOGIN_ENVIRONMENTAL_VARIABLE_NAME = "AUTO_TESTING_EMAIL";
    private final static String PASSWD_ENVIRONMENTAL_VARIABLE_NAME = "AUTO_TESTING_PASSWORD";
    private final static String SIGN_IN_LINK_CSS_SELECTOR = ".sign-in-link.tertiary-global-button a";
    private final static String LOGIN_FORM_CSS_SELECTOR = "#email";
    private final static String PASSWD_FORM_CSS_SELECTOR = "#password";
    private final static String SUBMIT_BUTTON_CSS_SELECTOR = "app-sign-in > form > button";
    private final static String TARGET_LOGIN_OBJECT_CSS_SELECTOR = "div.profile__details";
    private final static String LOG_OUT_LINK_XPATH = "//*[normalize-space(text()) = 'Sign out']";
    private final static String USER_AVATAR_WRAPPER_LINK_XPATH = "//*[@class='tertiary-global-button']//*[@class='arrow']/..";
    private static boolean userIsLoggedIn = false;
    private static WebDriver driver;
    private static Dimension resolution;


    @BeforeAll
    public static void BeforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(TEST_SITE_URL);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Assertions.assertTrue(logInToSite(userIsLoggedIn), "Log In failed. All other tests are ignored.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menuFunctionalTestWithTargetObjectsLoggedOn.csv")
    void verifyMenuTargetObjectCompliance_positive_targetObjectAppearsAfterClick(String menuItem, String target) {
        driver.manage().window().maximize();
        driver.findElement(By.xpath(menuItem)).click();
        Assertions.assertTrue(driver.findElement(By.xpath(target)).isDisplayed(),
                "A target element could not be found");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menuResolutionAppearanceTestDataPositiveLoggedOn.csv", numLinesToSkip = 1)
    void verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(String windowWidth, String shouldBeClicked,
                                                                     String elementXPath) {
        driver.navigate().to(TEST_SITE_URL);
        resolution = new Dimension(Integer.parseInt(windowWidth), driver.manage().window().getSize().height);
        driver.manage().window().setSize(resolution);
        if (shouldBeClicked != null && !shouldBeClicked.isEmpty()) {
            driver.findElement(By.xpath(shouldBeClicked)).click();
        }
        Assertions.assertTrue(driver.findElement(By.xpath(elementXPath)).isDisplayed(),
                elementXPath + " appearance fail");

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/anyPageSmokeTestDataPositiveLoggedOn.csv", numLinesToSkip = 1)
    void verifyAnyPage_positive_shouldBeDisplayed(String pageXPath, String shouldBeClicked, String elementXPath) {
        driver.manage().window().maximize();
        driver.findElement(By.xpath(pageXPath)).click();
        if (shouldBeClicked != null && !shouldBeClicked.isEmpty()) {
            driver.navigate().refresh();
            driver.findElement(By.xpath(shouldBeClicked)).click();
        }
        Assertions.assertTrue(driver.findElement(By.xpath(elementXPath)).isDisplayed(),
                elementXPath + " appearance fail");

    }

    @AfterAll
    public static void afterClass() {
        if (userIsLoggedIn) {
            logOutOfSite();
        }
        driver.close();
    }

    public static boolean logInToSite(boolean logInFlag) {
        return logInToSite();
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
