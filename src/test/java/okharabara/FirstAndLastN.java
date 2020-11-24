package okharabara;

public class FirstAndLastN {
    public int getSwappedFirstAndLastDigit(int n) {
        if (isNaturalDigit(n)) {
            String[] nToStringArray = String.valueOf(n).split("");
            if (nToStringArray.length < 2) {
                return n;
            }
            String temp = nToStringArray[0];
            nToStringArray[0] = nToStringArray[nToStringArray.length - 1];
            nToStringArray[nToStringArray.length - 1] = temp;
            return convertResultToInteger(nToStringArray);
        } else {
            throw new ArithmeticException("N must be natural digit");
        }
    }

    public boolean isNaturalDigit(int n) {
        return n > 0;
    }

    public int convertResultToInteger(String[] array) {
        StringBuilder result = new StringBuilder();
        for (String s : array) {
            result.append(s);
        }
        return Integer.parseInt(new String(result));
    }
}
