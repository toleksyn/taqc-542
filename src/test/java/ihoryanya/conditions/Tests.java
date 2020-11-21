package ihoryanya.conditions;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Tests {
    @DataProvider
    public Object[][] getValidDataTaskG() {
        return new Object[][] {
                {1, new int[]{3, 5, 10}},
        };
    }
    @Test(dataProvider = "getValidDataTaskG")
    public void testTaskG(int expected, int[] originNumbers) {
        ArraySequence arraySequence = new ArraySequence(0);
        arraySequence.setData(originNumbers);
        assertEquals(arraySequence.executeTaskG(), expected);
    }
    @DataProvider
    public Object[][] getValidDataTaskD() {
        return new Object[][] {
                {2, new int[]{1, 1, 1, 17, 33}},
        };
    }
    @Test(dataProvider = "getValidDataTaskD")
    public void testTaskD(int expected, int[] originNumbers) {
        ArraySequence arraySequence = new ArraySequence(0);
        arraySequence.setData(originNumbers);
        assertEquals(arraySequence.executeTaskD(), expected);
    }
    @DataProvider
    public Object[][] getValidDataFactorial() {
        return new Object[][]{
                {24, 4},
                {1, 0},
                {1, 1}
        };
    }
    @Test(dataProvider = "getValidDataFactorial")
    public void testFactorial(int expected, int originNumber) {
        assertEquals(MathUtils.getFactorial(originNumber), expected);
    }
}
