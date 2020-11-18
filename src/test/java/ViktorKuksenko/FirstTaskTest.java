package ViktorKuksenko;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class FirstTaskTest {
    private static FirstTask task = null;

    @DataProvider(name = "checkCalculationMaxNumPositive")
    public static Object[][] checkCalculationMaxNumPositive() {
        return new Object[][] {
            {3, 100}, {4, 1000}, {9, 1000000}
        };
    }

    @DataProvider(name = "checkCalculationMaxNumNegative")
    public static Object[][] checkCalculationMaxNumNegative() {
        return new Object[][] {
                {0}, {-123}
        };
    }

    @BeforeClass
    public static void setUpBeforeClass() {
        task = new FirstTask();
    }

    @Test(dataProvider = "checkCalculationMaxNumPositive")
    public void testCalculationMaxNum1(int expected, int actual) {
        Assert.assertEquals(expected, task.calcMaxNum(actual));
    }

    @Test(dataProvider = "checkCalculationMaxNumNegative", expectedExceptions = IllegalArgumentException.class)
    public void testCalculationMaxNum8(int expected) {
        task.calcMaxNum(expected);
    }
}
