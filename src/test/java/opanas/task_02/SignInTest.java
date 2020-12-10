import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
public class SignInTest {
    private WebDriver driver;
    private final String baseUrl = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.chrome.driver","C:/Users/Lenovo/Desktop/TAQC/chromedriver.exe");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//*[@class='sign-in-link tertiary-global-button last-nav-item']")).click();
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @DataProvider(name="invalidEmailData")
    public Object[][] getInvalidEmail(){
        return new Object[][]{
                {"xdknxusqvjeovowpfk#awdrt.com"},
                {"xdknxusqvjeovowpfk.awdrt.com"},
                {"xdknxusqvjeovowpfk*awdrt.com"},
                {"xdknxusqvjeovowpfk@awdrt@com"},
                {"@xdknxusqvjeovowpfk@awdrt.com"},
                {"@awdrt.comxdknxusqvjeovowpfk"},
                {"@awdrt@comxdknxusqvjeovowpfk"}
        };
    }

    @Test
    public void verifyInvalidPasswordWithZeroCharacters(){
        WebElement logInPassword=driver.findElement(By.id("password"));
        logInPassword.click();
        logInPassword.clear();
        logInPassword.sendKeys("");
        driver.findElement(By.xpath(".//*[@class='or-use-google']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='validation-password-error ng-star-inserted']")).getText(),"Password is required");
    }

    @Test
    public void verifyInvalidPasswordBetweenOneAndSevenCharacters(){
        WebElement logInPassword=driver.findElement(By.id("password"));
        logInPassword.click();
        logInPassword.clear();
        logInPassword.sendKeys("low132");
        driver.findElement(By.xpath("//*[@class='missing-account']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='validation-password-error ng-star-inserted']")).getText(),"Password must be at least 8 characters long");
    }

    @Test
    public void verifyInvalidPasswordMoreThanEightCharacters(){
        WebElement logInPassword=driver.findElement(By.id("password"));
        logInPassword.click();
        logInPassword.clear();
        driver.findElement(By.xpath(".//*[@class='or-use-google']")).click();
        logInPassword.sendKeys("1Ty/,@q83dasd");
    }

    @Test(description = "In this test we will input the valid data in all fields of Sign in form and look if we will be redirect to our personal account")
    public void verifyValidData()  {
        WebElement logInEmail=driver.findElement(By.id("email"));
        logInEmail.click();
        logInEmail.clear();
        logInEmail.sendKeys("xdknxusqvjeovowpfk@awdrt.com");
        WebElement logInPassword=driver.findElement(By.id("password"));
        logInPassword.click();
        logInPassword.clear();
        logInPassword.sendKeys(System.getenv().get("valid_password"));
        WebElement button=driver.findElement(By.xpath("//*[@type='submit']"));
        button.click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='tertiary-global-button']")).getText(),"you can buy eco-bags here");
    }



    @Test(dataProvider = "invalidEmailData")
    public void verifyInvalidEmail(String keysToSent) {
        WebElement LogInEmail=driver.findElement(By.id("email"));
        LogInEmail.click();
        LogInEmail.clear();
        LogInEmail.sendKeys(keysToSent);
        driver.findElement(By.xpath(".//*[@class='or-use-google']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='validation-email-error ng-star-inserted']")).getText(),"Please check that your e-mail address is indicated correctly");
    }



    @Test(description = "Connect via gmail")
    public void verifyGmailConnection(){
        driver.findElement(By.xpath(".//*[@class='google-sign-in']")).click();
        driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                                                  public Boolean apply(WebDriver driver) {
                                                      return driver.getWindowHandles().size()>1;
                                                  }
                                              }
        );
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Set<String> handles = driver.getWindowHandles();
        for (String currentWindow: handles){
            driver.switchTo().window(currentWindow);
            if (driver.getCurrentUrl().contains("accounts.google.com")){
                break;
            }
        }
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='kHn9Lb']")).getText(),"Увійдіть в обліковий запис Google");
    }

    @Test()
    public void verifyForgotPassword()  {
        driver.findElement(By.xpath(".//*[@class='forgot-password']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='send-btn primary-global-button']")).getText(),"Submit a login link");
    }
}