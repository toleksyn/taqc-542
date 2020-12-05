package dganushkeevych.task_03.header;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GuestComponent {

    private WebDriver driver;
    //
    private WebElement signIn;
    private WebElement signUp;

    public GuestComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        // init elements
        signIn = driver.findElement(By.cssSelector("li[class*='sign-in'] a"));
        signUp = driver.findElement(By.cssSelector("li[class*='sign-up'] div"));
    }

    // Page Object
    
    //signIn
    public WebElement getSignIn() {
        return signIn;
    }
    
    public String getSignInText() {
        return getSignIn().getText();
    }

    public void clickSignIn() {
        getSignIn().click();
    }
    
    //signUp
    public WebElement getSignUp() {
        return signUp;
    }

    public String getSignUpText() {
        return getSignUp().getText();
    }
    
    public void clickSignUp() {
        getSignUp().click();
    }
    
    // Functional

    // Business Logic
}
