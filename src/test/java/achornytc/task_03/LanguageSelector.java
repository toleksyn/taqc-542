package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class LanguageSelector extends GreenCityWebElement {
    public LanguageSelector(WebDriver driver) {
        super(driver);
        languageSelectorInit();
    }

    private void languageSelectorInit() {
        selfXPath = "//div[@class = 'switcher-wrapper']";
    }
}
