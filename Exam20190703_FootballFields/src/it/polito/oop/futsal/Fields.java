package it.polito.oop.futsal;

import java.util.HashMap;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import java.util.List;
import java.util.Map;
import java.util.*;
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
 * Represents a infrastructure with a set of playgrounds, it allows teams
 * to book, use, and  leave fields.
 *
 */
public class Fields {
    private Map<Integer,Pitch> pitch=new HashMap<>();
    private Map<Integer,Associate> associate=new HashMap<>();
    private Time open;
    private Time close=new Time("00:00");
    private int code;
    public static class Features {
        public final boolean indoor; // otherwise outdoor
        public final boolean heating;
        public final boolean ac;
        public Features(boolean i, boolean h, boolean a) {
            this.indoor=i; this.heating=h; this.ac = a;
        }
    }
    
    public void defineFields(Features... features) throws FutsalException {
        for(Features f:features) {
        	if(!f.indoor && (f.heating || f.ac))
        		throw new FutsalException("Feature non valida");
        	Pitch p=new Pitch(pitch.size()+1,f);
        	pitch.put(pitch.size()+1, p);   
        }
    }
    
    public long countFields() {
        return pitch.size();
    }

    public long countIndoor() {
        return pitch.values().stream()
        		.filter((Pitch p)->p.getFeature().indoor)
        		.count();
    }
    
    public String getOpeningTime() {
        return open.toString();
    }
    
    public void setOpeningTime(String time) {
     open=new Time(time);
    }
    
    public String getClosingTime () {
        return close.toString();
    }
    
    public void setClosingTime(String time) {
     close=new Time(time);
    }

    public int newAssociate(String first, String last, String mobile) {
       Associate a=new Associate(first,last,mobile);
       associate.put(code, a);
    	return code++;
    }
    
    public String getFirst(int partyId) throws FutsalException {
        if(!associate.containsKey(partyId))
        	throw new FutsalException("partyid");
        	
    	return associate.get(partyId).getName();
    }
    
    public String getLast(int associate) throws FutsalException {
    	if(!this.associate.containsKey(associate))
        	throw new FutsalException("associate");
        	
    	return this.associate.get(associate).getName();

    }
    
    public String getPhone(int associate) throws FutsalException {
    	if(!this.associate.containsKey(associate))
        	throw new  FutsalException("partyid");
        	
    	return this.associate.get(associate).getName();
    	
    }
    
    public int countAssociates() {
        return associate.size();
    }
    
    public void bookField(int field, int associate, String time) throws FutsalException {
    String t=time.split(":")[1];
    if(!(Integer.parseInt(t)==open.getMinute()) || !pitch.containsKey(field) || !this.associate.containsKey(associate ))
    	throw new FutsalException();
    
    if(time.compareTo(open.toString())<0 || time.compareTo(close.toString())>=0)
    	throw new FutsalException();
    Pitch p=pitch.get(field);
    p.addBooking(time,this.associate.get(associate));
    
    }

    public boolean isBooked(int field, String time) {
        Pitch p=pitch.get(field);
        if(p==null)
        	return false;
    	return p.booked(time);
    }
    
    public static class Option implements FieldOption{
     public Pitch p;
     public Option (Pitch p) {
    	 this.p=p;
     }
		@Override
		public int getField() {
			// TODO Auto-generated method stub
			return p.getId();
		}

		@Override
		public int getOccupation() {
			// TODO Auto-generated method stub
			return p.getNumberofBooking();
		}
    	
    }
    public int getOccupation(int field) {
    	Pitch p=pitch.get(field);
    	 if(p==null)
         	return 0;
    	
    	return p.getNumberofBooking();
    }
    
    
    public List<FieldOption> findOptions(String time, Features required){
        return pitch.values().stream()
        		.filter(p->!p.booked(time))
        		.filter(p->p.corrispond(required))
        		.map(p->new Option(p))
        		.sorted(comparing(FieldOption::getOccupation,reverseOrder()).thenComparing(FieldOption::getField))
        		.collect(toList());
    }
    
    public long countServedAssociates() {
        return ;
    }
    
    public Map<Integer,Long> fieldTurnover() {
        return null;
    }
    
    public double occupation() {
        return -1.0;
    }
    
}
