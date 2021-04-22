package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystemExt extends HSystem{
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		// TODO: to be implemented
		StringBuffer string=new StringBuffer();
		
		for(Element e:elements) {
			if(e instanceof Source ) {
				e.printLayout(string);
			}
		}
		
		
		return string.toString();
	}
	
	/**
	 * Deletes a previously added element with the given name from the system
	 */
	public boolean deleteElement(String name) {
		// TODO: to be implemented
		
		for(Element e:elements) {
			if(e.getName().equals(name) && e!=null)
			{
				if(e instanceof Split && e.getSize()>1)
				 return false;
				
				Element input=e.getInput();
				Element output=e.getOutput();
				
				/*if(input!=null ) { input.connect(output);
				}*/
			
				 
				 elements.remove(e);
				  return input.replacewith_Split(e,output);
				
				
				                
               
				
			}}
		return false;
	}

	/**
	 * starts the simulation of the system; if enableMaxFlowCheck is true,
	 * checks also the elements maximum flows against the input flow
	 */


	public void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		// TODO: to be implemented
		if(enableMaxFlowCheck) {
		for(Element e:elements) {
			if(e instanceof Source ) {
				e.simulate(-1,observer,enableMaxFlowCheck);
				
				
			}
		}}
	}
	
}
