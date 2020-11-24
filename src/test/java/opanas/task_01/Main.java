package opanas.task_01;

public class Main {

    public static void main(String[] args) {
        Console con = new Console();
        Calculation calculation = new Calculation();
        con.write("result ", calculation.calculate(con.read()));
        con.close();
    }
}