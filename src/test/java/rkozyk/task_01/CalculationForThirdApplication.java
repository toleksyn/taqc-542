package rkozyk.task_01;

import java.util.ArrayList;

import java.util.List;

/*
 * This class takes two numbers and finds amicable pairs between them
 */

public class CalculationForThirdApplication {
    private AmicablePair amicablePair;
    private List<AmicablePair> numbers;
    private int firstAmicableNumber;
    private int secondAmicableNumber;

    public CalculationForThirdApplication() {
        numbers = new ArrayList<>();
    }

    public List<AmicablePair> getAmicablePairList(int firstNumber, int secondNumber) {
        int firstSum;
        int secondSum;
        arrange(firstNumber, secondNumber);
        for (int i = firstAmicableNumber; i <= secondAmicableNumber; i++) {
            firstSum = getSum(getDivisors(i));
            secondSum = getSum(getDivisors(firstSum));
            if (isAmicablePair(i, firstSum, secondSum)) {
                createAmicablePair(i, firstSum);
            }
        }
        return numbers;
    }

    public List<Integer> getDivisors(int number) {
        List<Integer> deviders = new ArrayList<>();
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                deviders.add(i);
            }
        }
        return deviders;
    }

    public int getSum(List<Integer> deviders) {
        int sum = 0;
        for (int num : deviders) sum += num;
        return sum;
    }

    public void arrange(int firstNumber, int secondNumber) {
        if (firstNumber > secondNumber) {
            int temporaryInteger = firstNumber;
            firstNumber = secondNumber;
            secondNumber = temporaryInteger;
        }
        firstAmicableNumber = firstNumber;
        secondAmicableNumber = secondNumber;
    }

    public boolean isAmicablePair(int i, int firstSum, int secondSum) {
        if (secondSum == i && i < firstSum && firstSum <= secondAmicableNumber) {
            return true;
        }
        return false;
    }

    public void createAmicablePair(int firstNumber, int secondNumber) {
        amicablePair = new AmicablePair(firstNumber, secondNumber);
        numbers.add(amicablePair);
    }
}
