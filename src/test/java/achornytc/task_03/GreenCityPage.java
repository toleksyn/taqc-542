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
    private final String WELCOME_PAGE_URL = "https://ita-social-projects.github.io/GreenCityClient/";
    private String headerXPath = "//div[@class='header']";
    private String footerXPath = "//footer";


    public GreenCityPage() {
        greenCityPageInit();
        greenWebDriverSetup();
    }

    private void greenCityPageInit() {
        greenCityPageHeader = new GreenCityPageHeader();
        greenCityContentContainer = new GreenCityContentContainer();
        greenCityPageFooter = new GreenCityPageFooter();
    }

    public void greenWebDriverSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public GreenCityPage open(String url) {
        driver.get(url);
        return this;
    }

    public GreenCityPage openWelcomePage() {
        open(WELCOME_PAGE_URL);
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

    public void openEcoNewsHeaderMenuItem() {
        driver
                .findElement(By
                        .xpath(greenCityPageFooter
                                .getEcoNewsMenuItemXPath()))
                .click();
    }
}
