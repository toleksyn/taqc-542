package rkozyk.task_01;

import java.util.Scanner;

/*
 * This class provides interaction between user and console
 */

public class InteractionWithConsole {
    private Scanner scanner = null;

    public InteractionWithConsole() {
        scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public String readLine(String s) {
        System.out.print(s);
        return scanner.nextLine();
    }

    public int readInteger() {
        return scanner.nextInt();
    }

    public int readInteger(String s) {
        System.out.print(s);
        return scanner.nextInt();
    }

    public void writeString(String s) {
        System.out.println(s);
    }

    public void closeScanner() {
        scanner.close();
    }
}
