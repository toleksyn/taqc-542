package viktorkuksenko;

/*
 * Given an integer m > 1. Get the largest integer k, which satisfies the condition 4^k < m.
 * */

public class FirstTask {

    public int calculateMaximumPowerOfANumber(int m) {
        int num = 1;
        int k = 0;
        if (m > 1) {
            while (num * 4 < m) {
                num *= 4;
                k++;
            }
        } else {
            throw new IllegalArgumentException("Number must be > 0");
        }
        return k;
    }
}
