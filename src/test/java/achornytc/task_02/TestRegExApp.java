package achornytc.task_02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegExApp {
    private final static String TEST_SITE_URL = "https://ita-social-projects.github.io/GreenCityClient/";
    private final static Long IMPLICIT_WAIT = 10L;
    private static WebDriver driver;
    private static Dimension resolution;

    private static List<String> getSubstringsByRegularExpression(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        List<String> resultList = new ArrayList<>();
        while (matcher.find()) {
            resultList.add(matcher.group());
        }
        return resultList;
    }

    private static List<Integer> getAbsoluteDimensionsIntValues(String regex, String text) {
        List<Integer> absolutDimensions = new ArrayList<>();
        for (String string : getSubstringsByRegularExpression(regex, text)) {
            System.out.println(string);
            for (String element : getSubstringsByRegularExpression(NUMBERS_FROM_ROW_REG_EX, string)) {
                System.out.println(element);
                absolutDimensions.add(Integer.valueOf(element));
            }
        }
        return absolutDimensions;
    }

    final static String ABSOLUTE_MARGIN_APPEAR_REGEX = "\\bmargin[-,a-z]*:( )+([0-9,\\.]+(px)*( )*)+;";
    final static String ABSOLUTE_WIDTH_APPEAR_REGEX = "[^-]width[-,a-z]*:( )+([0-9,.]+(px)*( )*)+;";
    final static String NUMBERS_FROM_ROW_REG_EX = "\\b[0-9]+";
    final static Integer MAX_ABSOLUTE_MARGIN_VALUE = 100;
    final static Integer DESKTOP_WINDOW_WIDTH = 1366;
    final static Integer MOBILE_WINDOW_WIDTH = 360;

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(TEST_SITE_URL);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.navigate().to(TEST_SITE_URL);
        driver.manage().window().maximize();
        Object[] absoluteDimensionsList = getAbsoluteDimensionsIntValues
                (ABSOLUTE_MARGIN_APPEAR_REGEX, driver.getPageSource())
                .stream()
                .filter(x -> x > MAX_ABSOLUTE_MARGIN_VALUE)
                .toArray();
        System.out.println("Absolute margins quantity that are > " + MAX_ABSOLUTE_MARGIN_VALUE +" is "
                + absoluteDimensionsList.length);


        driver.navigate().to(TEST_SITE_URL);
        resolution = new Dimension(MOBILE_WINDOW_WIDTH, driver.manage().window().getSize().height);
        driver.manage().window().setSize(resolution);
        absoluteDimensionsList = getAbsoluteDimensionsIntValues
                (ABSOLUTE_WIDTH_APPEAR_REGEX, driver.getPageSource())
                .stream()
                .filter(x -> x > MOBILE_WINDOW_WIDTH)
                .toArray();
        System.out.println("Absolute widths quantity that are > " + MOBILE_WINDOW_WIDTH + " is "
                + absoluteDimensionsList.length);

        driver.close();
    }

}
