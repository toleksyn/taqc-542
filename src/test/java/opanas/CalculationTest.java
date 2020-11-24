package opanas;

import org.testng.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculationTest {
    private static Calculation calculation = null;
    @BeforeClass
    public static void setUpBeforeClass() {
        calculation = new Calculation();
    }
    @DataProvider(name="Calculating")
    public Object[][] GetData(){
        Object[][] data={
                {8,4},
                {4,3},
        };
        return data;
    }
    @Test(dataProvider = "Calculating")
    public void CalculatingTest(int expected,int origin) {
        Assert.assertEquals(expected, calculation.calculate(origin));
    }
    @DataProvider Object[][] Negative(){
        Object [][] negative={
                {8,3}
        };
        return negative;
    }


}