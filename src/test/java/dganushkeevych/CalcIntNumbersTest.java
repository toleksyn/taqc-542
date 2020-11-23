package dganushkeevych;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.testng.Assert;

public class CalcIntNumbersTest {

	private static CalcIntNumbers calc = null;

	@DataProvider
	public Object[][] getPositiveSquareNumbersData() {
		return new Object[][] { { 0, 25, 9, 48, 81 }, { 2, 16, 9, 25, 64 }, { 0, 8, 9, 3, 81 }, { 1, 8, 9, 3, 16 },
				{ 4, 16, 4, 100, 36 } };
	}

	@Test(dataProvider = "getPositiveSquareNumbersData")
	public void PositiveTestFindSquareNumbers(int expected, int p1, int p2, int p3, int p4) {
		calc = new CalcIntNumbers();
		int actual;
		List<Integer> list = Arrays.asList(p1, p2, p3, p4);

		actual = calc.findSquareNumbers(list);
		Assert.assertEquals(actual, expected);
	}


	@DataProvider
	public Object[][] getPositiveFindNumbersData() {
		return new Object[][] { { 0, 5, 15, 10, 20 }, { 4, 9, 3, 6, 12 }, { 2, 3, 16, 100, 36 }, { 0, 1, 2, 4, 7 } };
	}


	@Test(dataProvider = "getPositiveFindNumbersData")
	public void PositiveTestFindNumbers(int expected, int p1, int p2, int p3, int p4) {
		calc = new CalcIntNumbers();
		int actual;
		List<Integer> list = Arrays.asList(p1, p2, p3, p4);

		actual = calc.findNumbers(list);
		Assert.assertEquals(actual, expected);
	}


	@DataProvider
	public Object[][] getNegativeFindNumbersData() {
		return new Object[][] { { -2, 16, 8 }, { 0, 25, 9 } };
	}


	@Test(dataProvider = "getNegativeFindNumbersData", expectedExceptions = RuntimeException.class)
	public void NegativeTestFindNumbers(int p1, int p2, int p3) {
		calc = new CalcIntNumbers();
		List<Integer> list = Arrays.asList(p1, p2, p3);
		int actual = calc.findNumbers(list);
		Assert.assertEquals(actual, 0);
	}


	@Test(dataProvider = "getNegativeFindNumbersData", expectedExceptions = RuntimeException.class)
	public void NegativeTestFindSquareNumbers(int p1, int p2, int p3) {
		calc = new CalcIntNumbers();
		List<Integer> list = Arrays.asList(p1, p2, p3);
		int actual = calc.findSquareNumbers(list);
		Assert.assertEquals(actual, 0);
	}

	@DataProvider
	public Object[][] getNegativeFindTriplesData() {
		return new Object[][] {{0}, {-5} };
	}

	@Test(dataProvider = "getNegativeFindTriplesData", expectedExceptions = RuntimeException.class)
	public void NegativeTestFindTriples(int p1) {
		calc = new CalcIntNumbers();
		int actual = (calc.findTriples(p1)).size();
		Assert.assertEquals(actual, 0);
	}

	@DataProvider
	public Object[][] getPositiveFindTriplesData() {
		return new Object[][] {
				{1, Arrays.asList()},
				{5, Arrays.asList(new PythagoreanTriple(3, 4, 5))},
				{10, Arrays.asList(new PythagoreanTriple(6, 8, 10), new PythagoreanTriple(3, 4, 5))},
				{13, Arrays.asList(new PythagoreanTriple(6, 8, 10), new PythagoreanTriple(3, 4, 5), new PythagoreanTriple(5, 12, 13))}
		};
	}

	@Test(dataProvider = "getPositiveFindTriplesData")
	public void PositiveTestFindTriples(int p1, List<PythagoreanTriple> list) {
		calc = new CalcIntNumbers();
		Set<PythagoreanTriple> actual;
		Set<PythagoreanTriple> expected;

		expected = new HashSet<PythagoreanTriple>(list);
		actual = calc.findTriples(p1);
		Assert.assertEquals(actual, expected);
	}
}
