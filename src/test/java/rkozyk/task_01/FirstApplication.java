package rkozyk.task_01;

/*
 * This program finds amount and sum of those members of a given sequence
 * that are divisible by 5 and not divisible by 7.
 */

public class FirstApplication {

    public static void main(String[] args) {
        InteractionWithConsole interactionWithConsole = new InteractionWithConsole();
        CalculationForFirstApplication calculation = new CalculationForFirstApplication();
        UserFriendlyOutputInConsole userFriendlyOutputInConsole = new UserFriendlyOutputInConsole();
        calculation.getIntegerList(interactionWithConsole.readLine(userFriendlyOutputInConsole.getInstructions()));
        interactionWithConsole.writeString(userFriendlyOutputInConsole.getAmountAndSum(
                calculation.getAmount(calculation.getIntegerList()),
                calculation.getSum(calculation.getIntegerList())));
        interactionWithConsole.closeScanner();
    }
}
