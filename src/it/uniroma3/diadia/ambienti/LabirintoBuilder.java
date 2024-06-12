package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.FormatoFileNonValidoException;
//import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;
	private Map<String, Stanza> mappaStanze;
	
	public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto = new Labirinto();
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
	
//	public LabirintoBuilder addPersonaggio(String nome, String presentazione) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
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

