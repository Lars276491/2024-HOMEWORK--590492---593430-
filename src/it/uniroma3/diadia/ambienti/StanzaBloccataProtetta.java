package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;

public class StanzaBloccataProtetta extends StanzaProtetta {

	private String direzioneBloccata;
	private String chiave;

	public StanzaBloccataProtetta(String nome, String direzioneBloccata, String chiave) {
		super(nome);
		this.chiave = chiave;
		this.direzioneBloccata = direzioneBloccata;

	}

	public String getDirezioneBloccata() {
		return direzioneBloccata;
	}

	public void setDirezioneBloccata(String direzioneBloccata) {
		this.direzioneBloccata = direzioneBloccata;
	}

	public String getChiave() {
		return chiave;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

	@Override
	public StanzaProtetta getStanzaAdiacente(String direzione) {
		if (!this.hasAttrezzo(this.chiave) && this.direzioneBloccata.equals(direzione))
			return this;
		return this.stanzeAdiacenti.get(direzione);
	}

	@Override
	public String getDescrizione() {
		String s = super.getDescrizione();
		if (!this.hasAttrezzo(this.chiave))
			s = s + '\n' + "La direzione " + this.direzioneBloccata + " è bloccata." + '\n'
					+ "Posa l'attrezzo giusto per sbloccarla";
		else
			s = s + '\n' + "La direzione " + this.direzioneBloccata + " è sbloccata.";

		return s;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		StanzaBloccataProtetta that = (StanzaBloccataProtetta) obj;
		return this.nome.equals(that.getNome()) && this.chiave.equals(that.getChiave()) && this.direzioneBloccata.equals(that.getDirezioneBloccata());
	}

	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.nome.hashCode() + this.chiave.hashCode() + this.direzioneBloccata.hashCode();
	}
}
