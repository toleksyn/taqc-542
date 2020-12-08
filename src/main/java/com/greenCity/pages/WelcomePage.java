package com.greenCity.pages;


import org.openqa.selenium.By;

public class WelcomePage extends BasePage {
    public WelcomePage(){

    }

    public WelcomePage openWebApp(String url) {
        driver.get(url);
        return this;
    }

    public LoginInPage openSignInButton() {
        driver.findElement(By.xpath("//*[@class='sign-in-link tertiary-global-button last-nav-item']")).click();
        return new LoginInPage();
    }
}
