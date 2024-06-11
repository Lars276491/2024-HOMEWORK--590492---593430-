package it.uniroma3.diadia.ambienti;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;
/**
 * Questa classe modella un labirinto
 * 
 * @author 589489 e 589300
 * @see Stanza
 * @version v1.0
 */

public class Labirinto {

	/* Stanza iniziale e stanza finale-vincente */
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	/*
	private Stanza stanzaCorrente;
	private Stanza ultimaStanzaAggiunta;*/

	private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c =
				new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}


	private Labirinto() {
		//	this.creaStanze();
	}

	/* Crea il labirinto *
	public void creaStanze() {

		/* crea gli attrezzi *
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);

		/* crea stanze del labirinto *
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze *
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

		/* pone gli attrezzi nelle stanze *
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio*
		this.stanzaIniziale = atrio;
		this.stanzaVincente = biblioteca;
	}*/


	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		//	return stanzaVincente;
		return this.stanzaVincente;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public static LabirintoBuilder newBuilder(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(nomeFile);
	}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}


	/*
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
/*
	public Stanza getUltimaAggiunta() {
		return this.ultimaStanzaAggiunta;
	}*/

	//static nested class 
	// tutte queste cose qui sotto sono prese da notion


	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String, Stanza> mappaStanze; //perchè è la mappa delle stanze

		/*questo è come lo avevo fatto io il costruttore
		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.mappaStanze = new HashMap<String, Stanza>();
		}*/


		public LabirintoBuilder() {
			labirinto= new Labirinto(); //posso mettere this.labirinto qui?
			mappaStanze = new HashMap<>(); //perchè qui non c'è scritto String e Stanza nelle parentesi?
		}

		public LabirintoBuilder(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
			
				labirinto=new Labirinto(nomeFile);
			
		}

		/*ctrl o serve per cercare un metodo all'interno della classe*/


		public Stanza getUltimaStanza() {
			return this.ultimaStanzaAggiunta;
		}

		public void setUltimaStanza(Stanza ultimaStanza) {
			this.ultimaStanzaAggiunta = ultimaStanza;
		}

		public Map<String, Stanza> getMappaStanze() {
			return this.mappaStanze;
		}


		public void setMappaStanzaNelLabirinto(Map<String, Stanza> mappaStanze) {
			this.mappaStanze = mappaStanze;
		}


		public void setLabirinto(Labirinto labirinto) {
			this.labirinto = labirinto;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
			Stanza i = new Stanza(stanzaIniziale);
			/* 
			labirinto.setStanzaIniziale(stanzaIniziale);
			ultimaStanza=stanzaIniziale;
			mappaStanzaNelLabirinto.put(nomeStanzaIniziale,stanzaIniziale);
			 */
			labirinto.setStanzaIniziale(i);
			ultimaStanzaAggiunta=i; 
			mappaStanze.put(stanzaIniziale,i);
			// tiene traccia
			return this;
			/*come avevamo fatto noi
			this.labirinto.setStanzaCorrente(i);
			this.UltimaStanzaAggiuntaEAggiorna(i);
			return this;*/
		}

		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			Stanza s = new Stanza(stanzaVincente);
			/* 
			labirinto.setStanzaVincente(stanzaVincente);
			ultimaStanza=stanzaVincente;
			mappaStanzaNelLabirinto.put(nomeStanzaVincente,stanzaVincente);
			 */
			labirinto.setStanzaVincente(s);
			ultimaStanzaAggiunta=s; //qui devo mettere ultimaStanzaAggiunta?
			mappaStanze.put(stanzaVincente,s);
			// tiene traccia
			return this;
			/*come avevamo fatto noi
			this.labirinto.setStanzaVincente(s);
			this.UltimaStanzaAggiuntaEAggiorna(s);
			return this;*/
		}

		/*come avevamo fatto noi
		public LabirintoBuilder addStanza(String stanza) {
			Stanza s = new Stanza(stanza);
			this.UltimaStanzaAggiuntaEAggiorna(s);
			return this;
		}	*/


		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza s = new Stanza(nomeStanza);
			mappaStanze.put(nomeStanza, s);
			ultimaStanzaAggiunta=s;
			return this;
		}	

		/* questo è come lo avevamo fatto noi
		public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
			Map<String, Integer> attrezzi = new HashMap<>();
			Attrezzo a = new Attrezzo(attrezzo, peso);
			if(this.ultimaStanzaAggiunta==null)
				return this;
			this.ultimaStanzaAggiunta.addAttrezzo(a);
			attrezzi.put(attrezzo, peso);
			return this;
		}*/

		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nomeAttrezzo,peso));//qua devo mettere ultimaStanzaAggiunta
			return this;
		}

		/* metodo momentaneamente aggiunto perchè consigliato da bing per fare in modo che 
		 * ultimaStanzaAggiunta sia la stanza iniziale prima di chiamare addAttrezzo nel test*
		public LabirintoBuilder setUltimaStanzaAggiunta(Stanza ultimaStanzaAggiunta) {
			this.ultimaStanzaAggiunta = ultimaStanzaAggiunta;
			return this;
		}per ora questo non lo metto*/


		public LabirintoBuilder addMago(String nomeMago, String presentazione, String nomeAttrezzoDelMago, int peso) {
			Attrezzo attrezzoDelMago= new Attrezzo(nomeAttrezzoDelMago, peso);
			ultimaStanzaAggiunta.setPersonaggio(new Mago(nomeMago, presentazione, attrezzoDelMago));
			return this;
		}


		public LabirintoBuilder addStrega(String nomeStrega, String presentazione) {
			ultimaStanzaAggiunta.setPersonaggio(new Strega(nomeStrega, presentazione));
			return this;
		}


		public LabirintoBuilder addCane(String nomeCane, String presentazione, String nomeAttrezzoDelCane, int pesoAttrezzoDelCane, String nomeCiboPreferitoDelCane, int pesoCiboPreferitoDelCane) {
			Attrezzo attrezzoDelCane= new Attrezzo(nomeAttrezzoDelCane, pesoAttrezzoDelCane);
			Attrezzo ciboPreferitoDelCane= new Attrezzo(nomeCiboPreferitoDelCane, pesoCiboPreferitoDelCane);
			ultimaStanzaAggiunta.setPersonaggio(new Cane(nomeCane, presentazione, attrezzoDelCane, ciboPreferitoDelCane));
			return this;
		}

		/* come avevamo fatto noi 
		public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiecente, String direzione) {
			Stanza c = this.mappaStanze.get(stanzaCorrente);
			Stanza a = this.mappaStanze.get(stanzaAdiecente);
			c.impostaStanzaAdiacente(direzione, a);
			return this;
		}*/


		public LabirintoBuilder addAdiacenza(String nomeStanzaDoveMettoAdiacenza,String nomeStanzaAdiacente, Direzione direzione) {
			Stanza stanzaAdiacente=null;
			if(mappaStanze.containsKey(nomeStanzaAdiacente)) {
				stanzaAdiacente = mappaStanze.get(nomeStanzaAdiacente);
			} else {
				stanzaAdiacente = new Stanza(nomeStanzaAdiacente);
			}

			Stanza StanzaDoveMettoAdiacenza= mappaStanze.get(nomeStanzaDoveMettoAdiacenza);
			StanzaDoveMettoAdiacenza.impostaStanzaAdiacente(direzione, stanzaAdiacente);

			mappaStanze.put(nomeStanzaAdiacente,stanzaAdiacente);
			return this;
		}

		/* come avevamo fatto noi
		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			Stanza stanza = new StanzaMagica(nome);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}*/

		public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
			StanzaMagica stanzaMagica=new StanzaMagica(nomeStanza,sogliaMagica);
			mappaStanze.put(nomeStanza,stanzaMagica);
			ultimaStanzaAggiunta=stanzaMagica;
			return this;
		}

		/* come avevamo fatto noi
		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
			Stanza stanza = new StanzaBuia(nome,attrezzoPerVedere);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}*/


		public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzoCheIllumina) {
			StanzaBuia stanzaBuia =new StanzaBuia(nomeStanza,attrezzoCheIllumina);
			mappaStanze.put(nomeStanza,stanzaBuia);
			ultimaStanzaAggiunta=stanzaBuia;
			return this;
		}


		public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione direzioneBloccata, String attrezzoSbloccante) {
			//	Stanza stanza = new StanzaBloccata(nome, attrezzoSbloccante, direzioneBloccata); noi avevamo fatto così, con tipo statico diverso dal tipo dinamico a quanto pare, 
			//	ma mettere sia come tipo statico che come tipo dinamico mette la stessa cosa
			StanzaBloccata stanzaBloccata = new StanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante);
			//	this.UltimaStanzaAggiuntaEAggiorna(stanza); come avevamo fatto noi

			mappaStanze.put(nomeStanza,stanzaBloccata);//nelle parentesi che ce devo mette?
			ultimaStanzaAggiunta=stanzaBloccata;
			return this;
		}

	}

}

