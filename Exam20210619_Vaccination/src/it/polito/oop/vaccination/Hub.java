package it.polito.oop.vaccination;

public class Hub {

	private String name;
	private Staff staff;

	public Hub(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setStaff(Staff s) {
		// TODO Auto-generated method stub
		staff=s;
	}

	public Staff getStaff() {
		return staff;
	}
   
}
