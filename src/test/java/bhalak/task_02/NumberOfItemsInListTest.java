package bhalak.task_02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NumberOfItemsInListTest {
	private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
	private final Long IMPLICITLY_WAIT_SECONDS = 3L;
	private WebDriver driver;

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void verifyNumberOfEcoNewsItemsInList() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(BASE_URL);

		int actualNumberOfListItems = 0;
		int expectedNumberOfListItems = 0;

		driver.findElement(By.xpath("//div[@class = 'navigation-menu-left']//a[contains(@href, '/news')]")).click();

		if (driver.findElement(By.xpath("//span[contains(@class, 'btn-tiles-active')]")).isDisplayed()) {
			driver.findElement(By.xpath("//i[@class ='fa fa-bars']")).click();
		}

		Assert.assertTrue(driver.findElement(By.xpath("//span[@class = 'btn-bars btn-bars-active']")).isDisplayed());

		List<WebElement> description = new ArrayList<WebElement>();
		WebElement footer = null;
		Actions action = new Actions(driver);

		while (description.size() == 0) {
			footer = driver.findElement(By.xpath("//footer"));
			action.moveToElement(footer).perform();
			description = driver.findElements(By.xpath("//div[@class = 'description']"));
		}

		expectedNumberOfListItems = Integer
				.parseInt(driver
								.findElement(By.xpath("//app-remaining-count/p"))
								.getText()
								.split(" ")[0]
				);

		actualNumberOfListItems = driver.findElements(By.xpath("//ul[contains(@class, 'list')]/li")).size();

		Assert.assertEquals(expectedNumberOfListItems, actualNumberOfListItems);
	}
}
