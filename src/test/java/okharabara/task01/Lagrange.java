package okharabara.task01;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class Lagrange {
    public List<LagrangeFifth> getFourSquares(int number) {
        if (isNaturalDigit(number)) {
            List<LagrangeFifth> lagrangeList = new ArrayList<>();
            double squareRootN = Math.sqrt(number);
            for (int i = 0; i < squareRootN; i++) {
                for (int j = 0; j < squareRootN; j++) {
                    for (int k = 0; k < squareRootN; k++) {
                        for (int l = 0; l < squareRootN; l++) {
                            if (isPowSum(i, j, k, l, number) && isUnique(i, j, k, l)) {
                                lagrangeList.add(new LagrangeFifth(i, j, k, l));
                            }
                        }
                    }
                }
            }
            return lagrangeList;
        } else {
            throw new ArithmeticException("Number must be natural");
        }
    }

    public boolean isPowSum(int i, int j, int k, int l, int number) {
        return (int) (pow(i, 2) + pow(j, 2) + pow(k, 2) + pow(l, 2)) == number;
    }

    public boolean isUnique(int i, int j, int k, int l) {
        return i <= j && j <= k && k <= l;
    }

    public boolean isNaturalDigit(int number) {
        return number > 0;
    }
} 