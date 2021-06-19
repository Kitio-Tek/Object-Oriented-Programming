package it.polito.oop.vaccination;

import java.util.ArrayList;
import java.util.List;

public class Hub {

	private String name;
	private Staff staff;
	private List<Integer> hourSet=new ArrayList<>();
	private List<Integer> DailyAvailability=new ArrayList<>();

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

	public void SetHours(List<Integer> hourSet) {
		// TODO Auto-generated method stub
		 this.hourSet=hourSet;
	}

	public List<Integer> getHourSet() {
		return hourSet;
	}
	public List<Integer> getDailyAvailability()
	{ for(int hour:hourSet)
		DailyAvailability.add(hour*staff.getCapacity());
	
	 return DailyAvailability;
	}
   
}
