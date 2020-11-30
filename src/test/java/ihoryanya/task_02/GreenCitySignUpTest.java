package ihoryanya.task_02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class GreenCitySignUpTest {
    private WebDriver driver;

    @BeforeClass
    public void BeforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        String baseUrl = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
        driver.get(baseUrl);
        driver.findElement(By.xpath("//*[@class='sign-up-link ng-star-inserted']")).click();
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @DataProvider
    public Object[][] emailFieldInvalidData() {
        return new Object[][]{
                {"UPPERCASELETTERS"},
                {"lowercaseletters"},
                {"!#$%&'*+-/=?^_`{|}~"},
                {"\"John..Doe\""},
                {"YxXrtLmxJ59ePaRivEYR6k592XDbsEwSfvTmvwOKwOHENEA4Nok4OTxvN8TObVXguyWsoImbD5d02sfD9jC6" +
                        "ueUyEv2TyQNISSbkt8oahRoT4L0IH5BqotwBYiUiXnlZTzcNqk3BXnZ8A5WdGmnfL4kEKfPaeoagVQnXUjJi80BDQcMJMN" +
                        "1iCAyTtQ091e76Pz6ocpE27DjF6CXdcIhO8tMK593Y5dm5LmETmVZyxkJjperOBZlI20CIuVjsTrJ"},
                {"N0Al3ymO94p9eLa3yZA0CCIV94PPVF3DWrVQ8n86iL0e2wkfq4GLqepvyiVezYzyIoaLSPc8nZnWqTbrk5tg2" +
                        "IPWPbiAPw57c0vIGe9ZGjffPo6BAq1tYDCu8RGbmxvYmNHdWfwL8LSvX1m4ke2WhnJoDQXQ2hm5NAL7LZKHm6dl506FO" +
                        "GPNhrZAvjAtqx8g1SYFD7grPL010Pdk9DrtGtNE0AUO1wkTJdsKUfVRNLghNHCMStQ6HY57qDdfYruvsl4yG7QS2"},
                {"E5MzgEKaz1dymbLI2mHrEG3whQvZazwuEmKG7ub0mLrCrXLL4K1OfLPbDKSyYm67Ta95sTwN16XcQ6s2Wudb1" +
                        "Sgdw0s2LljkOMdlDB3VBVETHQ4m1iEiPJVJmXA4UtMSrVGL0h3d0eNXcDk8Zdyiz9Pn7265qIhpKDI0bWjEEupLp5TBFQ" +
                        "eNkWAayKoWYjxsFYGtiLSlkO3KNoSOhQE96qv9pXdy67QdcstixKAkiWZdsEZc1adqyZCW52vntfosQGVlJBfINeFBos"},
                {"ihor@"},
                {"softserve@$!^%&"}
        };
    }

    @DataProvider
    public Object[][] emailFieldValidData() {
        return new Object[][]{
                {"ihor@gmailcom"},
                {"softserve@g"},
                {"ihor@поштаком"}
        };
    }

    @DataProvider
    public Object[][] usernameFieldInvalidData() {
        return new Object[][]{
                {"ihorm"},
                {"6pPYhgScMW7UwKIrzqgqrK0icakYj3d"},
                {"1@#$%^&*("},
                {"ігормартинюк"},
                {"...6666"},
                {"........"}
        };
    }

    @DataProvider
    public Object[][] usernameFieldValidData() {
        return new Object[][]{
                {"ihormartyniuk"},
                {"666666"},
                {"lG26AQssAlnWhzYNCfCeibPMhNtdC"},
                {"wkLP1dZeK9cAPq91tGnHAf3kcAFzk."},
                {"wkLP1dZeK9cAPq91tGnHAf3kcAF..."}
        };
    }

    @DataProvider
    public Object[][] passwordFieldInvalidData() {
        return new Object[][]{
                {"ihoryanya"},
                {"ярославвв"},
                {"UPPERCASELETTERS"},
                {"lowercaseletters"},
                {"1234567890"},
                {"!#$%&'*+-/=?^_`{|}~"}
        };
    }

    @DataProvider
    public Object[][] confirmPasswordFieldData() {
        return new Object[][]{
                {"Imartyniuk20$", "ihor", false},
                {"iMartyniuk25#", "iMartyniuk25", false},
                {"imArtyniuk26&", "imArtyniuk6&", false},
                {"imaRtyniuk20$", "imartyniuk2", false},
                {"Imartyniuk20$", "Imartyniuk20$", true},
                {"iMartyniuk25#", "iMartyniuk25#", true},
                {"imArtyniuk26&", "imArtyniuk26&", true},
                {"imaRtyniuk20$", "imaRtyniuk20$", true},
        };
    }

    @Test(dataProvider = "emailFieldInvalidData")
    public void verifyEmailFieldWInvalidData(String keysToSend) {
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(keysToSend);
        email.click();
        driver.findElement(By.xpath("//*[@class='switch-sign-up']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='error-message ng-star-inserted']")).getText(),
                "Email is required");
    }

    @Test(dataProvider = "emailFieldValidData")
    public void verifyEmailFieldWValidData(String keysToSend) {
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(keysToSend);
        email.click();
        driver.findElement(By.xpath("//*[@class='switch-sign-up']")).click();
        int size = driver.findElements(By.xpath("//*[@class='error-message ng-star-inserted']")).size();
        Assert.assertEquals(size, 0);
    }

    @Test
    public void verifyEmptyEmailField() {
        driver.findElement(By.id("email")).click();
        driver.findElement(By.xpath("//*[@class='switch-sign-up']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='error-message ng-star-inserted']")).getText(),
                "Email is required");
    }

    @Test(dataProvider = "usernameFieldInvalidData")
    public void verifyUsernameFieldWInvalidData(String keysToSend) {
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys(keysToSend);
        firstName.click();
        driver.findElement(By.xpath("//*[@class='switch-sign-up']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='error-message ng-star-inserted']")).getText(),
                "The name must contain 6-30 characters and can contain letters(a-z), numbers(0-9) and a dot(.)");
    }

    @Test(dataProvider = "usernameFieldValidData")
    public void verifyUsernameFieldWValidData(String keysToSend) {
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys(keysToSend);
        firstName.click();
        driver.findElement(By.xpath("//*[@class='switch-sign-up']")).click();
        int size = driver.findElements(By.xpath("//*[@class='error-message ng-star-inserted']")).size();
        Assert.assertEquals(size, 0);
    }

    @Test(dataProvider = "passwordFieldInvalidData")
    public void verifyPasswordFieldWInvalidData(String keysToSend) {
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(keysToSend);
        password.click();
        driver.findElement(By.xpath("//*[@class='switch-sign-up']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='error-message ng-star-inserted']")).getText(),
                "Password has contain at least one character of Uppercase letter (A-Z), Lowercase letter (a-z)," +
                        " Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)");
    }

    @Test(dataProvider = "confirmPasswordFieldData")
    public void verifyConfirmPasswordFieldWData(String passwordToSend, String repeatPasswordToSend, boolean passwordsMatch) {
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(passwordToSend);
        password.click();
        WebElement repeatPassword = driver.findElement(By.id("repeatPassword"));
        repeatPassword.sendKeys(repeatPasswordToSend);
        repeatPassword.click();
        driver.findElement(By.xpath("//*[@class='switch-sign-up']")).click();
        if (passwordsMatch) {
            int size = driver.findElements(By.xpath("//*[@class='error-message ng-star-inserted']")).size();
            Assert.assertEquals(size, 0);
        }
        else {
            Assert.assertEquals(driver.findElement(By.xpath("//*[@class='error-message ng-star-inserted']")).getText(),
                    "Passwords do not match");
        }
    }
}
