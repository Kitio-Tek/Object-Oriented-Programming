package discounts;
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



import java.util.List;
import java.util.Map;

public class Discounts {

	private Map<Integer,String> card=new HashMap<>();
	private Map<String,Product> product=new HashMap<>();
	private Map<Integer,Purchase> purchase=new HashMap<>();
	
	//R1
	public int issueCard(String name) {
	    
		card.put(card.size()+1, name);
		return card.size();
	}
	
    public String cardHolder(int cardN) {
        return card.get(cardN);
    }
    

	public int nOfCards() {
	       return card.size();

	}
	
	//R2
	public void addProduct(String categoryId, String productId, double price) 
			throws DiscountsException {
		if(product.containsKey(productId))
			throw new DiscountsException();
		
		Product p=new Product(categoryId,productId, price);
		product.put(productId, p);
	}
	
	public double getPrice(String productId) 
			throws DiscountsException {
		if(!product.containsKey(productId))
			throw new DiscountsException();
		
		return product.get(productId).getPrice();
        
	}

	public int getAveragePrice(String categoryId) throws DiscountsException {
           double s =   product.values().stream()
        		.filter((Product p)->p.getCategoryId().equals(categoryId))
        		.collect(Collectors.averagingDouble((Product p)->p.getPrice()));
           
           return (int)s;
	
	}
	
	//R3
	public void setDiscount(String categoryId, int percentage) throws DiscountsException {
	    if(!(percentage>=0) || !(percentage<=50))
	    	throw new DiscountsException();
		
		          long c=  product.values().stream()
	                .filter((Product p)->p.getCategoryId().equals(categoryId))
	                .count();
	              
		          if(c==0)
		        	  throw new DiscountsException(); 
	               
		          product.values().stream()
	                .filter((Product p)->p.getCategoryId().equals(categoryId))
		            .forEach((Product p)->p.setPercentage(percentage));
	
	
	
	}

	public int getDiscount(String categoryId) {
         OptionalInt m= product.values().stream()
                .filter((Product p)->p.getCategoryId().equals(categoryId))
                .mapToInt(Product::getPercentage)
                .findFirst();
       
         
     return m.getAsInt();
	}

	//R4
	public int addPurchase(int cardId, String... items) throws DiscountsException {
        Purchase p=new Purchase(cardId);
		
        for(String s:items)
        { String[] str=s.split(":");
          p.addItems(product.get(str[0]),Integer.parseInt(str[1]));
        	
        }
		purchase.put(purchase.size()+1, p);
        
		return	purchase.size() ;
	}

	public int addPurchase(String... items) throws DiscountsException {
		 Purchase p=new Purchase();
			
	        for(String s:items)
	        { String[] str=s.split(":");
	          p.addItems(product.get(str[0]),Integer.parseInt(str[1]));
	        	
	        }
			purchase.put(purchase.size()+1, p);
	        
			return	purchase.size() ;
	}
	
	public double getAmount(int purchaseCode) {
       return purchase.get(purchaseCode).getRealPrice();
	}
	
	public double getDiscount(int purchaseCode)  {
        return purchase.get(purchaseCode).getPercentage();
	}
	
	public int getNofUnits(int purchaseCode) {
        return purchase.get(purchaseCode).getNofUnits();
	}
	
	//R5
	public SortedMap<Integer, List<String>> productIdsPerNofUnits() {
      return  purchase.values().stream()
                         .flatMap((Purchase e)->e.getItem().entrySet().stream())
                         .filter(e->e.getValue()>0)
                         .sorted(comparing(e->e.getValue()))
                         .collect(groupingBy(Map.Entry<Product, Integer>::getValue,
                        		             TreeMap::new,
                        		 Collectors.mapping(e->e.getKey().getProductId(),toList())
                        		 ));
		
	}
	
	public SortedMap<Integer, Integer> totalPurchasePerCard() {
        Map<Integer,Integer> m= purchase.values().stream()
        		                .filter(p->p.HasCardId())
        		                .collect(toMap( (Purchase p)->p.getCardId(),
        		                		         
        		                		     (Purchase p)->p.getNofUnits()
        		                		     
        		                		
        		                             )
        		                		);
        return m.entrySet().stream()
        		.collect(toMap(Map.Entry::getKey,
        				       Map.Entry::getKey,
        				       (oldValue,newValue)->newValue,
        				       TreeMap::new));
        				
        				
        				
        				
	}
	
	public int totalPurchaseWithoutCard() {
        return purchase.values().stream()
        		.filter(p->!p.HasCardId())
        		.collect(summingInt((Purchase p)->p.getNofUnits()));
	}
	
	public SortedMap<Integer, Integer> totalDiscountPerCard() {
       Map<Integer,Integer> m=purchase.values().stream()
    		                  .filter(p->p.HasCardId())
    		                  .collect(toMap((Purchase p)->p.getCardId(),
    		                		 
    		                		  (Purchase p)->(int)p.getPercentage()
    		                		  
    		                		   ) );
    		                  
		
		return m.entrySet().stream()
				.collect(toMap((Map.Entry::getKey,
 				                Map.Entry::getKey,
 				                (oldValue,newValue)->newValue,
 				                TreeMap::new));
	}


}
