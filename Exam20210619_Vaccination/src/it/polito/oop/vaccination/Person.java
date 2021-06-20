package it.polito.oop.vaccination;
import java.io.IOException;import java.io.BufferedReader;
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
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.*;
import java.util.ArrayList;
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


public class Person {

	private String first;
	private String last;
	private String ssn;
	private int y;
	public final static int CURRENT_YEAR = java.time.LocalDate.now().getYear();
	private boolean allocate=false;

	public Person(String first, String last, String ssn, int y) {
		// TODO Auto-generated constructor stub
		this.first=first;
		this.last=last;
		this.ssn=ssn;
		this.y=y;
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

	public int getY() {
		return y;
	}
	public int getAge() {
		return  CURRENT_YEAR-y;
	}
   public boolean isAllocated() {
	   return allocate;
   }
   public void SetAllocate() {
	   allocate=true;
   }
   public void clearAllocation() {
	   allocate=false;
   }
}
