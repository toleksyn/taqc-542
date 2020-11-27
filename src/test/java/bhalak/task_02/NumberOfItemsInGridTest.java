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

public class NumberOfItemsInGridTest {
	private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
	private final Long IMPLICITLY_WAIT_SECONDS = 3L;
	private WebDriver driver;
	
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
