package dganushkeevych.task_03;

import dganushkeevych.task_03.header.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EconewsPage extends TopPart {

    protected WebDriver driver;

    private WebElement news;
    private WebElement education;
    private WebElement initiatives;
    private WebElement lifehacks;
    private WebElement ads;
    private WebElement events;

    private NewsComponent newsComponent;
    private WebElement foundItems;

    public EconewsPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        news = driver.findElement(By.xpath("//li[contains(text(),'News')]"));
        education = driver.findElement(By.xpath("//li[contains(text(),'Education')]"));
        initiatives = driver.findElement(By.xpath("//li[contains(text(),'Initiatives')]"));
        lifehacks = driver.findElement(By.xpath("//li[contains(text(),'Lifehacks')]"));
        ads = driver.findElement(By.xpath("//li[contains(text(),'Ads')]"));
        events = driver.findElement(By.xpath("//li[contains(text(),'Events')]"));

        foundItems = driver.findElement(By.xpath("//app-remaining-count//p"));
    }

    // Page Object
    // News Filter
    public WebElement getNews() {
        return news;
    }

    public void clickNews() {
        getNews().click();
    }

    // Education Filter
    public WebElement getEducation() {
        return education;
    }

    public void clickEducation() {
        getEducation().click();
    }

    // Initiatives Filter
    public WebElement getInitiatives() {
        return initiatives;
    }

    public void clickInitiatives() {
        getInitiatives().click();
    }

    // Lifehacks Filter
    public WebElement getLifehacks() {
        return lifehacks;
    }

    public void clickLifehacks() {
        getLifehacks().click();
    }

    // Ads Filter
    public WebElement getAds() {
        return ads;
    }

    public void clickAds() {
        getAds().click();
    }

    // Events Filter
    public WebElement getEvents() {
        return events;
    }

    public void clickEvents() {
        getEvents().click();
    }

    //Count of News
    public Integer getCountOfFoundItems() {
        return Integer.parseInt(foundItems.getText().replaceAll("[^0-9]", ""));
    }

    public WebElement getFoundItems() {
        return foundItems;
    }

}