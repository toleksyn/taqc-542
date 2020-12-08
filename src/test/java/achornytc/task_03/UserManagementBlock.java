package achornytc.task_03;

import org.openqa.selenium.WebDriver;

public class UserManagementBlock extends GreenCityWebElement {
    public UserManagementBlock(WebDriver driver) {
        super(driver);
        userManagementBlockInit();
    }

    private void userManagementBlockInit() {
        //TODO - Sign in and Sign out are separate elements (not a block). After logging in appears drop down block.
    }
}
