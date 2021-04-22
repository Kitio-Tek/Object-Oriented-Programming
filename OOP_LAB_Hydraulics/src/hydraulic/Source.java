package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * The status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends ElementExt {
    
	public Source(String name) {
		super(name);
		//TODO: complete
	}

	/**
	 * defines the flow produced by the source
	 * 
	 * @param flow
	 */
	
	
	@Override
	void simulate(double inFlow,SimulationObserver observer)
	{
		observer.notifyFlow("Source", getName(), SimulationObserver.NO_FLOW, flow);
		getOutput().simulate(flow, observer);
	}
	
	@Override
	public void simulate(double inFlow,SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		// TODO: to be implemented
		if(enableMaxFlowCheck) {
		observer.notifyFlow("Source", getName(), SimulationObserver.NO_FLOW, flow);
		getOutput().simulate(flow, observer,enableMaxFlowCheck);
		}

	}
	
	@Override
	public void printLayout(StringBuffer string) {
		if(getOutput()!=null)
		{string.append(" ["+ this.getName()+"]"+ "Source" +" "+"-> ");
		getOutput().printLayout(string);}
		
		else
			string.append(" ["+ this.getName()+"]" + "Tap");
		    string.append(" * ");
		
	}
	@Override
	public void setMaxFlow(double maxFlow) {
		// TODO: to be implemented
	}
}
