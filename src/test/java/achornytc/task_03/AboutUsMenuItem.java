package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class AboutUsMenuItem extends GreenCityMenuItem {
    public AboutUsMenuItem(WebDriver driver) {
        super(driver);
        aboutUsMenuItemInit();
    }

    private void aboutUsMenuItemInit() {
        selfXPath = "//a[normalize-space(text()) = 'About us']";
        selfLinkText = "About us";
        targetPageTitle = "About us";
    }
}
