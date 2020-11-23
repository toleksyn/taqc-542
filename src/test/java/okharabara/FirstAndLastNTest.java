package okharabara;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstAndLastNTest {
	private static FirstAndLastN firstAndLastN;

	@DataProvider
	public Object[][] validDataProvider() {
		return new Object[][] {
			{5, 5},
			{25, 52},
			{489, 984},
			{1589, 9581}
		};
				
	}
	
	@Test(dataProvider = "validDataProvider")
	public void addOnesPositiveTest(int n, int expected) {
		firstAndLastN = new FirstAndLastN();
		int actual = firstAndLastN.getSwappedFirstAndLastDigit(n);
		Assert.assertEquals(actual, expected);
	}
}
