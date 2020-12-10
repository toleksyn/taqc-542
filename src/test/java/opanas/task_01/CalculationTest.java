package opanas.task_01;

import org.testng.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculationTest {
    private static Calculation calculation ;
    @DataProvider(name="Positive digits")
    public Object[][] getData(){
        Object[][] data={
                {8,4},
                {4,3},
        };
        return data;
    }
    @Test(dataProvider = "Positive digits")
    public void verifyPositiveDigits(int expected,int origin) {
        Assert.assertEquals(expected, calculation.calculateExpression(origin));
    }

}