package ihoryanya.task_01.a;

public class Main {
    public static void main (String[] args) {
        ArraySequence arraySequence = new ArraySequence(32);
        arraySequence.fillArrayWithRandomNumbers();
        arraySequence.printArray();
        System.out.println("Task G, count=" + arraySequence.executeTaskG());
        System.out.println("Task D, count=" + arraySequence.executeTaskD());
    }
}
