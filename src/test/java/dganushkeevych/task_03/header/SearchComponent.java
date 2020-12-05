package dganushkeevych.task_03.header;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchComponent implements Attributes {

    private WebDriver driver;
    //
    private WebElement search;
    private WebElement closeIcon;

    public SearchComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        // init elements
        search = driver.findElement(By.cssSelector("input[placeholder='Search']"));
        closeIcon = driver.findElement(By.cssSelector("div.close-icon"));
    }

    // Page Object
    
    //search
    public WebElement getSearch() {
        return search;
    }

    public String getSearchText() {
        return getSearch().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clearSearch() {
        getSearch().clear();
    }

    public void clickSearch() {
        getSearch().click();
    }

    public void typeSearch(String text) {
        getSearch().sendKeys(text);
    }
    
    //closeIcon
    public WebElement getCloseIcon() {
        return closeIcon;
    }

    public void clickCloseIcon() {
        getCloseIcon().click();
    }
    
    // Functional

    // Business Logic
}
