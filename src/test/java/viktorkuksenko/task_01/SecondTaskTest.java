package com.softserve.edu.task_01;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondTaskTest {
    private static SecondTask task = null;

    private void verifyOnePairXAndYWhichSatisfiesTheConditionWithPositiveValues(SumOfSquares expected, Integer actual) {
        task = new SecondTask();
        Assert.assertEquals(expected, task.getOnePairXAndYWhichSatisfiesTheCondition(actual));
    }

    @Test
    public void verifyOnePairXAndYWhichSatisfiesTheConditionWithFirstDataSetAndPositiveValues() {
        verifyOnePairXAndYWhichSatisfiesTheConditionWithPositiveValues(new SumOfSquares(0, 0, 67), 67);
    }

    @Test
    public void verifyOnePairXAndYWhichSatisfiesTheConditionWithSecondDataSetAndPositiveValues() {
        verifyOnePairXAndYWhichSatisfiesTheConditionWithPositiveValues(new SumOfSquares(21, 1, 442), 442);
    }

    private void verifyOnePairXAndYWhichSatisfiesTheConditionWithNegativeValues(Integer actual) {
        task = new SecondTask();
        Assert.assertEquals(0, task.getOnePairXAndYWhichSatisfiesTheCondition(actual));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void verifyOnePairXAndYWhichSatisfiesTheConditionWithFirstDataSetAndNegativeValues() {
        verifyOnePairXAndYWhichSatisfiesTheConditionWithNegativeValues(0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void verifyOnePairXAndYWhichSatisfiesTheConditionWithSecondDataSetAndNegativeValues() {
        verifyOnePairXAndYWhichSatisfiesTheConditionWithNegativeValues(-122);
    }

    private void verifyAllPairsXAndYWhichSatisfiesTheConditionWithPositiveValues(List<SumOfSquares> expected
            , Integer actual) {
        task = new SecondTask();
        Assert.assertEquals(expected, task.getAllPairsXAndYWhichSatisfiesTheCondition(actual));
    }

    @Test
    public void verifyAllPairsXAndYWhichSatisfiesTheConditionWithFirstDataSetPositiveValues() {
        verifyAllPairsXAndYWhichSatisfiesTheConditionWithPositiveValues(new ArrayList<>(Arrays
                .asList(new SumOfSquares(0, 0, 76))), 76);
    }

    @Test
    public void verifyAllPairsXAndYWhichSatisfiesTheConditionWithSecondDataSetPositiveValues() {
        verifyAllPairsXAndYWhichSatisfiesTheConditionWithPositiveValues(new ArrayList<>(Arrays
                .asList(new SumOfSquares(11, 1, 122))), 122);
    }

    @Test
    public void verifyAllPairsXAndYWhichSatisfiesTheConditionWithThirdDataSetPositiveValues() {
        verifyAllPairsXAndYWhichSatisfiesTheConditionWithPositiveValues(new ArrayList<>(Arrays
                .asList(new SumOfSquares(98, 86, 17000),
                        new SumOfSquares(110, 70, 17000),
                        new SumOfSquares(122, 46, 17000),
                        new SumOfSquares(130, 10, 17000))), 17000);
    }

    private void verifyAllPairsXAndYWhichSatisfiesTheConditionWithNegativeValues(Integer actual) {
        task = new SecondTask();
        Assert.assertEquals(0, task.getAllPairsXAndYWhichSatisfiesTheCondition(actual));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void verifyAllPairsXAndYWhichSatisfiesTheConditionWithFirstDataSetAndNegativeValues() {
        verifyAllPairsXAndYWhichSatisfiesTheConditionWithNegativeValues(0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void verifyAllPairsXAndYWhichSatisfiesTheConditionWithSecondDataSetAndNegativeValues() {
        verifyAllPairsXAndYWhichSatisfiesTheConditionWithNegativeValues(-122);
    }
}
