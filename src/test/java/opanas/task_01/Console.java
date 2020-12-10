package opanas.task_01;

import java.util.Scanner;

public class Console {
    private Scanner scanner=null;
    public Console() {
        scanner = new Scanner(System.in);
    }

    public int read() {
        System.out.print("Enter n:");
        return scanner.nextInt();
    }

    public void write(String message, int result) {
        System.out.print(message + result);
    }

    public void close() {
        scanner.close();
    }
}