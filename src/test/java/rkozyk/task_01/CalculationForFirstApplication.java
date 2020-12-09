package rkozyk.task_01;

import java.util.ArrayList;
import java.util.List;

/*
 * This class takes string of numbers and returns amount and sum of those
 * numbers of a given string that are divisible by 5 and not divisible by 7
 */

public class CalculationForFirstApplication {
    private List<Integer> numbers;

    public CalculationForFirstApplication() {
        numbers = new ArrayList<>();
    }

    public List<Integer> getIntegerList(String string) {
        char[] charArray = string.toCharArray();
        String number = "";
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] >= '0' && charArray[i] <= '9') {
                number += charArray[i];
            } else {
                if (number.length() != 0) {
                    numbers.add(Integer.parseInt(number));
                    number = "";
                }
            }
            if (i == charArray.length - 1 && number.length() != 0) {
                numbers.add(Integer.parseInt(number));
            }
        }
        return numbers;
    }

    public int getSum(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            if (number % 5 == 0 && number % 7 != 0) {
                sum += number;
            }
        }
        return sum;
    }

    public int getAmount(List<Integer> numbers) {
        int amount = 0;
        for (int number : numbers) {
            if (number % 5 == 0 && number % 7 != 0) {
                amount++;
            }
        }
        return amount;
    }

    public List<Integer> getIntegerList() {
        return numbers;
    }
}
