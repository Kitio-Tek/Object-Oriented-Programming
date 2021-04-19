package hydraulic;

/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 */
public abstract class Element {
	protected String name=new String();
	protected Element[] outputs;
	protected Element input;
	protected int length;
	protected double flow ;
	/**
	 * Constructor
	 * @param name the name of the element
	 */
	public Element(String name){
		// TODO: to be implemented
		this(name,1);
	}
	
	public Element(String name,int size){
		// TODO: to be implemented
		this.name=name;
		outputs=new Element[size];
	}

	/**
	 * getter method
	 * @return the name of the element
	 */
	public String getName(){
		// TODO: to be implemented
		return this.name ;
	}
	
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem){
		// TODO: to be implemented
		connect(elem,0);
	}
	public void connect(Element elem, int noutput){
		//TODO: complete
			outputs[noutput]=elem;
			elem.input=this;
			length++;
	}
	
	/**
	 * Retrieves the element connected downstream of this
	 * @return downstream element
	 */
	public Element getOutput(){
		// TODO: to be implemented
		return outputs[0];
	}
	public Element getInput()
	{
		return input;
	}
	 
	public Element[] getOutputs(){
		//TODO: complete
	if(outputs.length!=0)
     return outputs;
	
	return null;
}  
	public int getSize(){
	//TODO: complete
    return length;
}
	public void setFlow(double flow){
		   
		this.flow=flow;
	}
	
	abstract void simulate(double inFlow, SimulationObserver observer);
	abstract void printLayout(StringBuffer string);
	abstract void simulate(double inFlow,SimulationObserverExt observer, boolean enableMaxFlowCheck);
	
/**
 * connect one of the outputs of this split to a
 * downstream component.
 * 
 * @param elem  the element to be connected downstream
 * @param noutput the output number to be used to connect the element
 */
	
}
