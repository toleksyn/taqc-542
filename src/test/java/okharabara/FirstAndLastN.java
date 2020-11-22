package okharabara;

public class FirstAndLastN {
	public int firstAndLastN(int n) {
		isNaturalDigit(n);
		String[] nToStringArray = String.valueOf(n).split("");
		if(nToStringArray.length < 2) {
			return n;
		}
		String temp = nToStringArray[0];
		nToStringArray[0] = nToStringArray[nToStringArray.length - 1];
		nToStringArray[nToStringArray.length - 1] = temp;
		return returnResult(nToStringArray);
	}
	
	public boolean isNaturalDigit(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Please enter n > 0");
		}
		return true;
	}
	
	public int returnResult(String[] array) {
		StringBuilder result = new StringBuilder();
		for(String s : array) {
			result.append(s);
		}
		return Integer.parseInt(new String(result));
	}
}
