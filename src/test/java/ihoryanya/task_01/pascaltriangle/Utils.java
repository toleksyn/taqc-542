package ihoryanya.task_01.pascaltriangle;

//converting [] of Integers to [] int
public class Utils {
    public static int[] convertInt(Integer[] integers) {
        int[] data = new int[integers.length];
        for(int i = 0; i < integers.length; i++) {
            data[i] = integers[i];
        }
        return data;
    }
}
