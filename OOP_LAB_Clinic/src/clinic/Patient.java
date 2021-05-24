package clinic;

public class Patient {
	private String first,last,SSN;
	private Doctor doctor;

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

	public void assignedDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		this.doctor=doctor;
		}

	public Doctor getDoctor() {
		return doctor;
	}
	
	

}
