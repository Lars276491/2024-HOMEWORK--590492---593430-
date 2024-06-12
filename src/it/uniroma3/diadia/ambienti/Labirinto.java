package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Questa classe modella un labirinto
 * 
 * @author 589489 e 589300
 * @see Stanza
 * @version v1.0
 */

public class Labirinto {
	
	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private List<Stanza> stanze;
		private List<AbstractPersonaggio> personaggi;
		private Stanza stanzaIniziale;
		private Stanza stanzaVincente;

		public LabirintoBuilder(URI nomeFile) {
			this.labirinto = new Labirinto(nomeFile);
			this.stanze = new ArrayList<Stanza>();
			this.personaggi = new ArrayList<AbstractPersonaggio>();
		}
		
		public LabirintoBuilder(){
			this.labirinto = new Labirinto();
			this.stanze = new ArrayList<Stanza>();
			this.personaggi = new ArrayList<AbstractPersonaggio>();
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			this.stanzaIniziale = new Stanza(nomeStanza);
			this.labirinto.setStanzaIniziale(stanzaIniziale);
			if(!stanze.contains(stanzaIniziale))
				this.stanze.add(stanzaIniziale);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			this.stanzaVincente = new Stanza(nomeStanza);
			this.labirinto.setStanzaVincente(stanzaVincente);
			if(!stanze.contains(stanzaVincente))
				this.stanze.add(stanzaVincente);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String s1, String s2, String direzione) {
			Stanza stanza1 = null;
			Stanza stanza2 = null;
			for(Stanza s : this.stanze) {
				if(s.getNome().equals(s1))
					stanza1 = s;
				else if(s.getNome().equals(s2))
					stanza2 = s;
			}
			if(direzione.equals("sud") || direzione.equals("nord") || direzione.equals("ovest") || direzione.equals("est"))
				stanza1.impostaStanzaAdiacente(direzione, stanza2);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			if(nomeAttrezzo == null)
				return this;
			Attrezzo a = new Attrezzo(nomeAttrezzo, peso);
			Stanza s = this.stanze.get(this.stanze.size()-1);
			s.addAttrezzo(a);
			return this;
		}

		public LabirintoBuilder addStanza(String nomeStanza) {
			if(this.stanze.contains(new Stanza(nomeStanza)))
				return this;
			this.stanze.add(new Stanza(nomeStanza));
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
			if(this.stanze.contains(new Stanza(nomeStanzaMagica)))
				return this;
			this.stanze.add(new StanzaMagica(nomeStanzaMagica, sogliaMagica));
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String direzione, String chiave) {
			if(this.stanze.contains(new Stanza(nomeStanzaBloccata)))
				return this;
			this.stanze.add(new StanzaBloccata(nomeStanzaBloccata, direzione, chiave));
			return this;
		}

		public List<Stanza> getListaStanze() {
			return this.stanze;
		}

		public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String chiave) {
			if(this.stanze.contains(new Stanza(nomeStanzaBuia)))
				return this;
			this.stanze.add(new StanzaBuia(nomeStanzaBuia, chiave));
			return this;
		}

		public Stanza getStanzaIniziale() {
			return this.stanzaIniziale;
		}

		public Stanza getStanzaVincente() {
			return this.stanzaVincente;
		}

		public LabirintoBuilder addPersonaggio(String tipoPersonaggio, String nomePersonaggio, String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) {
			Attrezzo a = new Attrezzo(nomeAttrezzo, Integer.parseInt(nomeStanza));
			
			tipoPersonaggio = tipoPersonaggio.replace(tipoPersonaggio.charAt(0), Character.toUpperCase(tipoPersonaggio.charAt(0)));
			try {
				AbstractPersonaggio p = (AbstractPersonaggio) Class.forName("it.uniroma3.personaggi."+tipoPersonaggio).newInstance();
				p.setNome(nomePersonaggio);
				p.setAttrezzo(a);
				if(!this.personaggi.contains(p))
					this.personaggi.add(p);
				this.stanze.get(this.stanze.indexOf(new Stanza(nomeStanza))).setPersonaggio(p);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException e) {
				return this;
			}
			return this;
		}

		public List<AbstractPersonaggio> getPersonaggi() {
			return this.personaggi;
		}
	}

	/* Stanza iniziale e stanza finale-vincente */
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	private Labirinto() {
		this.creaStanze();
	}
	
	public Labirinto(URI nomeFile) {
		CaricatoreLabirinto c;
		try {
			c = new CaricatoreLabirinto(nomeFile);
			c.carica();
			this.stanzaIniziale = c.getStanzaIniziale();
			this.stanzaVincente = c.getStanzaVincente();
		} catch (FileNotFoundException | FormatoFileNonValidoException e) {
			this.creaStanze();
		}
		
	}

	/* Crea il labirinto */
	public void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
		this.stanzaIniziale = atrio;
		this.stanzaVincente = biblioteca;
	}

	public void setStanzaIniziale(Stanza s) {
		this.stanzaIniziale = s;
	}
	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public void setStanzaVincente(Stanza s) {
		this.stanzaVincente = s;
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	public static LabirintoBuilder newBuilder(URI uri) {
		return new LabirintoBuilder(uri);
	}
}
