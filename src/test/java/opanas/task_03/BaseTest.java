import com.greenCity.driver.DriverContainer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    public WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        DriverContainer.createWebDriver();
        driver=DriverContainer.getDriver();
    }

    @BeforeSuite
    public void beforeSuite() {
        DriverContainer.setProperty();
    }
    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}

