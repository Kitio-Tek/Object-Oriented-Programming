package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends ElementExt {
  private boolean open;
	public Tap(String name) {
		super(name);
		//TODO: complete
	}
	
	/**
	 * Defines whether the tap is open or closed.
	 * 
	 * @param open  opening level
	 */
	public void setOpen(boolean open){
		//TODO: complete
		this.open=open; 
	}
	@Override
	void simulate(double inFlow,SimulationObserver observer)
	{ double outFlow=open? inFlow:0.0;
		observer.notifyFlow("Tap", getName(), inFlow, outFlow);
		
		getOutput().simulate(outFlow, observer);
	}
	
	@Override
	public void simulate(double inFlow,SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		// TODO: to be implemented
		double outFlow=open? inFlow:0.0;
		
		if(enableMaxFlowCheck)
		{
			if(maxflow>=inFlow)
			{ 
			observer.notifyFlow("Tap", getName(), inFlow, outFlow);
			
			getOutput().simulate(outFlow, observer,enableMaxFlowCheck);
       }
			else {
				observer.notifyFlowError("Tap",getName(), inFlow,maxflow);
				getOutput().simulate(outFlow, observer,enableMaxFlowCheck);
				
				}}
		
	}
	@Override
	public void printLayout(StringBuffer string) {
		
		if(getOutput()!=null)
		    { string.append("["+ this.getName()+"]"+ "Tap" +" "+"-> ");
		      getOutput().printLayout(string);
		  }
		
		else
			string.append(" ["+ this.getName()+"]" + "Tap");
		    string.append(" * ");		
	}

}
