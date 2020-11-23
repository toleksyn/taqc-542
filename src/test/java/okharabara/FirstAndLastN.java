package okharabara;

public class FirstAndLastN {
	public int getSwappedFirstAndLastDigit(int n) {
		isNaturalDigit(n);
		String[] nToStringArray = String.valueOf(n).split("");
		if(nToStringArray.length < 2) {
			return n;
		}
		String temp = nToStringArray[0];
		nToStringArray[0] = nToStringArray[nToStringArray.length - 1];
		nToStringArray[nToStringArray.length - 1] = temp;
		return convertResultTInteger(nToStringArray);
	}
	
	public void isNaturalDigit(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Please enter n > 0");
		}
	}
	
	public int convertResultTInteger(String[] array) {
		StringBuilder result = new StringBuilder();
		for(String s : array) {
			result.append(s);
		}
		return Integer.parseInt(new String(result));
	}
}
