package okharabara.task02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SignUpLocalizationUaTest {
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/news";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final String VALID_NAME = "Ivanka";
    private final String VALID_EMAIL = "1@1.1";
    private final String VALID_PASSWORD = "Qwerty1!";
    private Map<String, Map<String, String>> localization;
    private WebDriver driver;

    private void initLocalization() {
        localization = new HashMap<>();
        localization.put("Ua", getUaLocalizationData());
        localization.put("Ru", getRuLocalizationData());
        localization.put("En", getEnLocalizationData());
    }

    private Map<String, String> getRuLocalizationData() {
        Map<String, String> ruLocalizationMap = new HashMap<>();
        ruLocalizationMap.put("verifyTitle", "Здравствуй!");
        ruLocalizationMap.put("verifySubtitle", "Пожалуйста, внеси свои данные, чтобы зарегистрироваться");
        ruLocalizationMap.put("verifyEmailHint", "example@email.com");
        ruLocalizationMap.put("verifyEmailValidator", "Введите адрес электронной почты");
        ruLocalizationMap.put("verifyFirstNameHint", "Введите имя");
        ruLocalizationMap.put("verifyFirstNameValidator", "Имя должно содержать 6-30 символов и может состоять из букв(a-z), цифр(0-9) и точки(.)");
        ruLocalizationMap.put("verifyPasswordHint", "Пароль");
        ruLocalizationMap.put("verifyPasswordValidator", "Пароль должен содержать хотя бы один символ латинского алфавита верхнего (A-Z) и нижнего регистра (a-z), а также число (0-9) и специальный символ (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)");
        ruLocalizationMap.put("verifyRepeatPasswordHint", "Пароль");
        ruLocalizationMap.put("verifySignUpLabels", "Электронная почта-Имя пользователя-Пароль-Подтвердить пароль");
        ruLocalizationMap.put("verifyRegistrationButtonWithValidData", "Зарегистрироваться");
        ruLocalizationMap.put("verifyOrButton", "или");
        ruLocalizationMap.put("verifySignUpFromGoogleButton", "Зарегистрироваться через Google");
        return ruLocalizationMap;
    }

    private Map<String, String> getUaLocalizationData() {
        Map<String, String> uaLocalizationMap = new HashMap<>();
        uaLocalizationMap.put("verifyTitle", "Вітаємо!");
        uaLocalizationMap.put("verifySubtitle", "Будь ласка, внеси свої дані для реєстрації");
        uaLocalizationMap.put("verifyEmailHint", "example@email.com");
        uaLocalizationMap.put("verifyEmailValidator", "Введіть адресу електронної пошти");
        uaLocalizationMap.put("verifyFirstNameHint", "Введіть ім'я");
        uaLocalizationMap.put("verifyFirstNameValidator", "Ім'я повинно містити 6-30 символів і може складатись з літер(a-z), цифр(0-9) та крапки(.)");
        uaLocalizationMap.put("verifyPasswordHint", "Пароль");
        uaLocalizationMap.put("verifyPasswordValidator", "Пароль має містити хоча б один символ латинського алфавіту верхнього (A-Z) та нижнього регістру (a-z), число (0-9) та спеціальний символ (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)");
        uaLocalizationMap.put("verifyRepeatPasswordHint", "Пароль");
        uaLocalizationMap.put("verifySignUpLabels", "Електронна пошта-Ім'я користувача-Пароль-Повторіть пароль");
        uaLocalizationMap.put("verifyRegistrationButtonWithValidData", "Зареєструватись");
        uaLocalizationMap.put("verifyOrButton", "або");
        uaLocalizationMap.put("verifySignUpFromGoogleButton", "Зареєструватись через Google");
        return uaLocalizationMap;
    }

    private Map<String, String> getEnLocalizationData() {
        Map<String, String> enLocalizationMap = new HashMap<>();
        enLocalizationMap.put("verifyTitle", "Hello!");
        enLocalizationMap.put("verifySubtitle", "Please enter your details to sign up");
        enLocalizationMap.put("verifyEmailHint", "example@email.com");
        enLocalizationMap.put("verifyEmailValidator", "Email is required");
        enLocalizationMap.put("verifyFirstNameHint", "User name is required");
        enLocalizationMap.put("verifyFirstNameValidator", "The name must contain 6-30 characters and can contain letters(a-z), numbers(0-9) and a dot(.)");
        enLocalizationMap.put("verifyPasswordHint", "Password");
        enLocalizationMap.put("verifyPasswordValidator", "Password has contain at least one character of Uppercase letter (A-Z), Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)");
        enLocalizationMap.put("verifyRepeatPasswordHint", "Password");
        enLocalizationMap.put("verifySignUpLabels", "Email-User name-Password-Confirm password");
        enLocalizationMap.put("verifyRegistrationButtonWithValidData", "Sign Up");
        enLocalizationMap.put("verifyOrButton", "or");
        enLocalizationMap.put("verifySignUpFromGoogleButton", "Sign Up with Google");
        return enLocalizationMap;
    }

    private void clickSignUp() {
        driver.findElement(By.cssSelector(".sign-up-link.ng-star-inserted")).click();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sign-up-link.ng-star-inserted")));
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
    }

    private WebElement getChangedLanguage(String name) {
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
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        driver.findElement(By.className("close-modal-window")).click();
        if (!result.isSuccess()) {
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
        getChangedLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.className("title-text")).getText();
        String expected = localization.get(localizationName).get("verifyTitle");
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifySubtitle(String localizationName) {
        getChangedLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.className("subtitle-text")).getText();
        String expected = localization.get(localizationName).get("verifySubtitle");
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifySignUpLabels(String localizationName) {
        getChangedLanguage(localizationName).click();
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
        getChangedLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.id("email")).getAttribute("placeholder");
        String expected = localization.get(localizationName).get("verifyEmailHint");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyEmailValidator(String localizationName) {
        getChangedLanguage(localizationName).click();
        clickSignUp();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.className("title-text")).click();
        String actual = driver.findElement(By.cssSelector(".error-message.ng-star-inserted div")).getText();
        String expected = localization.get(localizationName).get("verifyEmailValidator");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyFirstNameHint(String localizationName) {
        getChangedLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.id("firstName")).getAttribute("placeholder");
        String expected = localization.get(localizationName).get("verifyFirstNameHint");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyFirstNameValidator(String localizationName) {
        getChangedLanguage(localizationName).click();
        clickSignUp();
        driver.findElement(By.id("firstName")).click();
        driver.findElement(By.className("title-text")).click();
        String actual = driver.findElement(By.cssSelector(".error-message.ng-star-inserted div")).getText();
        String expected = localization.get(localizationName).get("verifyFirstNameValidator");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyPasswordHint(String localizationName) {
        getChangedLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.id("password")).getAttribute("placeholder");
        String expected = localization.get(localizationName).get("verifyPasswordHint");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyPasswordValidator(String localizationName) {
        getChangedLanguage(localizationName).click();
        clickSignUp();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.className("title-text")).click();
        String actual = driver.findElement(By.cssSelector(".error-message.ng-star-inserted div")).getText();
        String expected = localization.get(localizationName).get("verifyPasswordValidator");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyRepeatPasswordHint(String localizationName) {
        getChangedLanguage(localizationName).click();
        clickSignUp();
        String actual = driver.findElement(By.id("repeatPassword")).getAttribute("placeholder");
        String expected = localization.get(localizationName).get("verifyRepeatPasswordHint");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifyRegistrationButtonWithValidData(String localizationName) {
        getChangedLanguage(localizationName).click();
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
        getChangedLanguage(localizationName).click();
        clickSignUp();
        String expected = driver.findElement(By.className("switch-sign-up")).getText();
        String actual = localization.get(localizationName).get("verifyOrButton");
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataLocalization")
    public void verifySignUpFromGoogleButton(String localizationName) {
        getChangedLanguage(localizationName).click();
        clickSignUp();
        String expected = driver.findElement(By.className("google-text-sign-in")).getText();
        String actual = localization.get(localizationName).get("verifySignUpFromGoogleButton");
        Assert.assertEquals(expected, actual);
    }
}
