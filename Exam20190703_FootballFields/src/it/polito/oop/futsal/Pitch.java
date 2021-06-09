package it.polito.oop.futsal;

import java.util.HashMap;
import java.util.Map;

import it.polito.oop.futsal.Fields.Features;

public class Pitch {
private Map<String,Booking> booking=new HashMap<>();
private int id;
private Features feature;
public Pitch(int id, Features feature) {
	
	this.id = id;
	this.feature = feature;
}
public int getId() {
	return id;
}
public Features getFeature() {
	return feature;
}

public boolean booked(String time)
{ return booking.containsKey(time);
	}
public void addBooking(String time, Associate associate) {
	Booking b=new Booking(this,associate,time);
	booking.put(time, b);
	
}
public int getNumberofBooking() {
	// TODO Auto-generated method stub
	return booking.size();
}
public boolean corrispond(Features required) {
	if(required.indoor && !feature.indoor)
		return false;
	if(required.heating && !feature.heating)
		return false;
	if(required.ac && !feature.ac)
		return false;
	return true;
	
}

}
