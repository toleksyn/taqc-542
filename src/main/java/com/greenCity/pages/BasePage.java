package com.greenCity.pages;

import com.greenCity.driver.DriverContainer;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;
    public BasePage(){
        driver= DriverContainer.getDriver();
    }
}
