package it.polito.oop.vaccination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Hub {

	private String name;
	private Staff staff;
	private List<Integer> hourSet=new ArrayList<>();
	private List<Integer> DailyAvailability=new ArrayList<>();
	private HashMap<Integer,List<Person>> WeekAllocate=new HashMap<>();

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

	public void setDailyAllocate(int d, List<Person> result) {
		// TODO Auto-generated method stub
		WeekAllocate.put(d,result);
		
	}
	public List<String> getPersonperDay(int d){
		return WeekAllocate.get(d).stream()
				.filter((Person p)->p.isAllocated())
				.map(Person::getSsn)
				.collect(Collectors.toList());
	}
   
}
