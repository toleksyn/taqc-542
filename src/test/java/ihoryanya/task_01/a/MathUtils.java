package ihoryanya.task_01.a;

public class MathUtils {
    public static long getFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Invalid argument number = " + number);
        }
        long factorial = 1;
        while (number > 1) {
            factorial *= number;
            number--;
        }
        return factorial;
    }
}
