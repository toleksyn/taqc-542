package viktorkuksenko.task_01;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondTaskTest {
    private static SecondTask task = null;

    @DataProvider(name = "checkSumOfOnePairOfSquares")
    public static Object[][] checkSumOfOnePairOfSquares() {
        return new Object[][]{
                {new SumOfSquares(0, 0, 67), 67},
                {new SumOfSquares(21, 1, 442), 442},
        };
    }

    @DataProvider(name = "checkSumOfAllPairsOfSquares")
    public static Object[][] checkSumOfAllPairsOfSquares() {
        return new Object[][]{
                {new ArrayList<>(Arrays.asList(new SumOfSquares(0, 0, 76))), 76},
                {new ArrayList<>(Arrays.asList(new SumOfSquares(11, 1, 122))), 122},
                {new ArrayList<>(Arrays.asList(new SumOfSquares(98, 86, 17000),
                        new SumOfSquares(110, 70, 17000), new SumOfSquares(122, 46, 17000),
                        new SumOfSquares(130, 10, 17000))), 17000}
        };
    }

    @DataProvider(name = "checkSumOfSquaresNegative")
    public static Object[][] checkSumOfSquaresNegative() {
        return new Object[][]{
                {0},
                {-122}
        };
    }

    @Test(dataProvider = "checkSumOfOnePairOfSquares")
    public void verifyGetOnePairXAndYWhichSatisfiesTheCondition1(SumOfSquares expected, Integer actual) {
        task = new SecondTask();
        Assert.assertEquals(expected, task.getOnePairXAndYWhichSatisfiesTheCondition(actual));
    }

    @Test(dataProvider = "checkSumOfAllPairsOfSquares")
    public void verifyGetAllPairsXAndYWhichSatisfiesTheCondition1(List<SumOfSquares> expected, Integer actual) {
        task = new SecondTask();
        Assert.assertEquals(expected, task.getAllPairsXAndYWhichSatisfiesTheCondition(actual));
    }

    @Test(dataProvider = "checkSumOfSquaresNegative", expectedExceptions = IllegalArgumentException.class)
    public void verifyGetAllPairsXAndYWhichSatisfiesTheConditionNegative(Integer actual) {
        task = new SecondTask();
        Assert.assertEquals(0, task.getAllPairsXAndYWhichSatisfiesTheCondition(actual));
    }

    @Test(dataProvider = "checkSumOfSquaresNegative", expectedExceptions = IllegalArgumentException.class)
    public void verifyGetOnePairXAndYWhichSatisfiesTheConditionNegative(Integer actual) {
        task = new SecondTask();
        Assert.assertEquals(0, task.getOnePairXAndYWhichSatisfiesTheCondition(actual));
    }
}
