package rkozyk.task_01;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
 * This class is for testing CalculationForFirstApplication class with TestNG
 */

public class CalculationForFirstApplicationTestNG {
    private static CalculationForFirstApplication calculationForFirstApplication = null;

    @DataProvider
    public Object[][] dataProviderTestGetIntegerList() {
        return new Object[][]{
                {"", Arrays.asList()},
                {"Hello world!", Arrays.asList()},
                {"10", Arrays.asList(10)},
                {"Hello 10 world!", Arrays.asList(10)},
                {"2 7 40 79", Arrays.asList(2, 7, 40, 79)},
                {"2, 7 Hello 40 world!79", Arrays.asList(2, 7, 40, 79)},

        };
    }

    @DataProvider
    public Object[][] dataProviderTestGetSum() {
        return new Object[][]{
                {Arrays.asList(), 0},
                {Arrays.asList(8), 0},
                {Arrays.asList(35), 0},
                {Arrays.asList(5), 5},
                {Arrays.asList(5, 30, 50), 85},
        };
    }

    @DataProvider
    public Object[][] dataProviderTestGetAmount() {
        return new Object[][]{
                {Arrays.asList(), 0},
                {Arrays.asList(8), 0},
                {Arrays.asList(35), 0},
                {Arrays.asList(5), 1},
                {Arrays.asList(5, 30, 50), 3},
        };
    }

    @Test(dataProvider = "dataProviderTestGetIntegerList")
    public void testGetIntegerList(String data, List<Integer> expected) {
        calculationForFirstApplication = new CalculationForFirstApplication();
        List<Integer> actual;
        actual = calculationForFirstApplication.getIntegerList(data);
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataProviderTestGetSum")
    public void testGetSum(List<Integer> data, int expected) {
        calculationForFirstApplication = new CalculationForFirstApplication();
        int actual;
        actual = calculationForFirstApplication.getSum(data);
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "dataProviderTestGetAmount")
    public void testGetAmount(List<Integer> data, int expected) {
        calculationForFirstApplication = new CalculationForFirstApplication();
        int actual;
        actual = calculationForFirstApplication.getAmount(data);
        Assert.assertEquals(expected, actual);
    }
}
