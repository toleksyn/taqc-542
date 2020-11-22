package okharabara;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class AddOnesTest {
	private static AddOnes addOnes;
	@BeforeClass
	public static void setUpBeforeClass() {
		addOnes = new AddOnes();
	}
	
	@DataProvider
	public Object[][] ValidDataProvider() {
		return new Object[][] {
			{5, 151},
			{25, 1251},
			{489, 14891},
			{1589, 115891}
		};
				
	}
	
	@Test(dataProvider = "ValidDataProvider")
	public void addOnesPositiveTest(int n, int expected) {
		int actual = addOnes.addOnes(n);
		Assert.assertEquals(actual, expected);
	}
}