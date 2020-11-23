package ihoryanya.conditions;

public class ArraySequence {
    private int[] array;
    public ArraySequence(int n) {
        array = new int[n];
    }
    //generation of a random array
    public void randomizedArrayGeneration() {
        for (int i = 0; i < array.length; i++)
            array[i] = (int) Math.round((Math.random() * 31) + 1);
    }

    //for Tests
    public void setData(int...arr) {
        this.array = arr;
    }
    //method for printing elements of array
    public void printArray() {
        for (int e : array) {
            System.out.println(e);
        }
    }

    public int executeTaskG() {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            int currentElement = array[i];
            if (i - 1 < 0 || i + 1 >= array.length) {
                continue;
            }
            if (currentElement < (array[i - 1] + array[i + 1]) / 2) {
                count++;
            }
        }
        return count;
    }

    public int executeTaskD() {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            int currentElement = array[i];
            int k = i + 1;
            if (Math.pow(2, k) < currentElement && currentElement < MathUtils.getFactorial(k)) {
                count++;
            }
        }
        return count;
    }
}
