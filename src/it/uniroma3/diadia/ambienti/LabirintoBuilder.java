package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;
	private Map<String, Stanza> mappaStanze; //perchè è la mappa delle stanze

	/*questo è come lo avevo fatto io il costruttore
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.mappaStanze = new HashMap<String, Stanza>();
	}*/

	/* questo invece è come ha fatto Silvia il costruttore */
	/* Silvia il costruttore lo mette più avanti, non qui all'inizio
	 * ma va bene pure qui?
	 */
	public LabirintoBuilder() {
		labirinto= new Labirinto(); //posso mettere this.labirinto qui?
		mappaStanze = new HashMap<>(); //perchè qui non c'è scritto String e Stanza nelle parentesi?
	}


	/* metodo preso da Silvia */
	public Stanza getUltimaStanza() {
		return this.ultimaStanzaAggiunta;
	}
	/* metodo preso da Silvia */
	public void setUltimaStanza(Stanza ultimaStanza) {
		this.ultimaStanzaAggiunta = ultimaStanza;
	}

	public Map<String, Stanza> getMappaStanze() {
		return this.mappaStanze;
	}

	/* metodo preso da Silvia */
	public void setMappaStanzaNelLabirinto(Map<String, Stanza> mappaStanze) {
		this.mappaStanze = mappaStanze;
	}

	/* metodo preso da Silvia */
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
	/* questo Silvia l'ha messo più avanti, non qui, ma va bene uguale metterlo qui?*/
	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza i = new Stanza(stanzaIniziale);
		/* come ha fatto Silvia *
		labirinto.setStanzaIniziale(stanzaIniziale);
		ultimaStanza=stanzaIniziale;
		mappaStanzaNelLabirinto.put(nomeStanzaIniziale,stanzaIniziale);
		/* come ha fatto Silvia però con i nomi del mio progetto */
		labirinto.setStanzaIniziale(i);
		ultimaStanzaAggiunta=i; //qui devo mettere ultimaStanzaAggiunta?
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
		/* come ha fatto Silvia *
		labirinto.setStanzaVincente(stanzaVincente);
		ultimaStanza=stanzaVincente;
		mappaStanzaNelLabirinto.put(nomeStanzaVincente,stanzaVincente);
		/* come ha fatto Silvia però con i nomi del mio progetto */
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

	/*fatto nel modo di Silvia */
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
	}

	/* questo è come lo ha fatto Silvia */
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		ultimaStanza.addAttrezzo(new Attrezzo(nomeAttrezzo,peso));//qua devo mettere ultimaStanzaAggiunta
		return this;
	}

	/* metodo momentaneamente aggiunto perchè consigliato da bing per fare in modo che 
	 * ultimaStanzaAggiunta sia la stanza iniziale prima di chiamare addAttrezzo nel test*
	public LabirintoBuilder setUltimaStanzaAggiunta(Stanza ultimaStanzaAggiunta) {
		this.ultimaStanzaAggiunta = ultimaStanzaAggiunta;
		return this;
	}per ora questo non lo metto, Silvia non lo ha fatto*/
	
	/* metodo che ha solo Silvia, noi non lo avevamo fatto */
	public LabirintoBuilder addMago(String nomeMago, String presentazione, String nomeAttrezzoDelMago, int peso) {
		Attrezzo attrezzoDelMago= new Attrezzo(nomeAttrezzoDelMago, peso);
		ultimaStanza.setPersonaggio(new Mago(nomeMago, presentazione, attrezzoDelMago));
		return this;
	}
 
	/* metodo che ha solo Silvia, noi non lo avevamo fatto */
	public LabirintoBuilder addStrega(String nomeStrega, String presentazione) {
		ultimaStanza.setPersonaggio(new Strega(nomeStrega, presentazione));
		return this;
	}
	
	/* metodo che ha solo Silvia, noi non lo avevamo fatto */
	public LabirintoBuilder addCane(String nomeCane, String presentazione, String nomeAttrezzoDelCane, int pesoAttrezzoDelCane, String nomeCiboPreferitoDelCane, int pesoCiboPreferitoDelCane) {
		Attrezzo attrezzoDelCane= new Attrezzo(nomeAttrezzoDelCane, pesoAttrezzoDelCane);
		Attrezzo ciboPreferitoDelCane= new Attrezzo(nomeCiboPreferitoDelCane, pesoCiboPreferitoDelCane);
		ultimaStanza.setPersonaggio(new Cane(nomeCane, presentazione, attrezzoDelCane, ciboPreferitoDelCane));
		return this;
	}

	/* come avevamo fatto noi 
	public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiecente, String direzione) {
		Stanza c = this.mappaStanze.get(stanzaCorrente);
		Stanza a = this.mappaStanze.get(stanzaAdiecente);
		c.impostaStanzaAdiacente(direzione, a);
		return this;
	}
	
	/*come ha fatto SIlvia */
	public LabirintoBuilder addAdiacenza(String nomeStanzaDoveMettoAdiacenza,String nomeStanzaAdiacente, String direzione) {
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
		ultimaStanza=stanzaMagica;
		return this;
	}

	/* come avevamo fatto noi
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
		Stanza stanza = new StanzaBuia(nome,attrezzoPerVedere);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}*/
	
	/*come ha fatto SIlvia */
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzoCheIllumina) {
		StanzaBuia stanzaBuia =new StanzaBuia(nomeStanza,attrezzoCheIllumina);
		mappaStanze.put(nomeStanza,stanzaBuia);
		ultimaStanza=stanzaBuia;
		return this;
	}
 

	public LabirintoBuilder addStanzaBloccata(String nome, String attrezzoSbloccante, String direzioneBloccata) {
	//	Stanza stanza = new StanzaBloccata(nome, attrezzoSbloccante, direzioneBloccata); noi avevamo fatto così, con tipo statico diverso dal tipo dinamico a quanto pare, 
	//	ma Silvia mettere sia come tipo statico che come tipo dinamico mette la stessa cosa, quindi qui sotto lo faccio come Silvia
		StanzaBloccata stanza = new StanzaBloccata(nome, attrezzoSbloccante, direzioneBloccata);
	//	this.UltimaStanzaAggiuntaEAggiorna(stanza); come avevamo fatto noi
		/* come ha fatto Silvia*/
		mappaStanze.put(nomeStanza,stanzaBloccata);//nelle parentesi che ce devo mette?
		ultimaStanza=stanzaBloccata;
		return this;
	}
/*per ora questi non li metto, Silvia mi sembra non li ha messi
	public void UltimaStanzaAggiuntaEAggiorna(Stanza stanza) {
		this.ultimaStanzaAggiunta = stanza;
		this.mappaStanze.put(stanza.getNome(), stanza);
	}

	public Map<String, Stanza> getListaStanze() {
		return new HashMap<>(this.mappaStanze);
	}*/
}
