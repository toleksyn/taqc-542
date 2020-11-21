package achornytc;

class Test {
    static int a = 0;
    int b = 4;

    public void foo() {
        while (b > 0) {
            b--;
            a++;
        }
    }
}
// Java Fundamentals - 50/100 - Such A Shame !!! 15 minutes instead of given 50 and a lot of mistakes...

public class Working_On_Mistakes {
    public static void main(String... args) {
        System.out.println("OK"); //q1 "public static void main(String... args)" - other correct answer
                                // public static void int(String[] args) - incorrect (I thought main can return
                                // some values to command line - I was wrong)
        short number = 260;
        //q3
        byte b = (byte)number; //had not considered 0 (zero) while calculating 260-255
        System.out.println(b);
        //q5
        int a = 5;
        System.out.println("a++ + ++a = " + (a++ + ++a)); //order from the left to the right - I had calculated each
                                                    // addition separately, so, I've lost an increment of left addition
        //q7
        boolean result = (a < 10) | ((a = 10) > 5); //haven't notice a single operator | and calculated like ||
        System.out.println(a + ", " + result);

        //q9
        a = 3;
        b = 24;
        System.out.println("(a << 3) + (b >> 3) = " + ((a << 3) + (b >> 3))); //moved binaries in wrong directions
                                                                            // inattention && hurry were my enemies
                                                                            // on this quiz

    // Program Flow - 60/100 - more complicated quiz - CASE is my weakness. So lets analyze mistakes


        //q6
        Test p1 = new Test( );
        p1.foo( );
        Test p2 = new Test( );
        p2.foo( );
        System.out.println(p1.a+" "+p2.a); //q6 - its my inattention - answered as if required output
                                        // would be println(p1.a+" "+p2.b). Found out after quiz

        //q7 - q10 - I will analyze in one block, as the problem is my incomplete knowledge how CASE works

        //------------------q7----------------NO BOOLEAN IN CASE OPERATOR
//        boolean flag = true;
//        switch (flag) {
//            case true : System.out.println("true");
//            default: System.out.println("false");
//        }

        //------------------q8----------------NO BOOLEAN IN CASE OPERATOR - same as previous
//        public static boolean getBool( ) {  return true;  }
//        public static String getString( ) {  return "true";  }
//            public static void main(String args[ ] ) {
//                switch(getBool( )) {
//                    case true : System.out.println("true"); break;
//                    default : System.out.println("none"); break;
//                }
//            }
        //------------------q9---------------My answer was correct - it will not compile, but it was just
                                            // logical guess. Real reason is: NO BOOLEAN IN CASE OPERATOR
//        int x = Integer.parseInt(args[0]);
//        switch(x) {
//            case x < 1 : System.out.println("SMALLER"); break;
//            case x > 1 : System.out.println("BIGGER");
//            default :    System.out.println("EQUAL"); break;
//        }

        //-------------------q10------------------Seems, that after default: statement all case statements will
                                                // be executed till break
        switch ( 10 ) {
            case 4: System.out.println(4);
            case 5: System.out.println(5);
            default:
            case 1: System.out.println(1);
            case 0: System.out.println(0);
            case 2: System.out.println(2); break;
            case 3: System.out.println(3);
        }

    }
}
