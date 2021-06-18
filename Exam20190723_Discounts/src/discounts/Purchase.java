package discounts;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
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


public class Purchase {
int cardId;
private Map<Product,Integer> item=new HashMap<>();
	public Purchase(int cardId) {
		// TODO Auto-generated constructor stub
		this.cardId=cardId;
	}
	public Purchase() {
		
	}
	
	public void addItems(Product product, int parseInt) {
		// TODO Auto-generated method stub
		item.put(product,parseInt);
		
	}
	public double getRealPrice() {
		
		return item.entrySet().stream()
		               .collect(summingDouble(e->e.getValue()*e.getKey().getDiscountPrice()));
	} 
	public int getNofUnits() {
		return item.values().stream()
		             .collect(summingInt((Integer e)->e));
	}
	public double getPercentage() {
		return item.entrySet().stream()
				.collect(summingDouble(e->e.getValue()*e.getKey().getPercentage()));
	}

	public Map<Product,Integer> getItem(){
		return item;
	}
}
