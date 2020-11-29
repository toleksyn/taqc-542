package okharabara.task02;

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
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SignUpLocalizationUaTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/news";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long ONE_SECOND_DELAY = 1000L;
    private final String VALID_NAME = "Ivanka";
    private final String VALID_EMAIL = "1@1.1";
    private final String VALID_PASSWORD = "Qwerty1!";
    private Map<String, Map<String, String>> localization;
    private WebDriver driver;

    private void initLocalization() {
        localization = new HashMap<>();
        localization.put("Ua", uaLocalization());
        localization.put("Ru", ruLocalization());
        localization.put("En", enLocalization());
    }

    private Map<String, String> ruLocalization() {
        Map<String, String> result = new HashMap<>();
        result.put("verifyTitle", "Здравствуй!");
        result.put("verifySubtitle", "Пожалуйста, внеси свои данные, чтобы зарегистрироваться");
        result.put("verifyEmailHint", "example@email.com");
        result.put("verifyEmailValidator", "Введите адрес электронной почты");
        result.put("verifyFirstNameHint", "Введите имя");
        result.put("verifyFirstNameValidator", "Имя должно содержать 6-30 символов и может состоять из букв(a-z), цифр(0-9) и точки(.)");
        result.put("verifyPasswordHint", "Пароль");
        result.put("verifyPasswordValidator", "Пароль должен содержать хотя бы один символ латинского алфавита верхнего (A-Z) и нижнего регистра (a-z), а также число (0-9) и специальный символ (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)");
        result.put("verifyRepeatPasswordHint", "Пароль");
        result.put("verifySignUpLabels", "Электронная почта-Имя пользователя-Пароль-Подтвердить пароль");
        result.put("verifyRegistrationButtonWithValidData", "Зарегистрироваться");
        result.put("verifyOrButton", "или");
        result.put("verifySignUpFromGoogleButton", "Зарегистрироваться через Google");
        return result;
    }

    private Map<String, String> uaLocalization() {
        Map<String, String> result = new HashMap<>();
        result.put("verifyTitle", "Вітаємо!");
        result.put("verifySubtitle", "Будь ласка, внеси свої дані для реєстрації");
        result.put("verifyEmailHint", "example@email.com");
        result.put("verifyEmailValidator", "Введіть адресу електронної пошти");
        result.put("verifyFirstNameHint", "Введіть ім'я");
        result.put("verifyFirstNameValidator", "Ім'я повинно містити 6-30 символів і може складатись з літер(a-z), цифр(0-9) та крапки(.)");
        result.put("verifyPasswordHint", "Пароль");
        result.put("verifyPasswordValidator", "Пароль має містити хоча б один символ латинського алфавіту верхнього (A-Z) та нижнього регістру (a-z), число (0-9) та спеціальний символ (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)");
        result.put("verifyRepeatPasswordHint", "Пароль");
        result.put("verifySignUpLabels", "Електронна пошта-Ім'я користувача-Пароль-Повторіть пароль");
        result.put("verifyRegistrationButtonWithValidData", "Зареєструватись");
        result.put("verifyOrButton", "або");
        result.put("verifySignUpFromGoogleButton", "Зареєструватись через Google");
        return result;
    }

    private Map<String, String> enLocalization() {
        Map<String, String> result = new HashMap<>();
        result.put("verifyTitle", "Hello!");
        result.put("verifySubtitle", "Please enter your details to sign up");
        result.put("verifyEmailHint", "example@email.com");
        result.put("verifyEmailValidator", "Email is required");
        result.put("verifyFirstNameHint", "User name is required");
        result.put("verifyFirstNameValidator", "The name must contain 6-30 characters and can contain letters(a-z), numbers(0-9) and a dot(.)");
        result.put("verifyPasswordHint", "Password");
        result.put("verifyPasswordValidator", "Password has contain at least one character of Uppercase letter (A-Z), Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)");
        result.put("verifyRepeatPasswordHint", "Password");
        result.put("verifySignUpLabels", "Email-User name-Password-Confirm password");
        result.put("verifyRegistrationButtonWithValidData", "Sign Up");
        result.put("verifyOrButton", "or");
        result.put("verifySignUpFromGoogleButton", "Sign Up with Google");
        return result;
    }

    private void clickSignUp() {
        driver.findElement(By.cssSelector(".sign-up-link.ng-star-inserted")).click();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sign-up-link.ng-star-inserted")));
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
    }

    private WebElement changeLanguage(String name) {
        driver.findElement(By.cssSelector("div.switcher-wrapper>ul")).click();
        return driver.findElement(By.xpath("//ul[@class='add-shadow']//li[contains(text(),'" + name + "')]"));
    }

    private void takeScreenShot(String name) {
        String currentTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-S").format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_TC_" + name + "_screenshot.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void takePageSource(String name) {
        String currentTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-S").format(new Date());
        String pageSource = driver.getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_TC_" + name + "_source.html");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass() {
        initLocalization();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
        initLocalization();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        driver.findElement(By.className("close-modal-window")).click();
        if (!result.isSuccess()) {
            String testName = result.getName();
            System.out.println("***TC error, name = " + testName + " ERROR");

            takeScreenShot(result.getName());
            takePageSource(result.getName());
            driver.manage().deleteAllCookies(); // clear cache; delete cookie; delete session;
        }
    }

    @DataProvider
    public Object[][] dataLocalization() {
        return new Object[][]{
                {"Ua"},
                {"Ru"},
                {"En"},
        };
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyTitle(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.className("title-text")).getText();
        String expected = localization.get(localizationName).get("verifyTitle");
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifySubtitle(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.className("subtitle-text")).getText();
        String expected = localization.get(localizationName).get("verifySubtitle");
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifySignUpLabels(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        List<WebElement> labels = driver.findElements(By.className("content-label"));
        List<String> actual = new ArrayList();
        for (WebElement f : labels) {
            actual.add(f.getText());
        }
        List<String> expected = Arrays.asList(localization.get(localizationName).get("verifySignUpLabels").split("-"));
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyEmailHint(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.id("email")).getAttribute("placeholder");
        String expected = localization.get(localizationName).get("verifyEmailHint");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyEmailValidator(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.className("title-text")).click();
        String actual = driver.findElement(By.cssSelector(".error-message.ng-star-inserted div")).getText();
        String expected = localization.get(localizationName).get("verifyEmailValidator");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyFirstNameHint(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.id("firstName")).getAttribute("placeholder");
        String expected = localization.get(localizationName).get("verifyFirstNameHint");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyFirstNameValidator(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        driver.findElement(By.id("firstName")).click();
        driver.findElement(By.className("title-text")).click();
        String actual = driver.findElement(By.cssSelector(".error-message.ng-star-inserted div")).getText();
        String expected = localization.get(localizationName).get("verifyFirstNameValidator");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyPasswordHint(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.id("password")).getAttribute("placeholder");
        String expected = localization.get(localizationName).get("verifyPasswordHint");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyPasswordValidator(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.className("title-text")).click();
        String actual = driver.findElement(By.cssSelector(".error-message.ng-star-inserted div")).getText();
        String expected = localization.get(localizationName).get("verifyPasswordValidator");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyRepeatPasswordHint(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.id("repeatPassword")).getAttribute("placeholder");
        String expected = localization.get(localizationName).get("verifyRepeatPasswordHint");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyRegistrationButtonWithValidData(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(VALID_EMAIL);
        driver.findElement(By.id("firstName")).click();
        driver.findElement(By.id("firstName")).clear();
        driver.findElement(By.id("firstName")).sendKeys(VALID_NAME);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(VALID_PASSWORD);
        driver.findElement(By.id("repeatPassword")).click();
        driver.findElement(By.id("repeatPassword")).clear();
        driver.findElement(By.id("repeatPassword")).sendKeys(VALID_PASSWORD);
        String actual = driver.findElement(By.className("primary-global-button")).getText();
        String expected = localization.get(localizationName).get("verifyRegistrationButtonWithValidData");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyOrButton(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        String expected = driver.findElement(By.className("switch-sign-up")).getText();
        String actual = localization.get(localizationName).get("verifyOrButton");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifySignUpFromGoogleButton(String localizationName) {
        changeLanguage(localizationName).click();
        clickSignUp();
        String expected = driver.findElement(By.className("google-text-sign-in")).getText();
        String actual = localization.get(localizationName).get("verifySignUpFromGoogleButton");
        Assert.assertEquals(expected, actual);
    }
}
