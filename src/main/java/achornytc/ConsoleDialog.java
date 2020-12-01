package achornytc;

import java.util.Scanner;

public class ConsoleDialog {

    public static String getAnswerFromConsole(String question) {
        System.out.print(question);
        try (Scanner scan = new Scanner(System.in)) {
            return scan.nextLine();
        } catch (Exception exception) {
            return "";
        }
    }
}

