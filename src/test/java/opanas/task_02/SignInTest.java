package opanas.task_02;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
public class SignInTest {

    private WebDriver driver;
    private final String baseUrl = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
    private static int delay=10;
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.chrome.driver","C:/Users/Lenovo/Desktop/chromedriver.exe");
    }
    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
    @Test(description = "In this test we will input the valid data in all fields of Sign in form and look if we will be redirect to our personal account")
    public void verifyValidData()  {
        WebElement LogInEmail=driver.findElement(By.id("email"));
        LogInEmail.click();
        LogInEmail.sendKeys("xdknxusqvjeovowpfk@awdrt.com");
        WebElement LogInPassword=driver.findElement(By.id("password"));
        LogInPassword.click();
        LogInPassword.sendKeys(System.getenv().get("valid_password"));
        WebElement button=driver.findElement(By.xpath("//*[@type='submit']"));
        button.click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='tertiary-global-button']")).getText(),"you can buy eco-bags here");
    }
    @Test(description = "Fill in password with incorrect data")
    private void verifyInvalidPassword() {
        WebElement LogInEmail=driver.findElement(By.id("email"));
        LogInEmail.click();
        LogInEmail.sendKeys("xdknxusqvjeovowpfk@awdrt.com");
        WebElement LogInPassword=driver.findElement(By.id("password"));
        LogInPassword.click();
        LogInPassword.sendKeys("55464");
        driver.findElement(By.xpath(".//*[@class='or-use-google']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='validation-password-error ng-star-inserted']")).getText(),"Password must be at least 8 characters long");
    }
    @Test(description = "Fill in email with incorrect data")
    private void verifyInvalidEmail() {
        WebElement LogInEmail=driver.findElement(By.id("email"));
        LogInEmail.click();
        LogInEmail.sendKeys("xdknxusqvjeovowpfk.awdrt.com");
        driver.findElement(By.xpath(".//*[@class='or-use-google']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='validation-email-error ng-star-inserted']")).getText(),"Please check that your e-mail address is indicated correctly");
    }
    @Test(description = "Connect via gmail")
    private void verifyGmailConnection() {
        driver.findElement(By.xpath(".//*[@class='google-sign-in']")).click();
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles);
        List<String> siteIdList=new ArrayList<>(handles);
        driver.switchTo().window(siteIdList.get(1));
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='kHn9Lb']")).getText(),"Увійдіть в обліковий запис Google");
    }
    @Test()
    private void verifyForgotPassword()  {
        driver.findElement(By.xpath(".//*[@class='forgot-password']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='send-btn primary-global-button']")).getText(),"Submit a login link");
    }

}