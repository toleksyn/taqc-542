package achornytc.task_03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GreenCityWebElement {
    private WebDriver driver;
    protected String selfXPath;
    protected String selfCSSSelector;

    public GreenCityWebElement(WebDriver driver) {
        this.driver = driver;
    }

    public String getSelfXPath() {
        return selfXPath;
    }

    public String getSelfCSSSelector() {
        return selfCSSSelector;
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected boolean isDisplayed() {
        return this
                .getDriver()
                .findElement(By
                        .xpath(this.getSelfXPath()))
                .isDisplayed();
    }
}
