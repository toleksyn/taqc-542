package bhalak.task_02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Util {
	private static final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
	
	private Util() {}
	
	public static void takeScreenShot(WebDriver driver) throws IOException {
		String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
	}

	public static void takePageSource(WebDriver driver) throws IOException {
		String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
		String pageSource = driver.getPageSource();
		byte[] strToBytes = pageSource.getBytes();
		Path path = Paths.get("./" + currentTime + "_source.html");
		Files.write(path, strToBytes, StandardOpenOption.CREATE);
	}	
}
