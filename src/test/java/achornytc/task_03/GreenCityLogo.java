package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class GreenCityLogo extends GreenCityMenuItem {
    public GreenCityLogo(WebDriver driver) {
        super(driver);
        greenCityLogoInit();
    }

    private void greenCityLogoInit() {
        selfXPath = "//div[@class = 'logo']";
    }
}
