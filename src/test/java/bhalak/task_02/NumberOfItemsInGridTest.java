package bhalak.task_02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NumberOfItemsInGridTest {
	private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
	private final Long IMPLICITLY_WAIT_SECONDS = 3L;
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
	public void verifyNumberOfEcoNewsItemsInGrid() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(BASE_URL);

		int actualNumberOfGridItems = 0;
		int expectedNumberOfGridItems = 0;

		driver.findElement(By.cssSelector(".navigation-menu-left a[href *= '/news']")).click();

		Assert.assertTrue( driver.findElement(By.cssSelector(".btn-tiles-active")).isDisplayed() );

		List<WebElement> description = new ArrayList<WebElement>();
		WebElement footer = null;
		Actions action = new Actions(driver);

		while (description.size() == 0) {
			footer = driver.findElement(By.cssSelector("footer"));

			action.moveToElement(footer).perform();
			presentationSleep(2);
			description = driver.findElements(By.cssSelector(".description"));
		}

		expectedNumberOfGridItems = Integer.parseInt(driver
				.findElement(By.cssSelector("app-remaining-count p"))
				.getText()
				.split(" ")[0]);

		actualNumberOfGridItems = driver.findElements(By.cssSelector(".list li")).size();

		Assert.assertEquals(actualNumberOfGridItems, expectedNumberOfGridItems);
	}
}
