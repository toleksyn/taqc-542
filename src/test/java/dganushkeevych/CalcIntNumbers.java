package dganushkeevych;

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


public class CalcIntNumbers {

	
	public void isNaturalNum(List<Integer> list) {
		for (Integer i : list) {
			isValid(i);
		}
	}

	public void isValid(int number) {
		if (number <= 0)
			throw new RuntimeException("Not natural number!");
	}

	public int findSquareNumbers(List<Integer> list) {
		isNaturalNum(list);
		int result = 0;
		for (int i = 0; i < list.size(); i++) {
			if (Math.sqrt(list.get(i)) % 2 == 0) {
				result++;
			}
		}
		return result;
	}

	public int findNumbers(List<Integer> a) {
		isNaturalNum(a);
		int result = 0;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) % 3 == 0 && a.get(i) % 5 != 0) {
				result++;
			}
		}
		return result;
	}

	public Set<PythagoreanTriple> findTriples(int limit) {
		isValid(limit);
		Set<PythagoreanTriple> list = new HashSet<>();
		for (int i = 1; i <= limit; i++) {
			for (int j = 1; j <= limit; j++) {
				for (int k = 1; k <= limit; k++) {
					if (Math.pow(i, 2) + Math.pow(j, 2) == Math.pow(k, 2) && i <= j && j <= k) {
						PythagoreanTriple triple = new PythagoreanTriple(i, j, k);
						list.add(triple);
					}
				}
			}
		}
		return list;
	}

}
