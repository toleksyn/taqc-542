package panas224;

import org.testng.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalcTest {
    private static Calc calc = null;
    @BeforeClass
    public static void setUpBeforeClass() {
        calc = new Calc();
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
        Assert.assertEquals(expected,calc.calculate(origin));
    }
    @DataProvider Object[][] Negative(){
        Object [][] negative={
                {8,3}
        };
        return negative;
    }


}