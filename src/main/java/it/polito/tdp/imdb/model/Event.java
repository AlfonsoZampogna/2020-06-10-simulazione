package it.polito.tdp.imdb.model;

public class Event {

	public enum EventType{
		NUOVA_INTERVISTA,
		GIORNO_FERIE
	}
	
	private EventType tipo;
	private int idEvent;
	
	public Event(EventType tipo, int idEvent) {
		super();
		this.tipo = tipo;
		this.idEvent = idEvent;
	}
	
	public EventType getTipo() {
		return tipo;
	}
	public void setTipo(EventType tipo) {
		this.tipo = tipo;
	}
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	
	
}
