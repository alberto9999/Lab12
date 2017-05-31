package it.polito.tdp.rivers.Model;

import java.time.LocalDate;



public class Event implements Comparable<Event>{

	private LocalDate date;
	private double f_in;
	
	public Event( LocalDate date,double f_in) {
		super();
		this.date = date;
		this.f_in=f_in;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getF_in() {
		return f_in;
	}

	public void setF_in(double f_in) {
		this.f_in = f_in;
	}

	@Override
	public int compareTo(Event other) {
		if(this.date.isBefore(other.date))
			return -1;
		
		if(this.date.isAfter(other.date))
			return 1;
		
		return 0;
	}
	

}
