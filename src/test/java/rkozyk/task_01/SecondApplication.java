package rkozyk.task_01;

/*
 * This program finds all natural numbers less than a given number and
 * relatively prime to it
 */

public class SecondApplication {

    public static void main(String[] args) {
        InteractionWithConsole interactionWithConsole = new InteractionWithConsole();
        CalculationForSecondApplication calculation = new CalculationForSecondApplication();
        UserFriendlyOutputInConsole userFriendlyOutputInConsole = new UserFriendlyOutputInConsole();
        interactionWithConsole.writeString(userFriendlyOutputInConsole.getRelativelyPrimeNumbers(
                calculation.getRelativelyPrimeNumbersList(interactionWithConsole.readInteger(
                        userFriendlyOutputInConsole.getInstructions()))));
        interactionWithConsole.closeScanner();
    }
}
