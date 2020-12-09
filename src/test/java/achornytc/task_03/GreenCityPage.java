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


    public GreenCityPage() {
        greenCityWebDriverSetup();
        greenCityPageInit();
    }

    private void greenCityPageInit() {
        greenCityPageHeader = new GreenCityPageHeader(getDriver());
        greenCityContentContainer = new GreenCityContentContainer(getDriver());
        greenCityPageFooter = new GreenCityPageFooter(getDriver());
    }

    public void greenCityWebDriverSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public GreenCityPageHeader getGreenCityPageHeader() {
        return greenCityPageHeader;
    }

    public GreenCityContentContainer getGreenCityContentContainer() {
        return greenCityContentContainer;
    }

    public GreenCityPageFooter getGreenCityPageFooter() {
        return greenCityPageFooter;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public GreenCityPage open(String url) {
        driver.get(url);
        return this;
    }

    public GreenCityPage openWelcomePage() {
        open(WELCOME_PAGE_URL);
        return this;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void close() {
        driver.manage().deleteAllCookies();
        driver.close();
    }

    public GreenCityPage openEcoNewsHeaderMenuItem() {
        driver
                .findElement(By
                        .xpath(greenCityPageHeader
                                .getEcoNewsMenuItemXPath()))
                .click();
        return this;
    }

    public String getEcoNewsExpectedPageTitle() {
        return this
                .getGreenCityPageHeader()
                .getHeaderNavigationMenu()
                .getEcoNewsMenuItem()
                .getTargetPageTitle();
    }

    public GreenCityPage openTipsTricksHeaderMenuItem() {
        driver
                .findElement(By
                        .xpath(greenCityPageHeader
                                .getTipsTricksItemXPath()))
                .click();
        return this;
    }

    public String getTipsTricksExpectedPageTitle() {
        return this
                .getGreenCityPageHeader()
                .getHeaderNavigationMenu()
                .getTipsTricksMenuItem()
                .getTargetPageTitle();
    }

    public GreenCityPage openPlacesHeaderMenuItem() {
        driver
                .findElement(By
                        .xpath(greenCityPageHeader
                                .getPlacesItemXPath()))
                .click();
        return this;
    }

    public String getPlacesExpectedPageTitle() {
        return this
                .getGreenCityPageHeader()
                .getHeaderNavigationMenu()
                .getPlacesMenuItem()
                .getTargetPageTitle();
    }

    public GreenCityPage openAboutUsHeaderMenuItem() {
        driver
                .findElement(By
                        .xpath(greenCityPageHeader
                                .getAboutUsItemXPath()))
                .click();
        return this;
    }

    public String getAboutUsExpectedPageTitle() {
        return this
                .getGreenCityPageHeader()
                .getHeaderNavigationMenu()
                .getAboutUsMenuItem()
                .getTargetPageTitle();
    }

    public GreenCityPage openEcoNewsFooterMenuItem() {
        driver
                .findElement(By
                        .xpath(greenCityPageFooter
                                .getEcoNewsItemXPath()))
                .click();
        return this;
    }

    public GreenCityPage openTipsTricksFooterMenuItem() {
        driver
                .findElement(By
                        .xpath(greenCityPageFooter
                                .getTipsTricksItemXPath()))
                .click();
        return this;
    }

    public GreenCityPage openPlacesFooterMenuItem() {
        driver
                .findElement(By
                        .xpath(greenCityPageFooter
                                .getPlacesItemXPath()))
                .click();
        return this;
    }

    public GreenCityPage openAboutUsFooterMenuItem() {
        driver
                .findElement(By
                        .xpath(greenCityPageFooter
                                .getAboutUsItemXPath()))
                .click();
        return this;
    }
}
