package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class BurgerMenuContainer extends GreenCityWebElement {
    private NavigationMenu navigationMenu;
    private UserManagementBlock userManagementBlock;

    public BurgerMenuContainer(WebDriver driver) {
        super(driver);
        burgerMenuContainerInit();
    }

    private void burgerMenuContainerInit() {
        selfXPath = "//div[@class = 'navigation-menu-left-col']";
        navigationMenu = new NavigationMenu(getDriver());
        userManagementBlock = new UserManagementBlock(getDriver());
    }

    public NavigationMenu getNavigationMenu() {
        return navigationMenu;
    }

    public UserManagementBlock getUserManagementBlock() {
        return userManagementBlock;
    }

    public String getEcoNewsMenuItemXPath() {
        return getSelfXPath() + navigationMenu.getEcoNewsMenuItem().getSelfXPath();
    }

    public String getTipsTricksMenuItemXPath() {
        return getSelfXPath() + navigationMenu.getTipsTricksMenuItem().getSelfXPath();
    }

    public String getPlacesMenuItemXPath() {
        return getSelfXPath() + navigationMenu.getPlacesMenuItem().getSelfXPath();
    }

    public String getAboutUsMenuItemXPath() {
        return getSelfXPath() + navigationMenu.getAboutUsMenuItem().getSelfXPath();
    }
}
