package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class GreenCityContentContainer extends GreenCityWebElement {
    public GreenCityContentContainer(WebDriver driver) {
        super(driver);
        greenCityContentContainerInit();
    }

    private void greenCityContentContainerInit() {
        selfXPath = "//div[@class = 'app-container']";
    }
}
