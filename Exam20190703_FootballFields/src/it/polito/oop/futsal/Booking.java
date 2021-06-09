package it.polito.oop.futsal;

public class Booking {
private Pitch pitch;
private Associate associate;
private String time;
public Booking(Pitch pitch, Associate associate, String time) {
	
	this.pitch = pitch;
	this.associate = associate;
	this.time = time;
	associate.setBooking(true);
}
 
}
