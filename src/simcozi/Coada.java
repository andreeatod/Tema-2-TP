package simcozi;

import java.util.*;

public class Coada {
	
	ArrayList<Customer> waitingCust;
	
	//constructor
	public Coada(){
		waitingCust=new ArrayList<Customer>();
	}
	
	public int getTimpServire(){
		if(waitingCust.isEmpty()==true)
			return 0;
		else
			return waitingCust.get(0).getTimpServ();
	}
	
	public void adauga(Customer c){
		waitingCust.add(c);
	}
	
	public void sterge(){
		if(waitingCust.isEmpty()==false)
			waitingCust.remove(0); //mereu stergem primul client din coada
	}
	
	public void treceTimp(){
		if(waitingCust.isEmpty()==false)
			waitingCust.get(0).setTimpServ(waitingCust.get(0).getTimpServ()-1);
	}
	
	//getter si setter timp asteptare pt client
	public int getTimpAsteptare(){
		if(waitingCust.isEmpty()==true)
			return 0;
		else
			return waitingCust.get(waitingCust.size()-1).getTimpAst()+waitingCust.get(waitingCust.size()-1).getTimpServ();
	}
	
	public void setTimpAsteptare(){
		if(waitingCust.isEmpty()==false)
			waitingCust.get(0).setTimpAst(0);
		for(int i=1;i<this.getCustNr();i++)
			waitingCust.get(i).setTimpAst(waitingCust.get(i-1).getTimpAst()+waitingCust.get(i-1).getTimpServ());
	}
	
	public boolean coadaEmpty(){
		if(waitingCust.isEmpty()==true)
			return true;
		else
			return false;
		
	}

	public int getCustNr() {
		if(waitingCust.isEmpty()==true)
			return 0;
		else
			return waitingCust.size();
	}


}
