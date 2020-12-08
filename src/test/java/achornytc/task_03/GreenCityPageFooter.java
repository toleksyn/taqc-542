package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class GreenCityPageFooter extends GreenCityWebElement {
    private NavigationMenu footerNavigationMenu;

    public GreenCityPageFooter(WebDriver driver) {
        super(driver);
        greenCityPageFooterInit();
    }

    private void greenCityPageFooterInit() {
        footerNavigationMenu = new NavigationMenu(getDriver());
        selfXPath = "//footer";
    }

    public String getEcoNewsItemXPath() {
        return getSelfXPath() + footerNavigationMenu.getEcoNewsMenuItem().getSelfXPath();
    }

    public String getTipsTricksItemXPath() {
        return getSelfXPath() + footerNavigationMenu.getTipsTricksMenuItem().getSelfXPath();
    }

    public String getPlacesItemXPath() {
        return getSelfXPath() + footerNavigationMenu.getPlacesMenuItem().getSelfXPath();
    }

    public String getAboutUsItemXPath() {
        return getSelfXPath() + footerNavigationMenu.getAboutUsMenuItem().getSelfXPath();
    }
}
