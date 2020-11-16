package dganushkeevych;

/*
 *  Pythagorean triple class consists of three positive integers
 *  a, b, and c, such that a2 + b2 = c2
 */


public class PythagoreanTriple {

	private Integer a;
	private Integer b;
	private Integer c;
	
	public PythagoreanTriple() {
		a = 0;
		b = 0;
		c = 0;
	}

	public PythagoreanTriple(int first, int second, int third) {
		a = first;
		b = second;
		c = third;
	}
	
	int getA(){return a;}

	int getB(){return b;}

	int getC(){return c;}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = a * hash + b * hash + c * hash;
		return hash;
	}

	@Override
	public String toString() {
		return "Triple: (" + a + ',' + b + ',' + c + ')' + "\n";
	}
	
	@Override
    public boolean equals(Object obj){
         
        if (!(obj instanceof PythagoreanTriple)) return false;
 
        PythagoreanTriple p = (PythagoreanTriple)obj;
        return this.a.equals(p.a)&&this.b.equals(p.b)&&this.c.equals(p.c);
    }
}
