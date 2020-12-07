package okharabara.task02;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private WebDriver driver;
    public WebElement getChangedLanguage(String name) {
        driver.findElement(By.cssSelector("div.switcher-wrapper>ul")).click();
        return driver.findElement(By.xpath("//ul[@class='add-shadow']//li[contains(text(),'" + name + "')]"));
    }

    public void takeScreenShot(String name) {
        String currentTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-S").format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_TC_" + name + "_screenshot.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void takePageSource(String name) {
        String currentTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-S").format(new Date());
        String pageSource = driver.getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_TC_" + name + "_source.html");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
