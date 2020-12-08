package achornytc.task_03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class GreenCityPage {
    private GreenCityPageHeader greenCityPageHeader;
    private GreenCityContentContainer greenCityContentContainer;
    private GreenCityPageFooter greenCityPageFooter;

    private static final long IMPLICIT_WAIT = 10L;
    private WebDriver driver;
    private String headerXPath = "//div[@class='header']";
    private String footerXPath = "//footer";


    public GreenCityPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public GreenCityPage open(String url) {
        driver.get(url);
        return this;
    }

    public GreenCityPage openHeaderMenuItem(String menuItemText) {
        driver.findElement(By.xpath(headerXPath + "//a[normalize-space(text()) = '" + menuItemText + "']")).click();
        return this;
    }

    public GreenCityPage openFooterMenuItem(String menuItemText) {
        driver.findElement(By.xpath(footerXPath + "//a[normalize-space(text()) = '" + menuItemText + "']")).click();
        return this;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void close() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
