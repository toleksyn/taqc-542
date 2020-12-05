package dganushkeevych.task_03.header;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LanguageComponent {
    
    private WebDriver driver;
    //
    private WebElement en;
    private WebElement ua;
    private WebElement ru;
    
    public LanguageComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        // init elements
        en = driver.findElement(By.xpath("//li[contains(text(),'En')]"));
        ua = driver.findElement(By.xpath("//li[contains(text(),'Ua')]"));
        ru = driver.findElement(By.xpath("//li[contains(text(),'Ru')]"));
    }

    // Page Object
    
    //en
    public WebElement getEn() {
        return en;
    }

    public String getEnText() {
        return getEn().getText();
    }
    
    public void clickEn() {
        getEn().click();
    }

    //ua
    public WebElement getUa() {
        return ua;
    }

    public String getUaText() {
        return getUa().getText();
    }
    
    public void clickUa() {
        getUa().click();
    }
    
    //ru
    public WebElement getRu() {
        return ru;
    }

    public String getRuText() {
        return getRu().getText();
    }
    
    public void clickRu() {
        getRu().click();
    }
    
    // Functional

    // Business Logic
}
