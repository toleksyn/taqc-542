package okharabara.task01;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstAndLastToOneReplacerTest {
    private static FirstAndLastToOneReplacer firstAndLastToOneReplacer;

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {5, 151},
                {25, 1251},
                {489, 14891},
                {1589, 115891}
        };

    }

    @Test(dataProvider = "validDataProvider")
    public void verifyFirstAndLastToOneReplacerPositiveTest(int testNumber, int expected) {
        firstAndLastToOneReplacer = new FirstAndLastToOneReplacer();
        int actual = firstAndLastToOneReplacer.getAddOne(testNumber);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                {0},
                {-5},
        };
    }

    @Test(dataProvider = "invalidDataProvider")
    public void verifyFirstAndLastToOneReplacerNegativeTest(int testNumber) {
        try {
            firstAndLastToOneReplacer = new FirstAndLastToOneReplacer();
            int actual = firstAndLastToOneReplacer.getAddOne(testNumber);
            Assert.fail();
        } catch (ArithmeticException arithmeticException) {
        }
    }
}