package clinic;

public class Patient {
	private String first,last,SSN;

	public Patient(String first, String last, String SSN) {
		
		this.first = first;
		this.last = last;
		this.SSN = SSN;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public String getSSN() {
		return SSN;
	}
	
	

}
