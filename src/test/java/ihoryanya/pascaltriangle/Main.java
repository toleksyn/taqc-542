package ihoryanya.pascaltriangle;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        int num;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row value: ");
        try {
            num = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.out.println("Input can contain only numbers");
            return;
        }
        //%4d instructs the formatter to print the number within 4 spaces.
        //We choose 4 since we know the maximum number of digits in the largest number of a Pascal triangle with 10 rows is 3 digits.
        Integer[][] res = PascalTriangle.calculate(num);
        for (int i = 0; i < res.length; i++ ) {
            System.out.format("%" + (res.length-i) * 2 + "s", "");
            for (int j = 0; j < res[i].length; j++) {
                System.out.format("%4d", res[i][j]);
            }
            System.out.println();
        }
    }
}
