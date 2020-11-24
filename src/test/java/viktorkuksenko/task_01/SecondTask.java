package viktorkuksenko.task_01;

import java.util.ArrayList;
import java.util.List;

/*
*
* A natural number n is given. Can you imagine it in
as the sum of two squares of natural numbers? If possible:
a) Find a pair x, y of natural numbers which satisfies the condition n = x^2 + y^2.
b) Find all pairs x, y of natural numbers which satisfies the condition n = x^2 + y^2, x >= y.
 */
public class SecondTask {

    public SumOfSquares getOnePairXAndYWhichSatisfiesTheCondition(int n) {
        int x = 0;
        int y = 0;
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i * i + j * j == n) {
                        x = i;
                        y = j;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Number must be > 0");
        }
        return new SumOfSquares(x, y, n);
    }

    public List<SumOfSquares> getAllPairsXAndYWhichSatisfiesTheCondition(int n) {
        List<SumOfSquares> listOfAllPairs = new ArrayList<>();
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i * i + j * j == n && i >= j) {
                        listOfAllPairs.add(new SumOfSquares(i, j, n));
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Number must be > 0");
        }
        if (listOfAllPairs.size() == 0) {
            listOfAllPairs.add(new SumOfSquares(0, 0, n));
        }
        return listOfAllPairs;
    }
}
