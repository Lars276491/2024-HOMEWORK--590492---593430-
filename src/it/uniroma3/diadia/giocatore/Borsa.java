package it.uniroma3.diadia.giocatore;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.Costanti;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.OrdinatorePerPeso;

/**
 * Questa classe gestisce gli attrezzi in possesso del giocatore
 * 
 * @see Attrezzo
 * @author 589489 e 589300
 * @version v1.0
 */

public class Borsa {
	private final static int DEFAULT_PESO_MAX_BORSA = getDefaultPesoMaxBorsa();
	private List<Attrezzo> attrezzi;
	private int pesoMax;
	private int pesoTotale;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public static int getDefaultPesoMaxBorsa() {
		int defaultPesoMaxBorsa= 10;

		Properties properties = new Properties();
		Reader fileReader = null;
		Writer fileWriter = null;

		try {
			fileReader = new FileReader("diadia.properties.txt");
			properties.load(fileReader);
			if(properties.getProperty("default_peso_max_borsa") != null)
				defaultPesoMaxBorsa= Integer.parseInt(properties.getProperty("default_peso_max_borsa"));
			else
				properties.setProperty("default_peso_max_borsa", Integer.toString(defaultPesoMaxBorsa));
			return defaultPesoMaxBorsa;
		} catch (IOException e) {
			try {
				return defaultPesoMaxBorsa;
			}finally {
				try {
					fileWriter = new FileWriter("diadia.properties.txt");
					if(fileWriter != null) {
						properties.store(fileWriter, "Costanti gioco diadia");
						fileWriter.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				fileWriter = new FileWriter("diadia.properties.txt");
				properties.store(fileWriter, "Costanti gioco diadia");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(fileReader != null)
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(fileWriter != null)
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>();
		this.pesoTotale = 0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || this.pesoMax < this.getPeso() + attrezzo.getPeso())
			return false;
		this.pesoTotale += attrezzo.getPeso();
		return this.attrezzi.add(attrezzo);
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if (!hasAttrezzo(nomeAttrezzo))
			return null;
		return this.attrezzi.get(this.attrezzi.indexOf(new Attrezzo(nomeAttrezzo, 0)));
	}

	public int getPeso() {
		return pesoTotale;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.contains(new Attrezzo(nomeAttrezzo, 0));
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = this.getAttrezzo(nomeAttrezzo);
		this.attrezzi.remove(new Attrezzo(nomeAttrezzo, 0));
		this.pesoTotale -= a.getPeso();
		return a;
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		final List<Attrezzo> ordinata = this.attrezzi;
		Collections.sort(ordinata, new OrdinatorePerPeso());
		return ordinata;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		final SortedSet <Attrezzo> ordinata = new TreeSet<Attrezzo>();
		ordinata.addAll(this.attrezzi);
		return ordinata;
	}

	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		final Map<Integer, Set<Attrezzo>> m = new TreeMap<Integer, Set<Attrezzo>>();
		for (Attrezzo a : this.attrezzi) {
			Integer i = a.getPeso();
			Set<Attrezzo> tmp = m.get(i);
			if (tmp == null)
				tmp = new HashSet<Attrezzo>();
			tmp.add(a);
			m.put(i, tmp);
		}
		return m;
	}

	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		final SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(new OrdinatorePerPeso());
		s.addAll(this.attrezzi);
		return s;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Attrezzi nella borsa: ");
			s.append(this.attrezzi.toString());
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
}
