package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class PlacesMenuItem extends GreenCityMenuItem {
    public PlacesMenuItem(WebDriver driver) {
        super(driver);
        placesMenuItemInit();
    }

    private void placesMenuItemInit() {
        selfXPath = "//a[normalize-space(text()) = 'Places']";
        selfLinkText = "Places";
        targetPageTitle = "Places";
    }
}
