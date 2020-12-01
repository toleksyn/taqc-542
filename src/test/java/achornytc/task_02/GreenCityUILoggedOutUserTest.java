package achornytc.task_02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.assertj.core.api.JUnitJupiterSoftAssertions;
import org.assertj.core.api.SoftAssertions;
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

    @Test
    void verifyMyHabitsMenuItem_forTargetTitleCompliance() {
        try {
            verifyMenuTargetTitleCompliance_targetTitleEqualsExpected("My habits", "Sign in");
        } finally {
            driver.navigate().to(TEST_SITE_URL);
        }
    }


    private static void verifyLinkAndTargetObjectCompliance_targetObjectAppearsAfterClick(String menuItemCSSSelector,
                                                                                          String targetObjectCSSSelector) {
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector(menuItemCSSSelector)).click();
        Assertions.assertTrue(driver.findElement(By.cssSelector(targetObjectCSSSelector)).isDisplayed(),
                "A target element could not be found");
    }

    @Test
    void verifyEcoNewsMenuItem_forTargetObjectCompliance() {
        verifyLinkAndTargetObjectCompliance_targetObjectAppearsAfterClick(
                ".navigation-menu a[href='#/news']",
                "app-news-list");
    }

    @Test
    void verifyTipsAndTricksMenuItem_forTargetObjectCompliance() {
        verifyLinkAndTargetObjectCompliance_targetObjectAppearsAfterClick(
                ".navigation-menu a[href='#/tips'",
                "#swiper-wrapper");
    }

    @Test
    void verifyPlacesMenuItem_forTargetObjectCompliance() {
        verifyLinkAndTargetObjectCompliance_targetObjectAppearsAfterClick(
                ".navigation-menu a[href='#/map']",
                "div.agm-map-container-inner.sebm-google-map-container-inner");
    }

    @Test
    void verifyAboutUsMenuItem_forTargetObjectCompliance() {
        verifyLinkAndTargetObjectCompliance_targetObjectAppearsAfterClick(
                ".navigation-menu a[href='#/about']",
                "div.container-about > div.full-text-block");
    }

    @Test
    void verifyMyHabitsMenuItem_forTargetObjectCompliance() {
        try {
            verifyLinkAndTargetObjectCompliance_targetObjectAppearsAfterClick(
                    ".navigation-menu a[href='#/profile/not_signed_in']",
                    "#password");
        } finally {
            driver.navigate().to(TEST_SITE_URL);
        }
    }

    private void verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(Integer windowWidth, String shouldBeClicked,
                                                                             String elementCSSSelector) {
        driver.navigate().to(TEST_SITE_URL);
        resolution = new Dimension(windowWidth, driver.manage().window().getSize().height);
        driver.manage().window().setSize(resolution);
        if (shouldBeClicked != null && !shouldBeClicked.isEmpty()) {
            driver.findElement(By.cssSelector(shouldBeClicked)).click();
        }
        Assertions.assertTrue(driver.findElement(By.cssSelector(elementCSSSelector)).isDisplayed(),
                elementCSSSelector + " appearance fail");
    }

    @Test
    void verifyMenuTopLeftBlockAppearance_inWindowWidth900px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                900, null, "div.navigation-menu-left");
    }

    @Test
    void verifySignInLinkAppearance_inWindowWidth900px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                900, null, "li.sign-in-link");
    }

    @Test
    void verifySignUpLinkAppearance_inWindowWidth900px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                900, null, "li.sign-up-link");
    }

    @Test
    void verifyBurgerButtonAppearance_inWindowWidth850px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                850, null, "li.burger-b");
    }

    @Test
    void verifySignInLinkAppearance_inWindowWidth850px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                850, null, "li.sign-in-link");
    }

    @Test
    void verifySignUpLinkAppearance_inWindowWidth850px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                850, null, "li.sign-up-link");
    }

    @Test
    void verifyMenuUnderBurgerButtonAppearance_inWindowWidth850px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                850, "li.burger-b", "div.navigation-menu-left-col");
    }

    @Test
    void verifyBurgerButtonAppearance_inWindowWidth500px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                500, null, "li.burger-b");
    }

    @Test
    void verifyMenuUnderBurgerButtonAppearance_inWindowWidth500px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                500, "li.burger-b", "div.navigation-menu-left-col");
    }

    @Test
    void verifySignInLinkUnderBurgerButtonAppearance_inWindowWidth500px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                500, "li.burger-b", "a.sign-in-link");
    }

    @Test
    void verifySignUpLinkUnderBurgerButtonAppearance_inWindowWidth500px() {
        verifyMenuResizeWindowAppearance_positive_shouldBeDisplayed(
                500, "li.burger-b", "div.create-button");
    }

    private static List<String> getSubstringsByRegularExpression(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        List<String> resultList = new ArrayList<>();
        while (matcher.find()) {
            resultList.add(matcher.group());
        }
        return resultList;
    }

    private static List<Integer> getAbsoluteDimensionsIntValues(String regex, String text) {
        List<Integer> absolutDimensions = new ArrayList<>();
        for (String string : getSubstringsByRegularExpression(regex, text)) {
            for (String element : getSubstringsByRegularExpression(NUMBERS_FROM_ROW_REG_EX, string)) {
                absolutDimensions.add(Integer.valueOf(element));
            }
        }
        return absolutDimensions;
    }

    final static String ABSOLUTE_MARGIN_APPEAR_REGEX = "\\bmargin[-,a-z]*:( )+([0-9,\\.]+(px)*( )*)+;";
    final static String ABSOLUTE_WIDTH_APPEAR_REGEX = "[^-]width[-,a-z]*:( )+([0-9,.]+(px)*( )*)+;";
    final static String NUMBERS_FROM_ROW_REG_EX = "\\b[0-9]+";
    final static Integer MAX_ABSOLUTE_MARGIN_VALUE = 100;
    final static Integer DESKTOP_WINDOW_WIDTH = 1366;
    final static Integer MOBILE_WINDOW_WIDTH = 360;

    @Test
    void verifyAbsoluteMarginsAdmissibilityInPageSourceCode() {
        driver.navigate().to(TEST_SITE_URL);
        driver.manage().window().maximize();
        Object[] absoluteDimensionsList = getAbsoluteDimensionsIntValues
                (ABSOLUTE_MARGIN_APPEAR_REGEX, driver.getPageSource())
                .stream()
                .filter(x -> x > MAX_ABSOLUTE_MARGIN_VALUE)
                .toArray();
        Assertions.assertEquals(absoluteDimensionsList.length, 0,
                "There are absolute Margins in the page source: " + TEST_SITE_URL);
    }

    @Test
    void verifyAbsoluteElementsWidthAdmissibility_ForDesktop_InPageSourceCode() {
        driver.navigate().to(TEST_SITE_URL);
        resolution = new Dimension(DESKTOP_WINDOW_WIDTH, driver.manage().window().getSize().height);
        driver.manage().window().setSize(resolution);
        Object[] absoluteDimensionsList = getAbsoluteDimensionsIntValues
                (ABSOLUTE_WIDTH_APPEAR_REGEX, driver.getPageSource())
                .stream()
                .filter(x -> x > DESKTOP_WINDOW_WIDTH)
                .toArray();
        Assertions.assertEquals(0, absoluteDimensionsList.length,
                "There are absolute element widths grater than window width in the page source: "
                        + TEST_SITE_URL);
    }

    @Test
    void verifyAbsoluteElementsWidthAdmissibility_ForMobile_InPageSourceCode() {
        driver.navigate().to(TEST_SITE_URL);
        resolution = new Dimension(MOBILE_WINDOW_WIDTH, driver.manage().window().getSize().height);
        driver.manage().window().setSize(resolution);
        Object[] absoluteDimensionsList = getAbsoluteDimensionsIntValues
                (ABSOLUTE_WIDTH_APPEAR_REGEX, driver.getPageSource())
                .stream()
                .filter(x -> x > MOBILE_WINDOW_WIDTH)
                .toArray();
        Assertions.assertEquals(0, absoluteDimensionsList.length,
                "There are absolute element widths grater than window width in the page source: "
                        + TEST_SITE_URL);
    }

    @AfterAll
    public static void afterClass() {
        driver.close();
    }


}
