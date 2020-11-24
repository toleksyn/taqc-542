package opanas.task_01;

public class Calculation {

    public int calculate(int n)//2^r>n
    {
        int x0=1;//x^0
        int r=0;
        do {
            r++;
            x0=(int)Math.pow(2,r);

        }
        while (x0<=n);
        return x0;
    }
}
