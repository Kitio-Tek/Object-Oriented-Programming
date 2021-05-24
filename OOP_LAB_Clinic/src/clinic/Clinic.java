package clinic;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Comparator;
import java.util.OptionalDouble;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {
	private SortedMap<String,Patient> patient=new TreeMap<>();
	private SortedMap<Integer,Doctor> doctor=new TreeMap<>();

	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	public void addPatient(String first, String last, String ssn) {
		// TODO Complete method
		Patient p=new Patient(first,last,ssn);
		patient.put(ssn, p);

	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
		// TODO Complete method
		if(!(patient.containsKey(ssn)))
			throw new NoSuchPatient();
			
		return patient.get(ssn).getLast()+ " " +patient.get(ssn).getFirst()+" ("+patient.get(ssn).getSSN()+")";
	}

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		// TODO Complete method
		Doctor d=new Doctor(first,last, ssn, specialization,docID);
		doctor.put(docID, d);

	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		// TODO Complete method
		if(!(doctor.containsKey(docID)))
			throw new NoSuchDoctor();
		return doctor.get(docID).getLast()+" "+doctor.get(docID).getFirst()+" ("+doctor.get(docID).getSsn()+" )"+" ["+docID+" ]:"+doctor.get(docID).getSpecialization();
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		// TODO Complete method
		if(!(doctor.containsKey(docID)))
			throw new NoSuchDoctor();
		if(!(patient.containsKey(ssn)))
			throw new NoSuchPatient();
		doctor.get(docID).addPatient(patient.get(ssn));
		

	}
	
	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		// TODO Complete method
		if(!(patient.containsKey(ssn)))
			throw new NoSuchPatient();
		Patient p=patient.get(ssn);
		Doctor d=p.getDoctor();
		
		if(d==null)
			throw new NoSuchDoctor();
		
		return d.getDocID();
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSNs
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
		// TODO Complete method
		if(!(doctor.containsKey(id)))
			throw new NoSuchDoctor();
		Doctor d=doctor.get(id);
		
		return d.getAssignedPatients().stream().map((Patient p)->p.getSSN()).collect(Collectors.toList());
		
	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and specialization.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param readed linked to the file to be read
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader) throws IOException {
		// TODO Complete method
		BufferedReader in = new BufferedReader(reader);
		int n = 0;
		String line;
				while((line=in.readLine()) != null)
			  { String[] s=line.split(";");
				  if(s[0]!="P" || s[0]!="M")
					  continue;
				  else if(s[0]=="P" ) {
					  if(s.length!=4 || s[1]==null || s[2]==null ||s[3]==null)
						  continue;
					  this.addPatient(s[1], s[2], s[3]);}
				  else if(s[0]=="M") {
					  if(s.length!=6 || s[1]==null || s[2]==null ||s[3]==null ||s[4]==null ||s[5]==null )
						  continue;
                     this.addDoctor(s[2],s[3],s[4],Integer.parseInt(s[1]),s[5]);}
					
				  n++;
				}
		 return n;		
	}



	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and speciality.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method calls the
	 * {@link ErrorListener#offending} method passing the line itself,
	 * ignores the row, and skip to the next one.<br>

	 * 
	 * @param reader reader linked to the file to be read
	 * @param listener listener used for wrong line notifications
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader, ErrorListener listener) throws IOException {
		// TODO Complete method
		BufferedReader in = new BufferedReader(reader);
		int n = 0;
		String line;
				while((line=in.readLine()) != null)
			  { String[] s=line.split(";");
				  if(s[0]!="P" || s[0]!="M")
					  { listener.offending(line);
					    continue;}
				  else if(s[0]=="P" ) {
					  if(s.length!=4 || s[1]==null || s[2]==null ||s[3]==null)
					  {   listener.offending(line);
						  continue;}
					  this.addPatient(s[1], s[2], s[3]);}
				  
				  else if(s[0]=="M") {
					  if(s.length!=6 || s[1]==null || s[2]==null ||s[3]==null ||s[4]==null ||s[5]==null )
					  {  listener.offending(line);
						  continue;}
                     this.addDoctor(s[2],s[3],s[4],Integer.parseInt(s[1]),s[5]);}
					
				  n++;
				}
		 return n;		
	}

		
	/**
	 * Retrieves the collection of doctors that have no patient at all.
	 * The doctors are returned sorted in alphabetical order
	 * 
	 * @return the collection of doctors' ids
	 */
	public Collection<Integer> idleDoctors(){
		// TODO Complete method
		return doctor.values().stream()
				.filter((Doctor d)->d.getAssignedPatients().size()==0)
				.sorted(Comparator.comparing(Doctor::getLast).thenComparing(Doctor::getFirst))
				.map(Doctor::getDocID)
				.collect(Collectors.toList());
	}

	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors' ids
	 */
	public Collection<Integer> busyDoctors(){
		// TODO Complete method
		OptionalDouble s=doctor.values().stream().mapToInt((Doctor d)->d.getAssignedPatients().size()).average();
		
		
		
		return doctor.values().stream()
				.filter((Doctor d)->OptionalDouble.of(Double.valueOf(d.getAssignedPatients().size()))==s)
				.sorted(Comparator.comparing(Doctor::getLast).thenComparing(Doctor::getFirst))
				.map(Doctor::getDocID)
				.collect(Collectors.toList());
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
		// TODO Complete method
		return doctor.values().stream()
				.sorted(Comparator.comparing((Doctor e)->e.getAssignedPatients().size()).reversed())
				.map((Doctor d)->d.text())
				.collect(Collectors.toList());
	}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  speciality
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic speciality.
	 * 
	 * @return the collection of strings with speciality and patient count information.
	 */
	public Collection<String> countPatientsPerSpecialization(){
		// TODO Complete method
		return null;
	}
	
}
