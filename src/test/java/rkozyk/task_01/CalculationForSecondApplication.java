package rkozyk.task_01;

import java.util.ArrayList;
import java.util.List;

/*
 * This class takes natural number and finds all integers less than a given
 * number and relatively prime to it
 */

public class CalculationForSecondApplication {
    private List<Integer> numbers;

    public CalculationForSecondApplication() {
        numbers = new ArrayList<>();
    }

    public List<Integer> getRelativelyPrimeNumbersList(int number) {
        for (int i = 1; i < number; i++) {
            if (isRelativelyPrimeNumber(getGreatestCommonDivisor(number, i))) {
                numbers.add(i);
            }
        }
        return numbers;
    }

    public int getGreatestCommonDivisor(int firstNumber, int secondNumber) {
        if (secondNumber == 0) {
            return 0;
        }
        int greatestCommonDivisor;
        while (secondNumber != 0) {
            greatestCommonDivisor = firstNumber % secondNumber;
            firstNumber = secondNumber;
            secondNumber = greatestCommonDivisor;
        }
        return firstNumber;
    }

    public boolean isRelativelyPrimeNumber(int number) {
        if (number > 1) return true;
        return false;
    }
}
