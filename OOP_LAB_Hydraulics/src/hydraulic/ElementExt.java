package hydraulic;

public abstract class ElementExt extends Element{
	protected double maxflow;
	public ElementExt(String name) {
		super(name);
	}
	
	public ElementExt(String name,int size){
		// TODO: to be implemented
		super(name,size);
	}

	public void setMaxFlow(double maxFlow) {
		// TODO: to be implemented
		this.maxflow=maxFlow ;
	}

}
