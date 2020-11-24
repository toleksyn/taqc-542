package okharabara;

public class FirstAndLastToOneReplacer {
    public int getAddOne(int n) {
        if (isNaturalDigit(n)) {
            String[] nToStringArray = String.valueOf(n).split("");
            StringBuilder result = new StringBuilder();
            result.append(1);
            for (String s : nToStringArray) {
                result.append(s);
            }
            result.append(1);
            return Integer.parseInt(new String(result));
        } else {
            throw new ArithmeticException("Number must be natural");
        }

    }

    public boolean isNaturalDigit(int n) {
        return n > 0;
    }
}
