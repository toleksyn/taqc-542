package viktorkuksenko.task_01;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class FirstTaskTest {
    private static FirstTask task = null;

    @DataProvider(name = "checkCalculationMaxNumPositive")
    public static Object[][] checkCalculateMaximumPowerOfANumberPositive() {
        return new Object[][]{
                {3, 100}, {4, 1000}, {9, 1000000}
        };
    }

    @DataProvider(name = "checkCalculationMaxNumNegative")
    public static Object[][] checkCalculateMaximumPowerOfANumberNegative() {
        return new Object[][]{
                {0}, {-123}
        };
    }

    @Test(dataProvider = "checkCalculationMaxNumPositive")
    public void verifyGetMaximumPowerOfANumberPositive(int expected, int actual) {
        task = new FirstTask();
        Assert.assertEquals(expected, task.getMaximumPowerOfANumber(actual));
    }

    @Test(dataProvider = "checkCalculationMaxNumNegative", expectedExceptions = IllegalArgumentException.class)
    public void verifyGetMaximumPowerOfANumberNegative(int expected) {
        task = new FirstTask();
        Assert.assertEquals(0, task.getMaximumPowerOfANumber(expected));
    }
}
