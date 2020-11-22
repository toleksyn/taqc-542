package okharabara;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class FirstAndLastNTest {
	private static FirstAndLastN firstAndLastN;
	@BeforeClass
	public static void setUpBeforeClass() {
		firstAndLastN = new FirstAndLastN();
	}
	
	@DataProvider
	public Object[][] ValidDataProvider() {
		return new Object[][] {
			{5, 5},
			{25, 52},
			{489, 984},
			{1589, 9581}
		};
				
	}
	
	@Test(dataProvider = "ValidDataProvider")
	public void addOnesPositiveTest(int n, int expected) {
		int actual = firstAndLastN.firstAndLastN(n);
		Assert.assertEquals(actual, expected);
	}
	
	@DataProvider
	public Object[][] InvalidDataProvider() {
		return new Object[][] {
			{0}, 
			{-5}
		};
	}
	
	@Test(dataProvider = "InvalidDataProvider", expectedExceptions = IllegalArgumentException.class)
	public void addOnesNegativeTest(int n) {
		firstAndLastN.firstAndLastN(n);	
	}
}
