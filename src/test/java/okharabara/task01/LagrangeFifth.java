package okharabara.task01;

public class LagrangeFifth {
	private int x;
	private int y;
	private int z;
	private int t;
	
	public LagrangeFifth(int x, int y, int z, int t) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.t = t;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getT() {
		return t;
	}
	
	 @Override
	   public boolean equals(Object object) {
	      if (this == object) return true;
	      if (object == null || getClass() != object.getClass()) return false;
	      LagrangeFifth lagrangeFifth = (LagrangeFifth) object;
	      return x == lagrangeFifth.x
	              && y == lagrangeFifth.y
	              && z == lagrangeFifth.z
	              && t == lagrangeFifth.t;
	   }
	 
      @Override
	   public int hashCode() {
	      final int number = 11;
	      int result = 7;
	      result = number * result + x;
	      result = number * result + y;
	      result = number * result + z;
	      result = number * result + t;
	      return result;
	   }

	   @Override
	   public String toString() {
	      return x + " " + y + " " + z + " " + t + " " + "\n";
	   }
}
