package it.polito.oop.futsal;

public class Associate {
private String name,surname,telephone;
private boolean booking=false;

public Associate(String name, String surname, String telephone) {
	this.name = name;
	this.surname = surname;
	this.telephone = telephone;
}

public String getName() {
	return name;
}

public String getSurname() {
	return surname;
}

public String getTelephone() {
	return telephone;
}

public void setBooking(boolean b) {
	// TODO Auto-generated method stub
	booking=b;
}

public boolean isBooking() {
	return booking;
}




}
