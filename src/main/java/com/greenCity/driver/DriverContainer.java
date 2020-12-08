package com.greenCity.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverContainer {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setProperty() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Lenovo/Desktop/TAQC/chromedriver.exe");
    }

    public static void createWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
