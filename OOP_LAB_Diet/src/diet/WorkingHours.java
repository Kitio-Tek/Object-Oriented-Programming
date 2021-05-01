package diet;

public class WorkingHours {
	private String open,close;

	public WorkingHours(String open, String close) {
		
		this.open = open;
		this.close = close;
	}

	public String getOpen() {
		return open;
	}

	public String getClose() {
		return close;
	}

	public boolean includes(String tmp) {
		// TODO Auto-generated method stub
		if(open.compareTo(tmp)<0 && close.compareTo(tmp)>0 )
		return true;
		
		return false;
	}
	
}
