package it.polito.oop.vaccination;

public class Staff {

	private Hub hub;
	private int countDoctors;
	private int nNurses;
	private int other;

	public Staff(Hub hub, int countDoctors, int nNurses, int other) {
		// TODO Auto-generated constructor stub
		this.hub=hub;
		this.countDoctors=countDoctors;
		this.nNurses=nNurses;
		this.other=other;
	}

	public Hub getHub() {
		return hub;
	}

	public int getCountDoctors() {
		return countDoctors;
	}

	public int getnNurses() {
		return nNurses;
	}

	public int getOther() {
		return other;
	}
	public int getCapacity() {
		int c=Math.min(10*countDoctors,12*nNurses);
		int b=Math.min(c, 20*other);
		
		return b;
	}

}
