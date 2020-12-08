package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class GreenCityMenuItem extends GreenCityWebElement {
    protected String selfLinkText;
    protected String targetURL;
    protected String targetPageTitle;
    protected String targetObjectXPath;

    public GreenCityMenuItem(WebDriver driver) {
        super(driver);
    }

    public String getSelfLinkText() {
        return selfLinkText;
    }

    public String getTargetPageTitle() {
        return targetPageTitle;
    }

    public String getTargetObjectXPath() {
        return targetObjectXPath;
    }

    public String getTargetURL() {
        return targetURL;
    }

}
