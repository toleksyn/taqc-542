package dganushkeevych.task_01;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 1.Working with List of natural numbers.
 * 		a) Determine the number of multiples by three and not multiples by 5.
 * 		b) Determine the number of odd.
 * 2.Find all Pythagorean triples of natural numbers to a given natural number. a² + b² = c² (a<=b<=c<=n)
 *
 */

public class ActionWithIntNumber {
    public boolean isNaturalNumber(List<Integer> list) {
        boolean result = true;
        for (Integer i : list) {
            result = result && isGraterThanZero(i);
        }
        return result;
    }

    public boolean isGraterThanZero(int number) {
        return number > 0;
    }

    public int getSquareNumbers(List<Integer> list) {
        if (isNaturalNumber(list)) {
            int result = 0;
            for (int i = 0; i < list.size(); i++) {
                if (Math.sqrt(list.get(i)) % 2 == 0) {
                    result++;
                }
            }
            return result;
        } else {
            throw new RuntimeException("You try to work with not natural number!");
        }
    }

    public int getSpecialNumbers(List<Integer> list) {
        if (isNaturalNumber(list)) {
            int result = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) % 3 == 0 && list.get(i) % 5 != 0) {
                    result++;
                }
            }
            return result;
        } else {
            throw new RuntimeException("You try to work with not natural number!");
        }
    }

    public Set<PythagoreanTriple> getPynthagoreanTriples(int boundaryValue) {
        if (isGraterThanZero(boundaryValue)) {
            Set<PythagoreanTriple> list = new HashSet<>();
            for (int i = 1; i <= boundaryValue; i++) {
                for (int j = 1; j <= boundaryValue; j++) {
                    for (int k = 1; k <= boundaryValue; k++) {
                        if (Math.pow(i, 2) + Math.pow(j, 2) == Math.pow(k, 2) && i <= j && j <= k) {
                            PythagoreanTriple triple = new PythagoreanTriple(i, j, k);
                            list.add(triple);
                        }
                    }
                }
            }
            return list;
        } else {
            throw new RuntimeException("There are numbers < 0!");
        }
    }

}
