package it.polito.tdp.rivers.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.Model.Event;
import it.polito.tdp.rivers.Model.River;

public class RiversDAO {

	public List<River> getAllRivers() {
		List<River>rivers= new ArrayList<River>();
		final String sql = "SELECT * FROM river";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				River river = new River(rs.getInt("id"), rs.getString("name"));
				rivers.add(river);
				
			}

			return rivers;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public LocalDate getFirstDate(River r) {
		LocalDate ld=null;
		final String sql = "SELECT MIN(day) "+
		                   "FROM flow f, river r "+
                           "WHERE f.river=r.id AND r.id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,r.getId());

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				 ld= rs.getDate("MIN(day)").toLocalDate();
			
			}

			return ld;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public LocalDate getLastDate(River r) {
		LocalDate ld=null;
		final String sql = "SELECT MAX(day) "+
		                   "FROM flow f, river r "+
                           "WHERE f.river=r.id AND r.id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,r.getId());

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				 ld= rs.getDate("MAX(day)").toLocalDate();
			
			}

			return ld;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public int getNumberMeasurements(River r) {
		int num=0;
		final String sql = "SELECT COUNT(*) "+
                           "FROM flow "+
                           "WHERE river=? ";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,r.getId());

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				 num= rs.getInt("COUNT(*)");
			
			}

			return num;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public float getAVGFlow(River r) {

		float num=0;
		final String sql = "SELECT AVG(flow) "+
                           "FROM flow "+
                           "WHERE river=? ";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,r.getId());

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				 num= rs.getInt("AVG(flow)");
			
			}

			return num;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Event> getDateFlow(River r) {
		List<Event> listaEventi= new ArrayList<Event>();
		final String sql= "SELECT day, flow "+
                          "FROM flow "+
                          "WHERE river=? "+ 
                          "ORDER BY day DESC";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,r.getId());

			ResultSet rs = st.executeQuery();

			while(rs.next()) {
              Event e= new Event(rs.getDate("day").toLocalDate(),rs.getDouble("flow"));
				 listaEventi.add(e);
			
			}

			return listaEventi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
