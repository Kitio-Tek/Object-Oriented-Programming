package clinic;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
	private String first,last, ssn, specialization;
	private int docID;
	private List<Patient> assignedPatients=new ArrayList<>();
	public Doctor(String first, String last, String ssn, String specialization, int docID) {
		
		this.first = first;
		this.last = last;
		this.ssn = ssn;
		this.specialization = specialization;
		this.docID = docID;
	}
	public String getFirst() {
		return first;
	}
	public String getLast() {
		return last;
	}
	public String getSsn() {
		return ssn;
	}
	public String getSpecialization() {
		return specialization;
	}
	public int getDocID() {
		return docID;
	}
	public void addPatient(Patient patient) {
		patient.assignedDoctor(this);
		assignedPatients.add(patient);
		}
	public List<Patient> getAssignedPatients() {
		return assignedPatients;
	}
	public int numAssignedPatients() {
		return assignedPatients.size();
	}
	public String text() {
		String s=new String();
		s=String.format("%3d : %d %s %s", this.assignedPatients.size(),docID,last,first);
	   
		return s;
	}
 
}
