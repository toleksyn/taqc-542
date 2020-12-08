package achornytc.task_03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GreenCityPageHeader extends GreenCityWebElement {
    private GreenCityLogo greenCityLogo;
    private NavigationMenu headerNavigationMenu;
    private SearchField searchField;
    private LanguageSelector languageSelector;
    private UserManagementBlock userManagementBlock;
    private BurgerMenuButton burgerMenuButton;
    private BurgerMenuContainer burgerMenuContainer;

    public GreenCityPageHeader(WebDriver driver) {
        super(driver);
        greenCityPageHeaderInit();
    }

    private void greenCityPageHeaderInit() {
        selfXPath = "//div[@class='header']";

        greenCityLogo = new GreenCityLogo(getDriver());
        headerNavigationMenu = new NavigationMenu(getDriver());
        searchField = new SearchField(getDriver());
        languageSelector = new LanguageSelector(getDriver());
        userManagementBlock = new UserManagementBlock(getDriver());
        burgerMenuButton = new BurgerMenuButton(getDriver());
        burgerMenuContainer = new BurgerMenuContainer(getDriver());
    }

    public GreenCityLogo getGreenCityLogo() {
        return greenCityLogo;
    }

    public NavigationMenu getHeaderNavigationMenu() {
        return headerNavigationMenu;
    }

    public SearchField getSearchField() {
        return searchField;
    }

    public LanguageSelector getLanguageSelector() {
        return languageSelector;
    }

    public UserManagementBlock getUserManagementBlock() {
        return userManagementBlock;
    }

    public BurgerMenuButton getBurgerMenuButton() {
        return burgerMenuButton;
    }

    public BurgerMenuContainer getBurgerMenuContainer() {
        return burgerMenuContainer;
    }

    public String getEcoNewsMenuItemXPath() {
        if (burgerMenuButton.isDisplayed()) {
            burgerMenuButton.clickOnSelf();
            return getSelfXPath() + burgerMenuContainer.getEcoNewsMenuItemXPath();
        }
        return getSelfXPath() + headerNavigationMenu.getEcoNewsMenuItem().getSelfXPath();
    }

    public String getTipsTricksItemXPath() {
        if (burgerMenuButton.isDisplayed()) {
            burgerMenuButton.clickOnSelf();
            return getSelfXPath() + burgerMenuContainer.getTipsTricksMenuItemXPath();
        }
        return getSelfXPath() + headerNavigationMenu.getTipsTricksMenuItem().getSelfXPath();
    }

    public String getPlacesItemXPath() {
        if (burgerMenuButton.isDisplayed()) {
            burgerMenuButton.clickOnSelf();
            return getSelfXPath() + burgerMenuContainer.getPlacesMenuItemXPath();
        }
        return getSelfXPath() + headerNavigationMenu.getPlacesMenuItem().getSelfXPath();
    }

    public String getAboutUsItemXPath() {
        if (burgerMenuButton.isDisplayed()) {
            burgerMenuButton.clickOnSelf();
            return getSelfXPath() + burgerMenuContainer.getAboutUsMenuItemXPath();
        }
        return getSelfXPath() + headerNavigationMenu.getAboutUsMenuItem().getSelfXPath();
    }
}
