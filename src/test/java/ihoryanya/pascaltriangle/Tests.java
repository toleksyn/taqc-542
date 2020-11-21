package ihoryanya.pascaltriangle;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class Tests {
    @DataProvider
    public Object[][] getValidData() {
        return new Object[][] {
                {3, 0, new int[]{1}},
                {3, 1, new int[]{1, 1}},
                {3, 2, new int[]{1, 2, 1}}
        };
    }
    @Test(dataProvider = "getValidData")
    public void testPascalTriangle(int height, int pos, int[] data) {
        Integer[][] res = PascalTriangle.calculate(height);
        assertEquals(Utils.convertInt(res[pos]),data);
    }
}
