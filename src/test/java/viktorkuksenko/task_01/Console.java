package viktorkuksenko.task_01;

import java.util.List;
import java.util.Scanner;

public class Console {
    private Scanner scanner = null;

    public Console() {
        scanner = new Scanner(System.in);
    }

    public int read() {
        System.out.println("Enter number: ");
        return scanner.nextInt();
    }

    public void write(int result) {
        System.out.println(result);
    }

    public void write(SumOfSquares sumOfSquares) {
        System.out.println(sumOfSquares.toString());
    }

    public void write(List<SumOfSquares> list) {
        for (SumOfSquares sumOfSquares : list) {
            System.out.println(sumOfSquares.toString());
        }
    }

    public void close() {
        scanner.close();
    }
}
