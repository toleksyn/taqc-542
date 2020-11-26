package bhalak.task_02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date; 
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CorrectListViewTest {
	private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
	private final Long IMPLICITLY_WAIT_SECONDS = 10L;
	private final Long ONE_SECOND_DELAY = 1000L;
	private WebDriver driver;
	private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";

	private void presentationSleep() {
		presentationSleep(1);
	}

	private void presentationSleep(int seconds) {
		try {
			Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void takeScreenShot(WebDriver driver) throws IOException {
		String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
	}

	private void takePageSource(WebDriver driver) throws IOException {
		String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
		String pageSource = driver.getPageSource();
		byte[] strToBytes = pageSource.getBytes();
		Path path = Paths.get("./" + currentTime + "_source.html");
		Files.write(path, strToBytes, StandardOpenOption.CREATE);
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void verifyEcoNewsItemListView() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(BASE_URL);
		
		WebElement listItem = null;
		int newsContentLeftMargin = 0;
		Point listImagePosition = null;
		Point newsContentPosition = null;

		driver.findElement(By.cssSelector(".navigation-menu-left a[href *= '/news']")).click();

		if ( driver.findElement(By.xpath("//span[contains(@class, 'btn-tiles-active')]")).isDisplayed() ) {
			driver.findElement(By.xpath("//i[@class ='fa fa-bars']")).click();
		}

		Assert.assertTrue(driver.findElement(By.xpath("//span[@class = 'btn-bars btn-bars-active']")).isDisplayed());

		listItem = driver.findElement(By.cssSelector(".list li:first-child"));
		newsContentLeftMargin = Integer.parseInt(
												listItem.findElement(By.className("news-content"))
														.getCssValue("margin-top")
														.replaceAll("[^0-9]", ""));

		listImagePosition = listItem.findElement(By.className("list-image")).getLocation();
		newsContentPosition = listItem.findElement(By.className("news-content")).getLocation();

		Assert.assertEquals(listImagePosition.getY(), newsContentPosition.getY() - newsContentLeftMargin);
	}
}
