package simcozi;

import java.util.*;
import java.lang.*;

public class Simulare {
	
	private ArrayList<Coada> cozi;
	private ArrayList<Integer> waiters;
	private int nrC, timpSim, minS, maxS, nextCustTime;
	static private int max, min;
	
	
	//constructor
	public Simulare(int minS, int maxS, int min, int max, int nrC, int timpSim){
		this.minS=minS;
		this.maxS=maxS;
		this.min=min;
		this.max=max;
		this.nrC=nrC;
		this.timpSim=timpSim;
		cozi=new ArrayList<Coada>();
		for(int i=0;i<nrC;i++)
			cozi.add(new Coada());
		waiters=new ArrayList<Integer>();
	}
	
	static public int getMin(){
		return min;
	}
	
	static public int getMax(){
		return max;
	}
	
	public void afisClient(int nr){
		String clienti="";
		for(int i=0;i<cozi.get(nr).getCustNr();i++)
			clienti+="C ";
		GUI.setq(clienti, nr);
	}
	
	public void afisWait(int nr){
		GUI.setw(Integer.toString(cozi.get(nr).getTimpAsteptare()), nr);
	}
	
	public void run(){
		int i=0;
		while(i<timpSim){
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){}
			if(nextCustTime==0){
				int min=Integer.MAX_VALUE;
				int coadaMin=0;
				for(int j=0;j<nrC;j++)
				{
					if(cozi.get(j).getTimpAsteptare()<min){
						min=cozi.get(j).getTimpAsteptare();
						coadaMin=j;
					}
				}
				cozi.get(coadaMin).adauga(new Customer());
				cozi.get(coadaMin).setTimpAsteptare();
				nextCustTime=(int)(minS+Math.random()*(maxS-minS+1));
				waiters.add(cozi.get(coadaMin).getTimpAsteptare());
			}
			
			timpSim--;
			nextCustTime--;
			for(int j=0;j<cozi.size();j++){
				if(cozi.get(j).coadaEmpty()==false){
					if(cozi.get(j).getTimpServire()==0)
						cozi.get(j).sterge();
					else
						cozi.get(j).treceTimp();
					cozi.get(j).setTimpAsteptare();
				}
				afisClient(j);
				afisWait(j);
			}
			i++;
		}
		finalTime();			
	}
	
	public void finalTime(){
		float timpMediu=0;
		for(int i=0;i<waiters.size();i++)
			timpMediu+=waiters.get(i);
		timpMediu=timpMediu/waiters.size();
		GUI.setmw(Float.toString(timpMediu)); 
		try{
			Thread.sleep(10000);
		}catch(InterruptedException e){}
		System.exit(1);
		
	}

}
