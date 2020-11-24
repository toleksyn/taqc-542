package dganushkeevych.task_01;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.Assert;

public class ActionWithIntNumberTest {

    private static ActionWithIntNumber calculator = null;

    @DataProvider
    public Object[][] getPositiveSquareNumbersData() {
        return new Object[][]{{0, 25, 9, 48, 81}, {2, 16, 9, 25, 64}, {0, 8, 9, 3, 81}, {1, 8, 9, 3, 16},
                {4, 16, 4, 100, 36}};
    }

    @Test(dataProvider = "getPositiveSquareNumbersData")
    public void positiveFindSquareNumbersTest(int expected, int listElement1, int listElement2, int listElement3, int listElement4) {
        calculator = new ActionWithIntNumber();
        int actual;
        List<Integer> list = Arrays.asList(listElement1, listElement2, listElement3, listElement4);

        actual = calculator.getSquareNumbers(list);
        Assert.assertEquals(actual, expected);
    }


    @DataProvider
    public Object[][] getPositiveSpecialNumbersData() {
        return new Object[][]{{0, 5, 15, 10, 20}, {4, 9, 3, 6, 12}, {2, 3, 16, 100, 36}, {0, 1, 2, 4, 7}};
    }


    @Test(dataProvider = "getPositiveSpecialNumbersData")
    public void positiveFindSpecialNumbersTest(int expected, int listElement1, int listElement2, int listElement3, int listElement4) {
        calculator = new ActionWithIntNumber();
        int actual;
        List<Integer> list = Arrays.asList(listElement1, listElement2, listElement3, listElement4);

        actual = calculator.getSpecialNumbers(list);
        Assert.assertEquals(actual, expected);
    }


    @DataProvider
    public Object[][] getNegativeSpecialNumbersData() {
        return new Object[][]{{-2, 16, 8}, {0, 25, 9}};
    }


    @Test(dataProvider = "getNegativeSpecialNumbersData", expectedExceptions = RuntimeException.class)
    public void negativeTestFindSpecialNumbers(int listElement1, int listElement2, int listElement3) {
        calculator = new ActionWithIntNumber();
        List<Integer> list = Arrays.asList(listElement1, listElement2, listElement3);
        int actual = calculator.getSpecialNumbers(list);
        Assert.assertEquals(actual, 0);
    }


    @Test(dataProvider = "getNegativeSpecialNumbersData", expectedExceptions = RuntimeException.class)
    public void negativeTestFindSquareNumbers(int listElement1, int listElement2, int listElement3) {
        calculator = new ActionWithIntNumber();
        List<Integer> list = Arrays.asList(listElement1, listElement2, listElement3);
        int actual = calculator.getSquareNumbers(list);
        Assert.assertEquals(actual, 0);
    }

    @DataProvider
    public Object[][] getNegativeFindTriplesData() {
        return new Object[][]{{0}, {-5}};
    }

    @Test(dataProvider = "getNegativeFindTriplesData", expectedExceptions = RuntimeException.class)
    public void negativeTestFindTriples(int boundaryValue) {
        calculator = new ActionWithIntNumber();
        int actual = (calculator.getPynthagoreanTriples(boundaryValue)).size();
        Assert.assertEquals(actual, 0);
    }

    @DataProvider
    public Object[][] getPositiveFindTriplesData() {
        return new Object[][]{
                {1, Arrays.asList()},
                {5, Arrays.asList(new PythagoreanTriple(3, 4, 5))},
                {10, Arrays.asList(new PythagoreanTriple(6, 8, 10), new PythagoreanTriple(3, 4, 5))},
                {13, Arrays.asList(new PythagoreanTriple(6, 8, 10), new PythagoreanTriple(3, 4, 5), new PythagoreanTriple(5, 12, 13))}
        };
    }

    @Test(dataProvider = "getPositiveFindTriplesData")
    public void positiveTestFindTriples(int p1, List<PythagoreanTriple> list) {
        calculator = new ActionWithIntNumber();
        Set<PythagoreanTriple> expected = new HashSet<>(list);
        Set<PythagoreanTriple> actual = calculator.getPynthagoreanTriples(p1);
        Assert.assertEquals(actual, expected);
    }
}
