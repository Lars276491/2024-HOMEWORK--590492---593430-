package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaProtetta extends StanzaProtetta {

	private String attrezzoChiave;
	
	public StanzaBuiaProtetta(String nome, String chiave) {
		super(nome);
		this.attrezzoChiave = chiave;
	}

	public String getChiave() {
		return this.attrezzoChiave;
	}
	
	@Override
	public String getDescrizione() {
		StringBuilder descrizione = new StringBuilder("Qui c'è buio pesto!");

		// se la stanza contiene "Lanterna"
		
		if (this.attrezzi.contains(new Attrezzo(attrezzoChiave, 0)))
			return this.toString();
		// se la stanza non contiene "Lanterna"
		return descrizione.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		StanzaBuiaProtetta that = (StanzaBuiaProtetta) obj;
		return this.nome.equals(that.getNome()) && this.attrezzoChiave.equals(that.getChiave());
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.nome.hashCode() + this.attrezzoChiave.hashCode();
	}
}
