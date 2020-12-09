package rkozyk.task_01;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
 * This class is for testing CalculationForSecondTask class with TestNG
 */

public class CalculationForSecondApplicationTestNG {
    private static CalculationForSecondApplication calculation = null;

    @DataProvider
    public Object[][] dataProviderTestGetRelativelyPrimeNumbersList() {
        return new Object[][]{
                {1, Arrays.asList()},
                {13, Arrays.asList()},
                {4, Arrays.asList(2)},
                {49, Arrays.asList(7, 14, 21, 28, 35, 42)},
        };
    }

    @DataProvider
    public Object[][] dataProviderTestGetGreatestCommonDivisor() {
        return new Object[][]{
                {-50, -25, -25},
                {0, 0, 0},
                {100, 30, 10},
                {-20, 75, -5},
                {0, -48, -48},
                {39, 0, 0},
                {-15, 0, 0},
                {0, 35, 35},
                {28, -12, 4},
        };
    }

    @Test(dataProvider = "dataProviderTestGetRelativelyPrimeNumbersList")
    public void testGetRelativelyPrimeNumbersList(int data, List<Integer> expected) {
        calculation = new CalculationForSecondApplication();
        List<Integer> actual;
        actual = calculation.getRelativelyPrimeNumbersList(data);
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataProviderTestGetGreatestCommonDivisor")
    public void testGetGreatestCommonDivisor(int data1, int data2, int expected) {
        calculation = new CalculationForSecondApplication();
        int actual;
        actual = calculation.getGreatestCommonDivisor(data1, data2);
        Assert.assertEquals(expected, actual);
    }
}
