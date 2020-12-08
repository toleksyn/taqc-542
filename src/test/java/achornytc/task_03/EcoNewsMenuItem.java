package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class EcoNewsMenuItem extends GreenCityMenuItem {
    public EcoNewsMenuItem(WebDriver driver) {
        super(driver);
        ecoNewsMenuItemInit();
    }

    private void ecoNewsMenuItemInit() {
        selfXPath = "//a[normalize-space(text()) = 'Eco news']";
        selfLinkText = "Eco news";
        targetPageTitle = "Eco news";
    }
}
