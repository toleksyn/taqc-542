package rkozyk.task_01;

import java.util.List;

/*
 * This class makes user friendly interaction between user and console
 */

public class UserFriendlyOutputInConsole {

    public String getInstructions() {
        return "Enter numbers: ";
    }

    public String getAmountAndSum(int amount, int sum) {
        return "Amount: " + amount + "\nSum: " + sum;
    }

    public String getRelativelyPrimeNumbers(List<Integer> numbers) {
        String result = "";
        for (int number : numbers) {
            result += number + " ";
        }
        return "Relatively prime numbers: " + result;
    }

    public String getAmicablePair(List<AmicablePair> numbers) {
        String result = "";
        for (int i = 0; i < numbers.size(); i++) {
            if (i == numbers.size() - 1) {
                result += numbers.get(i).toString();
            } else {
                result += numbers.get(i).toString() + ", ";
            }
        }
        return "Amicable pairs: " + result;
    }
}
