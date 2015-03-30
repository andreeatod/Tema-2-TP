package simcozi;

public class Customer {
	private int timpService;
	private int timpAstept;
	
	public Customer(){
		int tmin, tmax; //timp de servit
		tmin=Simulare.getMin();
		tmax=Simulare.getMax();
		this.timpService=tmin+(int)(Math.random()*(tmax-tmin+1));
	}
	
	//settere si gettere pentru cei 2 timpi necesari pt client
	public void setTimpServ(int timp){
		this.timpService=timp;
	}
	
	public void setTimpAst(int timp){
		this.timpAstept=timp;
	}
	
	public int getTimpServ(){
		return this.timpService;
	}
	
	public int getTimpAst(){
		return this.timpAstept;
	}

}
