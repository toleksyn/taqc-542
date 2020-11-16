package dganushkeevych;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class CalcIntNumbersTest {

	private static CalcIntNumbers calc = null;
	
	@BeforeClass
	public void beforeClass() {
		calc = new CalcIntNumbers();
	}
	
	@DataProvider
	public Object[][] getPositiveSquareNumbersData() {
		return new Object[][] { { 0, 25, 9, 48, 81 }, { 2, 16, 9, 25, 64 }, { 0, 8, 9, 3, 81 }, { 1, 8, 9, 3, 16 },
				{ 4, 16, 4, 100, 36 } };
	}

	@Test(dataProvider = "getPositiveSquareNumbersData")
	public void PositiveTestFindSquareNumbers(int expected, int p1, int p2, int p3, int p4) {
		int actual;
		List<Integer> list = Arrays.asList(p1, p2, p3, p4);

		actual = calc.findSquareNumbers(list);
		Assert.assertEquals(expected, actual);
	}
	

	@DataProvider
	public Object[][] getPositiveFindNumbersData() {
		return new Object[][] { { 0, 5, 15, 10, 20 }, { 4, 9, 3, 6, 12 }, { 2, 3, 16, 100, 36 }, { 0, 1, 2, 4, 7 } };
	}

	
	@Test(dataProvider = "getPositiveFindNumbersData")
	public void PositiveTestFindNumbers(int expected, int p1, int p2, int p3, int p4) {
		int actual;
		List<Integer> list = Arrays.asList(p1, p2, p3, p4);

		actual = calc.findNumbers(list);
		Assert.assertEquals(expected, actual);
	}

	
	@DataProvider
	public Object[][] getNegativeFindNumbersData() {
		return new Object[][] { { -2, 16, 8 }, { 0, 25, 9 } };
	}

	
	@Test(dataProvider = "getNegativeFindNumbersData", expectedExceptions = RuntimeException.class)
	public void NegativeTestFindNumbers(int p1, int p2, int p3) {
		List<Integer> list = Arrays.asList(p1, p2, p3);
		calc.findNumbers(list);
	}
	

	@Test(dataProvider = "getNegativeFindNumbersData", expectedExceptions = RuntimeException.class)
	public void NegativeTestFindSquareNumbers(int p1, int p2, int p3) {
		List<Integer> list = Arrays.asList(p1, p2, p3);
		calc.findSquareNumbers(list);
	}
	
	@DataProvider
	public Object[][] getNegativeFindTriplesData() {
		return new Object[][] {{0}, {-5} };
	}
	
	@Test(dataProvider = "getNegativeFindTriplesData", expectedExceptions = RuntimeException.class)
	public void NegativeTestFindTriples(int p1) {
		calc.findTriples(p1);
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
		Set<PythagoreanTriple> actual;
		Set<PythagoreanTriple> expected;

		expected = new HashSet<PythagoreanTriple>(list);		
		actual = calc.findTriples(p1);
		Assert.assertEquals(expected, actual);

	}
	
	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}
	
}
