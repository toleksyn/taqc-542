package okharabara.task01;

public class FirstAndLastN {

    public int getSwappedFirstAndLastDigit(int number) {
        if (isNaturalDigit(number)) {
            String[] nToStringArray = String.valueOf(number).split("");
            if (nToStringArray.length < 2) {
                return number;
            }
            String temp = nToStringArray[0];
            nToStringArray[0] = nToStringArray[nToStringArray.length - 1];
            nToStringArray[nToStringArray.length - 1] = temp;
            return getConvertResultToInteger(nToStringArray);
        } else {
            throw new ArithmeticException("N must be natural digit");
        }
    }

    public boolean isNaturalDigit(int number) {
        return number > 0;
    }

    public int getConvertResultToInteger(String[] array) {
        StringBuilder result = new StringBuilder();
        for (String string : array) {
            result.append(string);
        }
        return Integer.parseInt(new String(result));
    }
}
