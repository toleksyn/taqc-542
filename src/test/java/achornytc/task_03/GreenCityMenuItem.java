package achornytc.task_03;

public class GreenCityMenuItem extends GreenCityWebElement{
    protected String selfLinkText;
    //protected String selfXPath;
    protected String targetURL;
    protected String targetPageTitle;
    protected String targetObjectXPath;

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
