package okharabara.task01;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstAndLastNTest {
    private static FirstAndLastN firstAndLastN;

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {5, 5},
                {25, 52},
                {489, 984},
                {1589, 9581}
        };

    }

    @Test(dataProvider = "validDataProvider")
    public void verifyFirstAndLastNPositiveTest(int testNumber, int expected) {
        firstAndLastN = new FirstAndLastN();
        int actual = firstAndLastN.getSwappedFirstAndLastDigit(testNumber);
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
    public void verifyFirstAndLastNNegativeTest(int testNumber) {
        try {
            firstAndLastN = new FirstAndLastN();
            int actual = firstAndLastN.getSwappedFirstAndLastDigit(testNumber);
            Assert.fail();
        } catch (ArithmeticException arithmeticException) {
        }
    }
}
