package it.polito.oop.futsal;

public class Time {
private int hour, minute;

public Time(String time) {
	String[] part=time.split(":");
	this.hour = Integer.parseInt(part[0]);
	this.minute =Integer.parseInt(part[1]) ;
}
@Override
public String toString() {
	return String.format("%02d:%02d", hour,minute);
}
public int getHour() {
	return hour;
}
public int getMinute() {
	return minute;
}
 
}
