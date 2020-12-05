package dganushkeevych.task_03.header;

import dganushkeevych.task_03.EconewsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class TopPart implements Attributes {

    protected WebDriver driver;
    //
    private WebElement logo;
    private WebElement search;
    private WebElement language;
    //
    private SearchComponent searchComponent;
    private LanguageComponent languageComponent;
    private GuestComponent guestComponent;
    private LoggedComponent loggedComponent;
    //
    private WebElement burger;
    private WebElement econews;
    private WebElement tipsTricks;
    private WebElement places;
    private WebElement about;
    private WebElement myHabits;

    public TopPart(WebDriver driver) {
        // super(driver);
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        // init elements
        logo = driver.findElement(By.cssSelector("div.logo a"));
        search = driver.findElement(By.cssSelector("li[class*='search'] a"));
        language = driver.findElement(By.cssSelector("div.switcher-wrapper ul"));
        //
        burger = driver.findElement(By.cssSelector("li.burger-b"));
        econews = driver.findElement(By.cssSelector("div.navigation-menu a[href*='/news']"));
        tipsTricks = driver.findElement(By.cssSelector("div.navigation-menu a[href*='/tips']"));
        places = driver.findElement(By.cssSelector("div.navigation-menu a[href*='/map']"));
        about = driver.findElement(By.cssSelector("div.navigation-menu a[href*='/about']"));
        myHabits = driver.findElement(By.cssSelector("div.navigation-menu a[href*='/profile']"));
    }

    // Page Object

    // logo
    public WebElement getLogo() {
        return logo;
    }

    public void clickLogo() {
        getLogo().click();
    }

    // search
    public WebElement getSearch() {
        return search;
    }

    public void clickSearch() {
        getSearch().click();
        // TODO
    }

    // language
    public WebElement getLanguage() {
        return language;
    }

    public String getLanguageText() {
        return getLanguage().getText().trim();
    }

    public void clickLanguage() {
        getLanguage().click();
        // TODO
    }

    // burger
    public WebElement getBurger() {
        return burger;
    }

    public boolean isDisplayedBurger() {
        return getBurger().isDisplayed();
    }

    public void clickBurger() {
        if (isDisplayedBurger()) {
            getBurger().click();
        }
        // TODO else new Exception();
    }

    // econews
    public boolean isDisplayedEconews() {
        return econews.isDisplayed();
    }

    public WebElement getEconews() {
        if (!isDisplayedEconews()) {
            clickBurger();
        }
        return econews;
    }

    public String getEconewsText() {
        return getEconews().getText();
    }

    public void clickEconews() {
        getEconews().click();
    }

    // tipsTricks
    public boolean isDisplayedTipsTricks() {
        return tipsTricks.isDisplayed();
    }

    public WebElement getTipsTricks() {
        if (!isDisplayedTipsTricks()) {
            clickBurger();
        }
        return tipsTricks;
    }

    public String getTipsTricksText() {
        return getTipsTricks().getText();
    }

    public void clickTipsTricks() {
        getTipsTricks().click();
    }

    // places
    public boolean isDisplayedPlaces() {
        return places.isDisplayed();
    }

    public WebElement getPlaces() {
        if (!isDisplayedPlaces()) {
            clickBurger();
        }
        return places;
    }

    public String getPlacesText() {
        return getPlaces().getText();
    }

    public void clickPlaces() {
        getPlaces().click();
    }

    // about
    public boolean isDisplayedAbout() {
        return about.isDisplayed();
    }

    public WebElement getAbout() {
        if (!isDisplayedAbout()) {
            clickBurger();
        }
        return about;
    }

    public String getAboutText() {
        return getAbout().getText();
    }

    public void clickAbout() {
        getAbout().click();
    }

    // myHabits
    public boolean isDisplayedMyHabits() {
        return myHabits.isDisplayed();
    }

    public WebElement getMyHabits() {
        if (!isDisplayedMyHabits()) {
            clickBurger();
        }
        return myHabits;
    }

    public String getMyHabitsText() {
        return getMyHabits().getText();
    }

    public void clickMyHabits() {
        getMyHabits().click();
    }

    // Functional

    // Business Logic

    public EconewsPage navigateEconewsPage() {
        clickEconews();
        return new EconewsPage(driver);
    }

//    public AboutPage navigateAboutPage() {
//        clickAbout();
//        return new AboutPage(driver);
//    }

}
