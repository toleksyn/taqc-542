package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class SearchField extends GreenCityWebElement {
    public SearchField(WebDriver driver) {
        super(driver);
        searchFieldInit();
    }

    private void searchFieldInit() {
        selfXPath = "//li[@class = 'search ng-star-inserted']";
    }
}
