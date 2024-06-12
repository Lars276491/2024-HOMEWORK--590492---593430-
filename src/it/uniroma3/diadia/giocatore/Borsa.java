package it.uniroma3.diadia.giocatore;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	private int pesoAttuale;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {

		this.pesoMax = pesoMax;
		this.attrezzi = new TreeMap<>(); 
		this.numeroAttrezzi = 0;
		this.pesoAttuale = 0;
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null)
			return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		this.numeroAttrezzi++;
		this.pesoAttuale += attrezzo.getPeso();
		return true;
	}
	public int getPesoMax() {
		return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(nomeAttrezzo != null && this.attrezzi.containsKey(nomeAttrezzo))
			a=this.attrezzi.get(nomeAttrezzo);
		return a;
	}
	public int getPeso() {
		return this.pesoAttuale;
	}
	
	public boolean getPesoRimanente(Attrezzo a) {
		if(a != null && this.getPesoMax()-this.getPeso() >= a.getPeso())
			return true;
		return false;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(nomeAttrezzo != null) {
			a = attrezzi.remove(nomeAttrezzo);
		}
		return a;
	}



	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			s.append("\n");
			s.append(this.getContenutoOrdinatoPerNome().toString());
			s.append("\n");
			s.append(this.getContenutoRaggruppatoPerPeso().toString());
			s.append("\n");
			s.append(this.getSortedSetOrdinatoPerPeso().toString());
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(new ComparatorePerPeso());
		s.addAll(this.attrezzi.values());
		return s;
	}
	List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> l = new ArrayList<>();
		l.addAll(this.attrezzi.values());
		Collections.sort(l, new ComparatorePerPeso());
		return l;
	}

	SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<Attrezzo>(this.attrezzi.values());
	}

	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> a2p = new TreeMap<>();
		
		for(Attrezzo a : this.attrezzi.values()){
			if(a2p.containsKey(a.getPeso())) {
				a2p.get(a.getPeso()).add(a);
			}
			else {
				Set<Attrezzo>  s =new HashSet<Attrezzo>();
				s.add(a);
				a2p.put(a.getPeso(), s);
			}
		}
		return a2p;
	}
	
	
}