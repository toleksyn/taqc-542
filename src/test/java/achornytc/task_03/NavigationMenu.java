package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class NavigationMenu extends GreenCityWebElement {
    private EcoNewsMenuItem ecoNewsMenuItem;
    private TipsTricksMenuItem tipsTricksMenuItem;
    private PlacesMenuItem placesMenuItem;
    private AboutUsMenuItem aboutUsMenuItem;

    public NavigationMenu(WebDriver driver) {
        super(driver);
        navigationMenuInit();
    }

    private void navigationMenuInit() {
        ecoNewsMenuItem = new EcoNewsMenuItem(getDriver());
        tipsTricksMenuItem = new TipsTricksMenuItem(getDriver());
        placesMenuItem = new PlacesMenuItem(getDriver());
        aboutUsMenuItem = new AboutUsMenuItem(getDriver());
    }

    public EcoNewsMenuItem getEcoNewsMenuItem() {
        return ecoNewsMenuItem;
    }

    public TipsTricksMenuItem getTipsTricksMenuItem() {
        return tipsTricksMenuItem;
    }

    public PlacesMenuItem getPlacesMenuItem() {
        return placesMenuItem;
    }

    public AboutUsMenuItem getAboutUsMenuItem() {
        return aboutUsMenuItem;
    }
}
