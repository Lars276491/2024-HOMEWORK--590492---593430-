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

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	/*
	private Stanza stanzaCorrente;
	private Stanza ultimaStanzaAggiunta;*/


	private Labirinto(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c =
				new CaricatoreLabirinto(labirinto);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}


/*
	private Labirinto() {
		//	this.creaStanze();
	}

	/* Crea il labirinto *
	public void creaStanze() {

		/* crea gli attrezzi *
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);
		Attrezzo piedediporco = new Attrezzo("piedediporco",2);
		Attrezzo spada = new Attrezzo("vanga",1);
		Attrezzo ascia = new Attrezzo("pala",1);

		/* crea stanze del labirinto *
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new StanzaMagica("Aula N11");
		Stanza aulaN10 = new StanzaBloccata("Aula N10", "est", "piedediporco");
		Stanza laboratorio = new StanzaBuia("Laboratorio Campus", "lanterna");
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
		atrio.addAttrezzo(spada);
		atrio.addAttrezzo(ascia);
		aulaN11.addAttrezzo(piedediporco);

		// il gioco comincia nell'atrio*
		this.stanzaIniziale = atrio;
		this.stanzaVincente = biblioteca;
	}*/

/*
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}*/

	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(labirinto);
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
/*
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}*/

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	/*forse da cancellare
	public static LabirintoBuilder newBuilder(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(nomeFile);
	}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}*/

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaIniziale = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaIniziale;
	}


	//static classe nidificata 
	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String, Stanza> mappaStanze;
		
		public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(labirinto);
			this.mappaStanze = new HashMap<>();
		}
		
		public Map<String, Stanza> getMappaStanze() {
			return this.mappaStanze;
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public Stanza getUltimaStanza() {
			return this.ultimaStanzaAggiunta;
		}

		public void setUltimaStanza(Stanza ultimaStanza) {
			this.ultimaStanzaAggiunta = ultimaStanza;
		}

		public void setMappaStanzaNelLabirinto(Map<String, Stanza> mappaStanze) {
			this.mappaStanze = mappaStanze;
		}

		public void setLabirinto(Labirinto labirinto) {
			this.labirinto = labirinto;
		}

		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
			Stanza i = new Stanza(stanzaIniziale);
			labirinto.setStanzaCorrente(i);
			ultimaStanzaAggiunta=i; 
			mappaStanze.put(stanzaIniziale,i);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			Stanza s = new Stanza(stanzaVincente);
			labirinto.setStanzaVincente(s);
			ultimaStanzaAggiunta=s;
			mappaStanze.put(stanzaVincente,s);
			return this;
		}

		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza s = new Stanza(nomeStanza);
			mappaStanze.put(nomeStanza, s);
			ultimaStanzaAggiunta=s;
			return this;
		}	
		
//		public LabirintoBuilder addPersonaggio(String nome, String presentazione) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//			AbstractPersonaggio p = null;
		//
		//			StringBuilder nomeClasse
		//			= new StringBuilder("it.uniroma3.diadia.personaggi.");
		//			nomeClasse.append( nome.substring(1) ) ;
		//			p = (AbstractPersonaggio)Class.forName(nomeClasse.toString()).newInstance();
		//			p.setNome(nome);
		//			p.setPresentazione(presentazione);
		//			if(this.ultimaStanzaAggiunta==null)
		//				return this;
		//			this.ultimaStanzaAggiunta.setPersonaggio(p);
		//			return this;
		//		}

		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nomeAttrezzo,peso));//qua devo mettere ultimaStanzaAggiunta
			return this;
		}

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

		public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
			StanzaMagica stanzaMagica=new StanzaMagica(nomeStanza,sogliaMagica);
			mappaStanze.put(nomeStanza,stanzaMagica);
			ultimaStanzaAggiunta=stanzaMagica;
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzoCheIllumina) {
			StanzaBuia stanzaBuia =new StanzaBuia(nomeStanza,attrezzoCheIllumina);
			mappaStanze.put(nomeStanza,stanzaBuia);
			ultimaStanzaAggiunta=stanzaBuia;
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione direzioneBloccata, String attrezzoSbloccante) {
			StanzaBloccata stanzaBloccata = new StanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante);
			mappaStanze.put(nomeStanza,stanzaBloccata);//nelle parentesi che ce devo mette?
			ultimaStanzaAggiunta=stanzaBloccata;
			return this;
		}

	}

}

