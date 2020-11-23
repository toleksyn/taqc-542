package panas224;

public class Calculation {
    public int calculate(int n)
    {
        int p=1;//x^0
        int r=0;
        do {
            r++;
            p=(int)Math.pow(2,r);

        }
        while (p<=n);
        return p;
    }
}
