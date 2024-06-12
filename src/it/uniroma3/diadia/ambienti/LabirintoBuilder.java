package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private List<Stanza> stanze;
	private List<AbstractPersonaggio> personaggi;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	public LabirintoBuilder() {
		this.labirinto = Labirinto.newBuilder().getLabirinto();
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
		Attrezzo a = new Attrezzo(nomeAttrezzo, Integer.parseInt(pesoAttrezzo));
		tipoPersonaggio = tipoPersonaggio.replace(tipoPersonaggio.charAt(0), Character.toUpperCase(tipoPersonaggio.charAt(0)));
		try {
			tipoPersonaggio = "it.uniroma3.diadia.personaggi."+tipoPersonaggio;
			AbstractPersonaggio p = (AbstractPersonaggio) Class.forName(tipoPersonaggio).newInstance();
			p.setNome(nomePersonaggio);
			p.setAttrezzo(a);
			this.personaggi.add(p);
			this.stanze.get(this.stanze.indexOf(new Stanza(nomeStanza))).setPersonaggio(p);
			return this;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException e) {
			return this;
		}
	}

	public List<AbstractPersonaggio> getPersonaggi() {
		return this.personaggi;
	}
}
