package it.polito.tdp.rivers.Model;

import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;





public class Simulator {
	//parametri di simulazione
	private double OCCUPAZIONE_INIZIALE; //q/2
	private double CAPIENZA_TOTALE;  //q
	private double F_OUT_MIN;      //0.8*fmed
    
	
	//stato del modello
	private double occupazione;
	
	//variabili di interesse
	private double occupazioneTot=0;
	private int numeroEventi=0;               //insieme mi calcolo la media
	private int noErogazioneMin=0;
	
	
	//Lista degli eventi
		PriorityQueue<Event> queue;
		
	public Simulator(double q, double fOutMin) {
		this.queue=new PriorityQueue<Event>();
		this.CAPIENZA_TOTALE=q;
		this.occupazione=q/2;
		this.F_OUT_MIN=fOutMin;	
	}
	
	
	public void run() {
		while(!queue.isEmpty()){
			Event e = queue.poll() ; //estrae l'ultimo  evento della coda
			double fOutEffettivo=F_OUT_MIN;
			if(Math.random()<0.1&&Math.random()<0.5){     //10% x 50% = 5%
				fOutEffettivo=10*F_OUT_MIN;               //flusso in uscita
			}
			double fIn= e.getF_in();                         //flusso in entrata
			double diff= fIn-fOutEffettivo;
			
			if(diff>0){
				occupazione=occupazione +diff;
				if(occupazione>CAPIENZA_TOTALE){
					occupazione=CAPIENZA_TOTALE;              //tracimazione
					
				}
				
			}
			if(diff<0){
				occupazione=occupazione+diff;
				if(occupazione<0){
					occupazione=0;  
					noErogazioneMin++;                       //non c 'era acqua sufficiente
				}               
			}
			occupazioneTot+= occupazione;
			
			numeroEventi++;
			System.out.println("data: "+e.getDate()+"      occupazione: "+occupazione+ "     numEvento: "+numeroEventi);
			
		}

		
	}


	public void addEvents(List<Event> events) {
		this.queue.addAll(events);
	}


	public double getOccupazioneMedia() {
		return occupazioneTot/numeroEventi;
	}


	public int getNoErogazioneMin() {
		return noErogazioneMin;
	}





	
	

}
