package ihoryanya.pascaltriangle;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public static Integer[][] calculate(int num) {
        List<Integer[]> result = new ArrayList<>();
        for (int i = 0; i < num; i++ ) {
            List<Integer> row = new ArrayList<>();
            int val = 1;
            for (int j = 0; j <= i; j++) {
                row.add(val);
                val = val * (i - j) / (j + 1);
            }
            result.add(row.toArray(new Integer[0]));
        }
        return result.toArray(new Integer[0][0]);
    }
}
