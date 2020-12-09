package rkozyk.task_01;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

/*
 * This class is for testing CalculationForThirdTask class with TestNG
 */

public class CalculationForThirdApplicationTestNG {
    private static CalculationForThirdApplication calculation = null;

    @DataProvider
    public Object[][] dataProviderTestGetAmicablePairList() {
        return new Object[][]{
                {50, 250, Arrays.asList()},

                {220, 284, Arrays.asList(new AmicablePair(220, 284))},

                {221, 284, Arrays.asList()},

                {221, 1210, Arrays.asList(new AmicablePair(1184, 1210))},

                {1, 20000, Arrays.asList(new AmicablePair(220, 284),
                        new AmicablePair(1184, 1210), new AmicablePair(2620, 2924),
                        new AmicablePair(5020, 5564), new AmicablePair(6232, 6368),
                        new AmicablePair(10744, 10856), new AmicablePair(12285, 14595),
                        new AmicablePair(17296, 18416))},
        };
    }

    @DataProvider
    public Object[][] dataProviderTestGetDivisors() {
        return new Object[][]{
                {1, Arrays.asList()},
                {7, Arrays.asList(1)},
                {42, Arrays.asList(1, 2, 3, 6, 7, 14, 21)},
        };
    }

    @DataProvider
    public Object[][] dataProvideTestGetSum() {
        return new Object[][]{
                {Arrays.asList(), 0},
                {Arrays.asList(15), 15},
                {Arrays.asList(15, 20, 65), 100},
        };
    }

    @Test(dataProvider = "dataProviderTestGetAmicablePairList")
    public void testGetAmicablePairList(int data1, int data2, List<AmicablePair> expected) {
        calculation = new CalculationForThirdApplication();
        List<AmicablePair> actual;
        actual = calculation.getAmicablePairList(data1, data2);
        AssertJUnit.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataProviderTestGetDivisors")
    public void testGetDivisors(int data, List<Integer> expected) {
        calculation = new CalculationForThirdApplication();
        List<Integer> actual;
        actual = calculation.getDivisors(data);
        AssertJUnit.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataProvideTestGetSum")
    public void testGetSum(List<Integer> data, int expected) {
        calculation = new CalculationForThirdApplication();
        int actual;
        actual = calculation.getSum(data);
        AssertJUnit.assertEquals(expected, actual);
    }
}
