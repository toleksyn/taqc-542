package dganushkeevych.task_03;

import dganushkeevych.task_03.header.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AboutPage extends TopPart {

    protected WebDriver driver;

    private WebElement formHabitUpperButton;
    private WebElement formHabitBottomButton;
    private WebElement findEcoPlacesButton;
    private WebElement tipsAndTricksButton;
    private WebElement findPeopleUpperButton;
    private WebElement getInspiredButton;
    private WebElement findPeopleBottomButton;

    private WebElement aboutUsText;

    public AboutPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        //not correct path
        formHabitUpperButton = driver.findElement(By.cssSelector(".container-about .full-text-block > button"));
        formHabitBottomButton =  driver.findElement(By.cssSelector(".container-vision .full-text-block > button"));
        findEcoPlacesButton = driver.findElement(By.id("second-card-link"));
        tipsAndTricksButton = driver.findElement(By.id("third-card-link"));
        findPeopleUpperButton = driver.findElement(By.id("fourth-card-link"));
        getInspiredButton = driver.findElement(By.id("fifth-card-link"));
        findPeopleBottomButton = driver.findElement(By.cssSelector(".card-holder-odd.first-card-holder a[href='#/map']"));

        aboutUsText = driver.findElement(By.cssSelector(".container-about"));
    }

    // Buttons
    public WebElement getFormHabitUpperButton() {
        return formHabitUpperButton;
    }

    public void clickFormHabitUpperButton() {
        getFormHabitUpperButton().click();
    }

    public WebElement getFormHabitBottomButton() {
        return formHabitBottomButton;
    }

    public void clickFormHabitBottomButton() {
        getFormHabitBottomButton().click();
    }

    public WebElement getFindEcoPlacesButton() {
        return findEcoPlacesButton;
    }

    public void clickFindEcoPlacesButton() {
        getFindEcoPlacesButton().click();
    }

    public WebElement getTipsAndTricksButton() {
        return tipsAndTricksButton;
    }

    public void clickTipsAndTricksButton() {
        getTipsAndTricksButton().click();
    }

    public WebElement getFindPeopleUpperButton() {
        return findPeopleUpperButton;
    }

    public void clickFindPeopleUpperButton() {
        getFindPeopleUpperButton().click();
    }

    public WebElement getGetInspiredButton() {
        return getInspiredButton;
    }

    public void clickGetInspiredButton() {
        getGetInspiredButton().click();
    }

    public WebElement getFindPeopleBottomButton() {
        return findPeopleBottomButton;
    }

    public void clickFindPeopleBottomButton() {
        getFindPeopleBottomButton().click();
    }

    // Text
    public boolean isAboutUsTextDisplayed() {
        return aboutUsText.getText().contains("About Us");
    }

    public WebElement getAboutUsText() {
        return aboutUsText;
    }

}