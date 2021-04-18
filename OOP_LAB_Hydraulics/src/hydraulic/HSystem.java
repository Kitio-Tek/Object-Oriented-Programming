package hydraulic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	/*private Element[] elements= new Element[100];*/
	private int nElement=0;
	protected List<Element> elements=new ArrayList<>();
	
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	public void addElement(Element elem){
		// TODO: to be implemented
		/*elements[nElement++]=elem;*/
		
		elements.add(elem);
	}
	
	/**
	 * returns the element added so far to the system.
	 * If no element is present in the system an empty array (length==0) is returned.
	 * 
	 * @return an array of the elements added to the hydraulic system
	 */ 
	public Element[] getElements(){
		// TODO: to be implemented
		/*Element[] result=new Element[nElement];
		for(int i=0;i<nElement;i++)
			result[i]=elements[i];
		return result;*/
		
		/*return Arrays.copyOf(elements, nElement);*/
		
		/*Element[] result=new Element[element.size()];
		for(int i=0;i<element.size();i++)
			result[i]=element.get(i);
		return result;*/
		
		
		return elements.toArray(new Element[elements.size()]  );
	}
	
		/**
	 * starts the simulation of the system
	 */
	public void simulate(SimulationObserver observer){
		// TODO: to be implemented
		for(Element e:elements) {
			if(e instanceof Source ) {
				e.simulate(-1,observer);
				
				
			}
		}
		
	}

}
