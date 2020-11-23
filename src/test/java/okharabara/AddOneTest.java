package okharabara;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddOneTest {
    private static AddOne addOne;

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
    public void addOnesPositiveTest(int n, int expected) {
        addOne = new AddOne();
        int actual = addOne.addOne(n);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] InvalidDataProvider() {
        return new Object[][]{
                {0},
                {-5}
        };
    }
}