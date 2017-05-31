package it.polito.tdp.rivers.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.DB.RiversDAO;

public class Model {
	private List<River>listaTotRivers;
	private Statistic stat=new Statistic();
	RiversDAO rDAO;
	
	public Model(){
		rDAO= new RiversDAO();
	}
	

	public List<River> getAllRivers() {
		if(listaTotRivers==null){
			RiversDAO rDAO= new RiversDAO();
			listaTotRivers=rDAO.getAllRivers();
			
		}
		return listaTotRivers;
	}
	
	public LocalDate getPrimaData(River r){		
		LocalDate primaData=rDAO.getFirstDate(r);
	
		return primaData;		
	}

	public LocalDate getUltimaData(River r) {
		LocalDate ultimaData=rDAO.getLastDate(r);
		return ultimaData;
	
	}

	public int getNumeroMisure(River r) {		
		return rDAO.getNumberMeasurements(r);
	}

	public float getAVGFlusso(River r) {
		return rDAO.getAVGFlow(r);
	}

	
	
	public void simula(River r, float k, float f_med) {	
		double Q=k*f_med*30;
		double fOutMin=  (f_med*0.8);
		Simulator sim= new Simulator(Q,fOutMin);
		List<Event>events= new ArrayList<Event>(rDAO.getDateFlow(r));	
		sim.addEvents(events);
		sim.run();	
		stat.setNoErogazione(sim.getNoErogazioneMin());
		stat.setOccupazioneMedia(sim.getOccupazioneMedia());
	}

	
	
	public Statistic getStat() {
		return stat;
	}
	



}
