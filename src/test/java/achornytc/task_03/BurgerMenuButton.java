package achornytc.task_03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BurgerMenuButton extends GreenCityWebElement {
    public BurgerMenuButton(WebDriver driver) {
        super(driver);
        burgerMenuButtonInit();
    }

    private void burgerMenuButtonInit() {
        selfXPath = "//li[@class = 'burger-b']";
    }

    public void clickOnSelf() {
        this
                .getDriver()
                .findElement(By
                        .xpath(this
                                .getSelfXPath()))
                .click();
    }
}
