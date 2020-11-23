package okharabara;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class LagrangeTest {

	private static Lagrange lagrange;

	@DataProvider
	public Object[][] validDataProvider() {
		return new Object[][] {
			{5, Arrays.asList(new LagrangeFifth(0,0,1,2))},
			{10, Arrays.asList(new LagrangeFifth(0,0,1,3), new LagrangeFifth(1,1,2,2))},
			{26, Arrays.asList(new LagrangeFifth(0,0,1,5), new LagrangeFifth(0,1,3,4), new LagrangeFifth(2, 2, 3, 3))},
		};
	}
	
	@Test(dataProvider = "validDataProvider")
	public void lagrangePositiveTest (int n, List<LagrangeFifth> expected) {
		lagrange = new Lagrange();
		List<LagrangeFifth> actual = lagrange.getFourSquares(n);
		Assert.assertEquals(actual, expected);
	}
}

