package hydraulic;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element  {

	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name,2);
		//TODO: complete
	}
	public Split(String name,int numOutput) {
		super(name,numOutput);
		//TODO: complete
	}

	
	@Override
	void simulate(double inFlow,SimulationObserver observer)
	{ double outFlow=inFlow/2;
		observer.notifyFlow("Split", getName(), inFlow, outFlow,outFlow);
		for(Element e:getOutputs())
		{ if(e!=null)
			e.simulate(outFlow, observer);
		}
	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
	@Override
	public void printLayout(StringBuffer string) {
		string.append(" ["+ this.getName()+"] "+ "Split");
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
