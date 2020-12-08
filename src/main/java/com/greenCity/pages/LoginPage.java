package com.greenCity.pages;

public class LoginPage {
    extends BasePage{
        public LoginInPage fillEmailField(String email){
            driver.findElement(By.id("email")).sendKeys(email);
            return this;
        }
}
