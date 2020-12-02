package com.softserve.edu.task_01;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class FirstTaskTest {
    private static FirstTask task = null;

    private void verifyMaximumPowerOfANumberWithPositiveValues(int expected, int actual) {
        task = new FirstTask();
        Assert.assertEquals(expected, task.getMaximumPowerOfANumber(actual));
    }

    @Test
    public void verifyMaximumPowerOfANumberWithFirstDataSetAndPositiveValues() {
        verifyMaximumPowerOfANumberWithPositiveValues(3, 100);
    }

    @Test
    public void verifyMaximumPowerOfANumberWithSecondDataSetAndPositiveValues() {
        verifyMaximumPowerOfANumberWithPositiveValues(4, 1000);
    }

    @Test
    public void verifyMaximumPowerOfANumberWithThirdDataSetAndPositiveValues() {
        verifyMaximumPowerOfANumberWithPositiveValues(9, 1000000);
    }

    private void verifyMaximumPowerOfANumberWithNegativeValues(int expected) {
        task = new FirstTask();
        Assert.assertEquals(0, task.getMaximumPowerOfANumber(expected));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void verifyMaximumPowerOfANumberWithFirstDataSetAndNegativeValues() {
        verifyMaximumPowerOfANumberWithNegativeValues(0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void verifyMaximumPowerOfANumberWithSecondDataSetAndNegativeValues() {
        verifyMaximumPowerOfANumberWithNegativeValues(-123);
    }
}
