package opanas.task_01;

public class Calculation {

    public int calculateExpression(int n)//2^r>n
    {
        int expessionDegreeZero=1;//2^0
        int degreeOfNumber=0;
        do {
            degreeOfNumber++;
            expessionDegreeZero=(int)Math.pow(2,degreeOfNumber);

        }
        while (expessionDegreeZero<=n);
        return expessionDegreeZero;
    }
}
