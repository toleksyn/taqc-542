package ihoryanya.conditions.taskgd;

public class Main {
    public static void main (String[] args) {
        ArraySequence arraySequence = new ArraySequence(32);
        arraySequence.randomArray();
        arraySequence.printArray();
        System.out.println("Task G, count=" + arraySequence.executeTaskG());
        System.out.println("Task D, count=" + arraySequence.executeTaskD());
    }
}
