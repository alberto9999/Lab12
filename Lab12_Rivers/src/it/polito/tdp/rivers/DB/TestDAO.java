package it.polito.tdp.rivers.DB;


import java.util.List;

import it.polito.tdp.rivers.Model.Model;
import it.polito.tdp.rivers.Model.River;

public class TestDAO {

	public static void main(String[] args) {
	Model m= new Model();
	RiversDAO rDAO = new RiversDAO();
	List<River>rivers= rDAO.getAllRivers();

	System.out.println(rDAO.getDateFlow(rivers.get(0)));
	
	

	}

}
