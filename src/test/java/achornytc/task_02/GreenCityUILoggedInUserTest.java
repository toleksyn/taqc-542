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
    private static WebDriver driver;
    private static Dimension resolution;


    @BeforeAll
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(TEST_SITE_URL);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Assertions.assertTrue(loggingInToSite(), "Log In failed. All other tests are ignored.");
    }

    private void verifyMenuTargetObjectCompliance_targetObjectAppearsAfterClick(String menuItem, String target) {
        driver.manage().window().maximize();
        driver.findElement(By.xpath(menuItem)).click();
        Assertions.assertTrue(driver.findElement(By.xpath(target)).isDisplayed(),
                "A target element could not be found");
    }

    @Test
    void verifyMyHabitsMenuItem_forTargetObjectCompliance() {
        verifyMenuTargetObjectCompliance_targetObjectAppearsAfterClick(
                "//div[@class='navigation-menu']//a[normalize-space(text()) = 'My habits']",
                "//div[@class='profile__details']");
    }

    private void verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(Integer windowWidth, String shouldBeClicked,
                                                                             String elementXPath) {
        driver.navigate().to(TEST_SITE_URL);
        resolution = new Dimension(windowWidth, driver.manage().window().getSize().height);
        driver.manage().window().setSize(resolution);
        if (shouldBeClicked != null && !shouldBeClicked.isEmpty()) {
            driver.findElement(By.xpath(shouldBeClicked)).click();
        }
        Assertions.assertTrue(driver.findElement(By.xpath(elementXPath)).isDisplayed(),
                elementXPath + " appearance fail");

    }

    @Test
    void verifyUserAvatarWrapperAppearance_inWindowWidth900px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                900, null, "//*[@id='user-avatar-wrapper']");
    }

    @Test
    void verifySettingsAppearance_inWindowWidth900px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                900, "//*[@id='user-avatar-wrapper']",
                "//div[@id='user-avatar-wrapper']//a[normalize-space(text()) = 'Settings']");
    }

    @Test
    void verifySignOutAppearance_inWindowWidth900px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                900, "//*[@id='user-avatar-wrapper']",
                "//div[@id='user-avatar-wrapper']//a[normalize-space(text()) = 'Sign out']");
    }

    @Test
    void verifySettingsAppearance_inWindowWidth500px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                500, "//li[@class='burger-b']",
                "//div[@class='navigation-menu-left-col']//a[normalize-space(text()) = 'Sign out']");
    }

    @Test
    void verifySignOutAppearance_inWindowWidth500px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                500, "//li[@class='burger-b']",
                "//div[@class='navigation-menu-left-col']//a[normalize-space(text()) = 'Sign out']");
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/anyPageSmokeTestDataPositiveLoggedOn.csv", numLinesToSkip = 1)
    void verifyAnyPage_elementsShouldBeDisplayed(String pageXPath, String shouldBeClicked, String elementXPath) {
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
        loggingOutOfSite();
        driver.close();
    }

    public static boolean loggingInToSite(boolean logInFlag) {
        return loggingInToSite();
    }

    private static boolean loggingInToSite() {
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

    private static boolean loggingOutOfSite() {
        driver.manage().window().maximize();
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
