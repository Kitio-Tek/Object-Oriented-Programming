package diet;

public class WorkingHours implements Comparable<WorkingHours>{
	private Time open,close;

	public WorkingHours(String open, String close) {
		String [] open_h_m=open.split(":");
		String [] close_h_m=close.split(":");
		
		this.open = new Time(Integer.parseInt(open_h_m[0]),Integer.parseInt(open_h_m[1]));
		this.close = new Time(Integer.parseInt(close_h_m[0]),Integer.parseInt(close_h_m[1]));
	}

	public Time getOpen() {
		return open;
	}

	public Time getClose() {
		return close;
	}

	public boolean includes(Time tmp) {
		// TODO Auto-generated method stub
		 if(open.compareTo(tmp)<=0 && close.compareTo(tmp)>=0) return true;
		 return false;
	}
   
	@Override
    public String toString() {
    	return open.toString()+":"+close.toString();
    }
	
	@Override
	public int compareTo(WorkingHours o) {
    	return open.compareTo(o.open);
    }

}
