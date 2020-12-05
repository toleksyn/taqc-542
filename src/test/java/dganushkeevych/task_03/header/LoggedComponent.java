package dganushkeevych.task_03.header;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoggedComponent {
    
    private WebDriver driver;
    //
    private WebElement profile;
    //
    //private ProfileComponent profileComponent;

    public LoggedComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        // init elements
        profile = driver.findElement(By.xpath("//li[@class='tertiary-global-button']//a"));
    }

    // Page Object
    
    //profile
    public WebElement getProfile() {
        return profile;
    }
    
    public String getProfileText() {
        return getProfile().getText();
    }

    public void clickProfile() {
        getProfile().click();
    }
    
    // Functional

    // Business Logic
}
