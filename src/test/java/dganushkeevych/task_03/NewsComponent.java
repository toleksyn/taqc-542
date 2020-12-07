package dganushkeevych.task_03;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewsComponent {

    private WebDriver driver;

    private List<WebElement> newsItems;
    private List<WebElement> lables;

    public NewsComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        newsItems = driver.findElements(By.className("list-gallery"));
        lables = driver.findElements(By.cssSelector(".ul-eco-buttons.ng-star-inserted"));
    }

    public List<WebElement> getNewsItems() {
        return newsItems;
    }

    public List<WebElement> getAllLabels() {
        return lables;
    }

    public List<WebElement> getLabelsForNewsItem(WebElement newsItem) {
        return newsItem.findElements(By.cssSelector(".ul-eco-buttons.ng-star-inserted"));
    }


}
