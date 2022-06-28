package it.polito.tdp.imdb.model;

public class Adiacenza {

	private Integer idActor1;
	private Integer idActor2;
	private Integer peso;
	public Adiacenza(Integer idActor1, Integer idActor2, Integer peso) {
		super();
		this.idActor1 = idActor1;
		this.idActor2 = idActor2;
		this.peso = peso;
	}
	public Integer getIdActor1() {
		return idActor1;
	}
	public void setIdActor1(Integer idActor1) {
		this.idActor1 = idActor1;
	}
	public Integer getIdActor2() {
		return idActor2;
	}
	public void setIdActor2(Integer idActor2) {
		this.idActor2 = idActor2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
}
