package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class TipsTricksMenuItem extends GreenCityMenuItem {
    public TipsTricksMenuItem(WebDriver driver) {
        super(driver);
        tipsTricksMenuItemInit();
    }

    private void tipsTricksMenuItemInit() {
        selfXPath = "//a[normalize-space(text()) = 'Tips & Tricks']";
        selfLinkText = "Tips & Tricks";
        targetPageTitle = "Tips & Tricks";
    }
}
