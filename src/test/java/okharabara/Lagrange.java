package okharabara;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.pow;

/**
 * Lagrange theorem n = a^2 + b^2 + c^2 + d^2
 * @author ACER
 *
 */
public class Lagrange {
    public List<LagrangeFifth> getFourSquares(int n) {
    	isNaturalDigit(n);
        List<LagrangeFifth> lagrangeList = new ArrayList<>();
        double squareRootN = Math.sqrt(n);
        for (int i =0; i <squareRootN; i++) {
            for (int j = 0; j < squareRootN; j++) {
                for (int k = 0; k < squareRootN; k++) {
                    for (int l = 0; l < squareRootN; l++) {
                        if (isPowSum(i, j, k, l, n) && isUnique(i, j, k, l)) {
                        	lagrangeList.add(new LagrangeFifth(i, j, k, l));
                        }
                    }
                }
            }
        }
        return lagrangeList;
    }
    
    public boolean isPowSum(int i, int j, int k, int l, int n) {
        return (int) (pow(i, 2) + pow(j, 2) + pow(k, 2) + pow(l, 2)) == n;
    }

    public boolean isUnique(int i, int j, int k, int l) {
        return i <= j && j <= k && k <= l;
    }
    
    public void isNaturalDigit(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Please enter n > 0");
		}
	}
} 