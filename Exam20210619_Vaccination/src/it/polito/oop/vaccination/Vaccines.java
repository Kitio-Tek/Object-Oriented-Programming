package it.polito.oop.vaccination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.BiConsumer;
import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Vaccines {

    public final static int CURRENT_YEAR = java.time.LocalDate.now().getYear();
    private Map<String,Person> person=new HashMap<>(); private BiConsumer<Integer,String> l=null;
    private Map<String,Hub> hub=new HashMap<>();
    private Map<Interval,List<Person>> interval=new HashMap<>();
    private List<Interval> inter=new ArrayList<>();
    private List<Integer> hourSet=new ArrayList<>();
    private List<Integer> day=new ArrayList<>();
    
    // R1
    /**
     * Add a new person to the vaccination system.
     *
     * Persons are uniquely identified by SSN (italian "codice fiscale")
     *
     * @param first first name
     * @param last last name
     * @param ssn italian "codice fiscale"
     * @param y birth year
     * @return {@code false} if ssn is duplicate,
     */
    public boolean addPerson(String first, String last, String ssn, int y) {
        if(person.containsKey(ssn))
        	return false;
    	
        Person p=new Person(first, last,ssn, y);
    	person.put(ssn, p);
    	return true;
    }

    /**
     * Count the number of people added to the system
     *
     * @return person count
     */
    public int countPeople() {
        return person.size();
    }

    /**
     * Retrieves information about a person.
     * Information is formatted as ssn, last name, and first name
     * separate by {@code ','} (comma).
     *
     * @param ssn "codice fiscale" of person searched
     * @return info about the person
     */
    public String getPerson(String ssn) {
        Person p=person.get(ssn);
    	return p.getSsn()+","+p.getLast()+","+p.getFirst();
    }

    /**
     * Retrieves of a person given their SSN (codice fiscale).
     *
     * @param ssn "codice fiscale" of person searched
     * @return age of person (in years)
     */
    public int getAge(String ssn) {
        return person.get(ssn).getAge();
    }

    /**
     * Define the age intervals by providing the breaks between intervals.
     * The first interval always start at 0 (non included in the breaks)
     * and the last interval goes until infinity (not included in the breaks).
     * All intervals are closed on the lower boundary and open at the upper one.
     * <p>
     * For instance {@code setAgeIntervals(40,50,60)}
     * defines four intervals {@code "[0,40)", "[40,50)", "[50,60)", "[60,+)"}.
     *
     * @param brk the array of breaks
     */
    public void setAgeIntervals(int... brk) { inter.add(new Interval(0,brk[0])); 
    for(int i=0;i<brk.length;i++)
    {  if(i==brk.length-1) {
    	inter.add(new Interval(brk[i],10000));
    	continue;
    	}
      else
    	inter.add(new Interval(brk[i],brk[i+1]));}
    	
        for(Interval i:inter) {
    		interval.put(i, person.values().stream()
    				.filter(e->i.Found(e.getAge()))
    				.collect(toList()));
    		person.values().stream()
			.filter(e->i.Found(e.getAge()))
			.forEach(e->e.SetInterval(i));

    	}
    	
    	
    }

    /**
     * Retrieves the labels of the age intervals defined.
     *
     * Interval labels are formatted as {@code "[0,10)"},
     * if the upper limit is infinity {@code '+'} is used
     * instead of the number.
     *
     * @return labels of the age intervals
     */
    public Collection<String> getAgeIntervals() {
     return	 inter.stream().map(Interval::toString).collect(toList());
   	
    	
       
    }

    /**
     * Retrieves people in the given interval.
     *
     * The age of the person is computed by subtracting
     * the birth year from current year.
     *
     * @param intv age interval label
     * @return collection of SSN of person in the age interval
     */
    public Collection<String> getInInterval(String intv) {
        List<String> s=new ArrayList<>();
    	 s=interval.entrySet().stream()
        		.filter(e->e.getKey().toString().equals(intv))
        		.flatMap(e->e.getValue().stream())
        		.map(Person::getSsn)
        		.collect(toList()); return s;
    }

    // R2
    /**
     * Define a vaccination hub
     *
     * @param name name of the hub
     * @throws VaccineException in case of duplicate name
     */
    public void defineHub(String name) throws VaccineException {
       if(hub.containsKey(name))
    	   throw new VaccineException();
       hub.put(name, new Hub(name));
    
    }

    
    /**
     * Retrieves hub names
     *
     * @return hub names
     */
    public Collection<String> getHubs() {
        return hub.values().stream()
        		.map(Hub::getName)
        		.collect(toList());
    }

    /**
     * Define the staffing of a hub in terms of
     * doctors, nurses and other personnel.
     *
     * @param name name of the hub
     * @param countDoctors number of doctors
     * @param nNurses number of nurses
     * @param other number of other personnel
     * @throws VaccineException in case of undefined hub, or any number of personnel not greater than 0.
     */
    public void setStaff(String name, int countDoctors, int nNurses, int other) throws VaccineException {
    	if(!hub.containsKey(name) || countDoctors<=0)
     	   throw new VaccineException();
    	Staff s=new Staff(hub.get(name),  countDoctors,  nNurses,  other);
    	
    	hub.get(name).setStaff(s);
    }

    /**
     * Estimates the hourly vaccination capacity of a hub
     *
     * The capacity is computed as the minimum among
     * 10*number_doctor, 12*number_nurses, 20*number_other
     *
     * @param hubName name of the hub
     * @return hourly vaccination capacity
     * @throws VaccineException in case of undefined or hub without staff
     */
    public int estimateHourlyCapacity(String hubName) throws VaccineException {
       if(!hub.containsKey(hubName)|| hub.get(hubName).getStaff()==null)
    	   throw new VaccineException();
    	   
    	return hub.get(hubName).getStaff().getCapacity();
    }

    // R3
    /**
     * Load people information stored in CSV format.
     *
     * The header must start with {@code "SSN,LAST,FIRST"}.
     * All lines must have at least three elements.
     *
     * In case of error in a person line the line is skipped.
     *
     * @param people {@code Reader} for the CSV content
     * @return number of correctly added people
     * @throws IOException in case of IO error
     * @throws VaccineException in case of error in the header
     */
    public long loadPeople(Reader people) throws IOException, VaccineException {
        // Hint:
        BufferedReader br = new BufferedReader(people);
        
        
		int n = 0,c=1;
		String line;  
		
		
		 try {
				while((line=br.readLine()) != null)
			  { String[] s=line.split(","); 
			    if(n==0) {
			    	if(!line.equals("SSN,LAST,FIRST,YEAR"))
			    		{if(l!=null) l.accept(c, line);               throw new VaccineException();}
			    	c++;n++; continue;
			    }
			    if(person.containsKey(s[0]) || s.length!=4   )
			    {if(l!=null) l.accept(c, line); 	}
			    
			    
			    if(s.length==4) {
                     this.addPerson(s[2],s[1],s[0],Integer.parseInt(s[3]));
                     n++;
                     }
					  
				c++;	  
				  
				 
					
				  
				}}
        
        catch(IOException e) { throw e;}
        
        
       
        
        
        
        
        
        br.close();
        return n;
    }

    // R4
    /**
     * Define the amount of working hours for the days of the week.
     *
     * Exactly 7 elements are expected, where the first one correspond to Monday.
     *
     * @param hours workings hours for the 7 days.
     * @throws VaccineException if there are not exactly 7 elements or if the sum of all hours is less than 0 ore greater than 24*7.
     */
    public void setHours(int... hours) throws VaccineException {
     if(hours.length!=7 )
       throw new VaccineException();
     for(int i:hours)
     { if(i>12)
    	 throw new VaccineException();
       else
    	   hourSet.add(i);
     
     }
     
    
     hub.values().stream()
                 .forEach(p->p.SetHours(hourSet));
    }

    /**
     * Returns the list of standard time slots for all the days of the week.
     *
     * Time slots start at 9:00 and occur every 15 minutes (4 per hour) and
     * they cover the number of working hours defined through method {@link #setHours}.
     * <p>
     * Times are formatted as {@code "09:00"} with both minuts and hours on two
     * digits filled with leading 0.
     * <p>
     * Returns a list with 7 elements, each with the time slots of the corresponding day of the week.
     *
     * @return the list hours for each day of the week
     */
    public List<List<String>> getHours() {
         List<List<String>> list=new LinkedList<>();
         
         for(int day:hourSet) {
        	 List<String> currList=new LinkedList<>();
        	 list.add(currList);
        	 int hh=9;
        	 while(hh<(day+9)) {
        		 int mm=0;
        		 while(mm<60) {
        			 String hour=String.format("%02d", hh);
        			 String minute=String.format("%02d", mm);
        			 String formatted=hour+ ":"+minute;
        			 currList.add(formatted);
        			 mm+=15;
        			 
        		 }
        		 hh++;
        	 }
        	 
         }
    	
    	
    	return list;
    }

    /**
     * Compute the available vaccination slots for a given hub on a given day of the week
     * <p>
     * The availability is computed as the number of working hours of that day
     * multiplied by the hourly capacity (see {@link #estimateCapacity} of the hub.
     *
     * @return
     */
    public int getDailyAvailable(String hubName, int d) {
        return hub.get(hubName).getHourSet().get(d)*hub.get(hubName).getStaff().getCapacity();
       
    }

    /**
     * Compute the available vaccination slots for each hub and for each day of the week
     * <p>
     * The method returns a map that associates the hub names (keys) to the lists
     * of number of available hours for the 7 days.
     * <p>
     * The availability is computed as the number of working hours of that day
     * multiplied by the capacity (see {@link #estimateCapacity} of the hub.
     *
     * @return
     */
    public Map<String, List<Integer>> getAvailable() {
        return hub.values().stream()
        		.collect(toMap( r->r.getName(),
        				        r->r.getDailyAvailability()
        				
        				
        				));
    }

    /**
     * Computes the general allocation plan a hub on a given day.
     * Starting with the oldest age intervals 40%
     * of available places are allocated
     * to persons in that interval before moving the the next
     * interval and considering the remaining places.
     * <p>
     * The returned value is the list of SSNs (codice fiscale) of the
     * persons allocated to that day
     * <p>
     * <b>N.B.</b> no particular order of allocation is guaranteed
     *
     * @param hubName name of the hub
     * @param d day of week index (0 = Monday)
     * @return the list of daily allocations
     */
    public List<String> allocate(String hubName, int d) {
       List<Interval> AgeInterval=new ArrayList<>();
       AgeInterval=        inter.stream()
    		               .sorted(comparing((Interval e)->e.getLower(),reverseOrder()))
    		               .collect(toList());
    	
    	int n=getDailyAvailable(hubName,d);
    	List<String> res=new ArrayList<>();
    	List<Person> result=new ArrayList<>();
    	
    	for(Interval c:AgeInterval) {
    	List<Person> ThisInterval=person.values().stream().filter((Person p)->!p.isAllocated()).filter(p->c.Found(p.getAge())).limit((int)(n*0.4)).collect(toList());
    	ThisInterval.forEach(Person::SetAllocate);
    	n-=ThisInterval.size();
    	
    	ThisInterval.forEach(p->res.add(p.getSsn()));
    	
    	
    		 
    	 }
    	
    	
    	if(n>0) {
    		 for(Interval c:AgeInterval) {
    		    	List<Person> ThisInterval=person.values().stream().filter((Person p)->!p.isAllocated()).filter(p->c.Found(p.getAge())).limit((int)(n)).collect(toList());
    		    	ThisInterval.forEach(Person::SetAllocate);
    		    	n-=ThisInterval.size();
    		    	
    		    	ThisInterval.forEach(p->res.add(p.getSsn()));
    		    	
    		    	 }
    	}
    	
    	
    	 
    	 hub.get(hubName).setDailyAllocate(d,result);
    	 
    	 return res;
    }

    /**
     * Removes all people from allocation lists and
     * clears their allocation status
     */
    public void clearAllocation() {
     person.values().forEach(Person::clearAllocation);
    
    }

    /**
     * Computes the general allocation plan for the week.
     * For every day, starting with the oldest age intervals
     * 40% available places are allocated
     * to persons in that interval before moving the the next
     * interval and considering the remaining places.
     * <p>
     * The returned value is a list with 7 elements, one
     * for every day of the week, each element is a map that
     * links the name of each hub to the list of SSNs (codice fiscale)
     * of the persons allocated to that day in that hub
     * <p>
     * <b>N.B.</b> no particular order of allocation is guaranteed
     * but the same invocation (after {@link #clearAllocation}) must return the same
     * allocation.
     *
     * @return the list of daily allocations
     */
    public List<Map<String, List<String>>> weekAllocate() {
    	List<Map<String, List<String>>> result=new ArrayList<>();
    	for(int j=0;j<7;j++) day.add(j);
    		
    	
    	for(int i:day) result.
    	               add(hub.values().stream()
    	            		   .collect(toMap(h->h.getName(),h ->allocate(h.getName(),i))));
    	
    	
    	return result;
    }

    // R5
    /**
     * Returns the proportion of allocated people
     * w.r.t. the total number of persons added
     * in the system
     *
     * @return proportion of allocated people
     */
    public double propAllocated() {
   return person.values().stream()
		   .filter(Person::isAllocated)
		   .count()
		   /Double.valueOf(countPeople());  
    	
    	
    }

    /**
     * Returns the proportion of allocated people
     * w.r.t. the total number of persons added
     * in the system, divided by age interval.
     * <p>
     * The map associates the age interval label
     * to the proportion of allocates people in that interval
     *
     * @return proportion of allocated people by age interval
     */
    public Map<String, Double> propAllocatedAge() {
    	  	Map<String,Double> res=new HashMap<>();
    	  
    	  	person.values().stream().filter((Person p)->p.isAllocated()).collect(groupingBy((Person p)->p.getI().toString(),counting()))
    	  	.forEach((key,value)->{ res.put(key, (double)(value/(double)person.values().stream().count()));});
    	  			
    	  			
    	return res;
    }

    /**
     * Retrieves the distribution of allocated persons
     * among the different age intervals.
     * <p>
     * For each age intervals the map reports the
     * proportion of allocated persons in the corresponding
     * interval w.r.t the total number of allocated persons
     *
     * @return
     */
    public Map<String, Double> distributionAllocated() {
    	Map<String,Double> res=new HashMap<>();
  	  
	  	person.values().stream().filter((Person p)->p.isAllocated()).collect(groupingBy((Person p)->p.getI().toString(),counting()))
	  	.forEach((key,value)->{ res.put(key, (double)(value/(double)person.values().stream().filter((Person p)->p.isAllocated()).count()));});
	  			
	  			
	return res;    }

    // R6
    /**
     * Defines a listener for the file loading method.
     * The {@ accept()} method of the listener is called
     * passing the line number and the offending line.
     * <p>
     * Lines start at 1 with the header line.
     *
     * @param lst the listener for load errors
     */
    public void setLoadListener(BiConsumer<Integer, String> lst) {
    	l=lst;
    }
}
