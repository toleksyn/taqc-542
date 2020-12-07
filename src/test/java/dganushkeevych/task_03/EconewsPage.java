package dganushkeevych.task_03;

import dganushkeevych.task_03.header.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EconewsPage extends TopPart {

    protected WebDriver driver;

    private WebElement newsFilter;
    private WebElement educationFilter;
    private WebElement initiativesFilter;
    private WebElement lifehacksFilter;
    private WebElement adsFilter;
    private WebElement eventsFilter;

    private NewsComponent newsComponent;
    private WebElement foundItemsText;

    public EconewsPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        newsFilter = driver.findElement(By.xpath("//li[contains(text(),'News')]"));
        educationFilter = driver.findElement(By.xpath("//li[contains(text(),'Education')]"));
        initiativesFilter = driver.findElement(By.xpath("//li[contains(text(),'Initiatives')]"));
        lifehacksFilter = driver.findElement(By.xpath("//li[contains(text(),'Lifehacks')]"));
        adsFilter = driver.findElement(By.xpath("//li[contains(text(),'Ads')]"));
        eventsFilter = driver.findElement(By.xpath("//li[contains(text(),'Events')]"));

        foundItemsText = driver.findElement(By.xpath("//app-remaining-count//p"));
    }

    // News Filter
    public WebElement getNewsFilter() {
        return newsFilter;
    }

    public void clickNews() {
        getNewsFilter().click();
    }

    // Education Filter
    public WebElement getEducationFilter() {
        return educationFilter;
    }

    public void clickEducation() {
        getEducationFilter().click();
    }

    // Initiatives Filter
    public WebElement getInitiativesFilter() {
        return initiativesFilter;
    }

    public void clickInitiatives() {
        getInitiativesFilter().click();
    }

    // Lifehacks Filter
    public WebElement getLifehacksFilter() {
        return lifehacksFilter;
    }

    public void clickLifehacks() {
        getLifehacksFilter().click();
    }

    // Ads Filter
    public WebElement getAdsFilter() {
        return adsFilter;
    }

    public void clickAds() {
        getAdsFilter().click();
    }

    // Events Filter
    public WebElement getEventsFilter() {
        return eventsFilter;
    }

    public void clickEvents() {
        getEventsFilter().click();
    }

    // Count of News
    public Integer getCountOfFoundItems() {
        return Integer.parseInt(foundItemsText.getText().replaceAll("[^0-9]", ""));
    }

    public WebElement getFoundItemsText() {
        return foundItemsText;
    }

}