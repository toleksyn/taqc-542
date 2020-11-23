package okharabara;

/**
 * add ones to the begin and to the end of digit
 * @author ACER
 *
 */
public class AddOne {
	public int addOne(int n) {
		isNaturalDigit(n);
		String[] nToStringArray = String.valueOf(n).split("");
		StringBuilder result = new StringBuilder();
		result.append(1);
		for (String s : nToStringArray) {
			result.append(s);
		}
		result.append(1);
		return Integer.parseInt(new String(result));
	}
	
	public void isNaturalDigit(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Please enter n > 0");
		}
	}
}
