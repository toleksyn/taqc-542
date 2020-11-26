package okharabara.task01;

public class FirstAndLastToOneReplacer {

    public int getAddOne(int number) {
        if (isNaturalDigit(number)) {
            String[] nToStringArray = String.valueOf(number).split("");
            StringBuilder result = new StringBuilder();
            result.append(1);
            for (String string : nToStringArray) {
                result.append(string);
            }
            result.append(1);
            return Integer.parseInt(new String(result));
        } else {
            throw new ArithmeticException("Number must be natural");
        }

    }

    public boolean isNaturalDigit(int number) {
        return number > 0;
    }
}
