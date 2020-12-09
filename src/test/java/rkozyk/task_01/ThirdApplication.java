package rkozyk.task_01;

/*
 * This program finds amicable pairs between two given numbers
 */

public class ThirdApplication {

    public static void main(String[] args) {
        InteractionWithConsole interactionWithConsole = new InteractionWithConsole();
        CalculationForThirdApplication calculation = new CalculationForThirdApplication();
        UserFriendlyOutputInConsole userFriendlyOutputInConsole = new UserFriendlyOutputInConsole();
        interactionWithConsole.writeString(userFriendlyOutputInConsole.getAmicablePair(calculation.getAmicablePairList(
                interactionWithConsole.readInteger(userFriendlyOutputInConsole.getInstructions()),
                interactionWithConsole.readInteger(userFriendlyOutputInConsole.getInstructions()))));
        interactionWithConsole.closeScanner();
    }
}
