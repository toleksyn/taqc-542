package panas224;


public class Main {

    public static void main(String[] args) {
        Console con = new Console();
        Calc calc = new Calc();
        con.write("result ",calc.calculate(con.read()));
        con.close();
    }
}