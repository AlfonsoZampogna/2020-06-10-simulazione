package it.polito.tdp.imdb.model;

import java.util.Comparator;

public class OrdineAlfabetico implements Comparator<Actor> {

	@Override
	public int compare(Actor o1, Actor o2) {
	    if(!o1.getLastName().equals(o2.getLastName()))
	    	return o1.getLastName().compareTo(o2.getLastName());
	    else
	    	return o1.getFirstName().compareTo(o2.getFirstName());	
	}

}
