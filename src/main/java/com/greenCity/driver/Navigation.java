package com.greenCity.driver;

import org.openqa.selenium.WebDriver;

public class Navigation {
    private WebDriver driver;

    public Navigation() {
        this.driver = DriverContainer.getDriver();
    }

    public void navigateToUrl(String url) {
        driver.get(url);
    }
}
