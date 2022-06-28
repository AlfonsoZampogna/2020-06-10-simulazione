package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {

	private ImdbDAO dao;
	private Graph<Actor,DefaultWeightedEdge> grafo;
	
	private List<String> genres = new ArrayList<String>();
	
	private Collection<Actor> interviste;
	private int ferie;
	
	public Model() {
		this.dao = new ImdbDAO();
	}

	public List<String> getGenres(){
		if(this.genres.isEmpty())
			this.genres = this.dao.listAllGenres();
		return this.genres;
	} 
	
	public void creaGrafo(String genre) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Map<Integer,Actor> idMap = new HashMap<Integer,Actor>();
		for(Actor a : this.dao.listActorsGenre(genre)) 
			idMap.put(a.getId(), a);
		
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		for(Adiacenza a : this.dao.listAdiacenze(genre)) {
			Actor a1 = idMap.get(a.getIdActor1());
			Actor a2 = idMap.get(a.getIdActor2());
			Graphs.addEdgeWithVertices(this.grafo, a1, a2, a.getPeso());
		}	
	}
	
	public int getNumVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNumArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public Set<Actor> getAttori(){
		return this.grafo.vertexSet();
	}

	public List<Actor> getSimili(Actor partenza) {
		List<Actor> percorso = new ArrayList<Actor>();
		GraphIterator<Actor,DefaultWeightedEdge> iteratore = 
				new BreadthFirstIterator <Actor,DefaultWeightedEdge>(this.grafo, partenza);
		while(iteratore.hasNext()) {
			Actor a = iteratore.next();
			percorso.add(a);
		}
		Collections.sort(percorso, new OrdineAlfabetico());
		return percorso;
	}

	public void getAttoriIntervistati(String genre, int days) {
		Simulator sim = new Simulator(days,this.grafo);
		sim.init();
		sim.run();
		interviste = sim.getInterviewedActors();
		ferie = sim.getPauses();
	}

	public Collection<Actor> getInterviste() {
		return interviste;
	}

	public int getFerie() {
		return ferie;
	}
	

}
