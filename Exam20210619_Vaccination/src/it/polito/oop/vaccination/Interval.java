package it.polito.oop.vaccination;

public class Interval {

	private int upper;
	private int lower;

	public Interval(int i, int j) {
		// TODO Auto-generated constructor stub
		lower=i;
	    upper = j;
	}
	public boolean Found(int i)
	{ if(lower<=i && upper>i )
		return true;
	 return false;
	}
   public String toString() {
	 if(upper==1000)
		 return  "["+lower+",+)"; 

	   return  "["+lower+","+upper+")"; 
   }
  public int getLower() {
	return lower;
}
   
}
