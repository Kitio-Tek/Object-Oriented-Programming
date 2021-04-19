package hydraulic;

import java.util.Arrays;

/**
 * Represents a multisplit element, an extension of the Split that allows many outputs
 * 
 * During the simulation each downstream element will
 * receive a stream that is determined by the proportions.
 */

public class Multisplit extends Split {
 double proportion[];
 
	/**
	 * Constructor
	 * @param name
	 * @param numOutput
	 */
	public Multisplit(String name, int numOutput) {
		super(name,numOutput); //you can edit also this line
		// TODO: to be implemented
		proportion=new double[numOutput];
	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
	

    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
    @Override
	public void connect(Element elem, int noutput){
		//TODO: complete
		super.connect(elem, noutput);
	}
	
	/**
	 * Define the proportion of the output flows w.r.t. the input flow.
	 * 
	 * The sum of the proportions should be 1.0 and 
	 * the number of proportions should be equals to the number of outputs.
	 * Otherwise a check would detect an error.
	 * 
	 * @param proportions the proportions of flow for each output
	 */
	public void setProportions(double... proportions) {
		// TODO: to be implemented
		proportion=Arrays.copyOf(proportions, proportions.length);
	}
	@Override
	void simulate(double inFlow,SimulationObserver observer)
	{ double[] outFlow=new double[proportion.length];
	
	  for(int i=0;i<proportion.length;i++)
		  outFlow[i]=proportion[i]*inFlow;
	
	 int j=0; 
	  observer.notifyFlow("Multi Split", getName(), inFlow, outFlow);
		for(Element e:getOutputs())
		{   
			e.simulate(outFlow[j], observer);
			j++;
			
		}
		
	}
	@Override
	public void simulate(double inFlow,SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		// TODO: to be implemented
		if(enableMaxFlowCheck)
		{
			if(maxflow>=inFlow)
			{ 
				double[] outFlow=new double[proportion.length];
				
				  for(int i=0;i<proportion.length;i++)
					  outFlow[i]=proportion[i]*inFlow;
				
				 int j=0; 
				  observer.notifyFlow("Multi Split", getName(), inFlow, outFlow);
					for(Element e:getOutputs())
					{   
						e.simulate(outFlow[j], observer,enableMaxFlowCheck);
						j++;
						
					}
       }
			else
				observer.notifyFlowError("Multi Split",getName(), inFlow,maxflow);}

	}
	
	@Override
	public void printLayout(StringBuffer string) {
		string.append(" ["+ this.getName()+"] "+ "Multi Split");
		char[] space=new char[string.length()];
		Arrays.fill(space,' ');
		int i=0;
		
		for(Element e:getOutputs())
		{ if(e!=null)
		       {
			string.append(" +-> ");
			e.printLayout(string);
			 
			if(i==getSize()-1) continue;
			i++;
			string.append("\n").append(space).append(" |\n").append(space);
		    
		   }
		else {
			string.append(" +-> *");
			if(i==getSize()-1) continue;
			i++;
			string.append("\n").append(space).append(" |\n").append(space);
		}
			
		}
		
		
	}


}
