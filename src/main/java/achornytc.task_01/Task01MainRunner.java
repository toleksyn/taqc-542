package achornytc.task_01;

import java.util.TreeMap;

import static java.lang.System.*;


public class Task01MainRunner {
    private static NaturalNumber number;

    public Task01MainRunner() {
    }

    public static void main(String[] args) {
        NaturalNumber number = new NaturalNumber(
                ConsoleDialog
                        .getAnswerFromConsole("Input natural number: "));
        out.println("Natural dividers of " + number.getValue() + ":");
        number.getNaturalDividers()
                .stream()
                .sorted()
                .map(x -> String.valueOf(x) + " ")
                .forEach(System.out::print);

        out.println("\nSimple natural dividers of " + number.getValue() + ":");
        number.getSimpleNaturalDividers()
                .stream()
                .sorted()
                .map(x -> String.valueOf(x) + " ")
                .forEach(System.out::print);

        out.println("\nNumbers [1.." + number.getValue() + "], whose record coincides with the last " +
                        "digits of their power 2 :");
        out.println(new TreeMap(number.getPowered2TaleDigitsCoinciders()));
    }
}

