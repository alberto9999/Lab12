package it.polito.tdp.rivers.Model;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.rivers.DB.RiversDAO;

public class Model {
	private List<River>listaTotRivers;
	
	
	

	public List<River> getAllRivers() {
		if(listaTotRivers==null){
			RiversDAO rDAO= new RiversDAO();
			listaTotRivers=rDAO.getAllRivers();
			
		}
		return listaTotRivers;
	}
	
	public LocalDate getPrimaData(River r){		
		RiversDAO rDAO= new RiversDAO();
		LocalDate primaData=rDAO.getFirstDate(r);
	
		return primaData;		
	}

	public LocalDate getUltimaData(River r) {
		RiversDAO rDAO= new RiversDAO();
		LocalDate ultimaData=rDAO.getLastDate(r);
	
		return ultimaData;
	
	}

	public int getNumeroMisure(River r) {
		RiversDAO rDAO= new RiversDAO();
		
		return rDAO.getNumberMeasurements(r);
	}

	public float getAVGFlusso(River r) {
		RiversDAO rDAO= new RiversDAO();
	
		return rDAO.getAVGFlow(r);
	}

}
