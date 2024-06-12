package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private String bloccata;
	private String chiave;

	public StanzaBloccata(String nome, String bloccata, String chiave) {
		super(nome);
		this.chiave = chiave;
		this.bloccata = bloccata;

	}

	public String getDirezioneBloccata() {
		return bloccata;
	}

	public void setDirezioneBloccata(String bloccata) {
		this.bloccata = bloccata;
	}

	public String getChiave() {
		return chiave;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (this.hasAttrezzo(this.chiave) || !this.bloccata.equals(direzione))
			return super.getStanzaAdiacente(direzione);
		else
			return this;
	}

	@Override
	public String getDescrizione() {
		String s = super.getDescrizione();
		if (!this.hasAttrezzo(this.chiave))
			s = s + '\n' + "La direzione " + this.bloccata + " è bloccata." + '\n'
					+ "Posa l'attrezzo giusto per sbloccarla";
		else
			s = s + '\n' + "La direzione " + this.bloccata + " è sbloccata.";

		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		StanzaBloccata that = (StanzaBloccata) obj;
		return super.equals(obj) && this.chiave.equals(that.getChiave()) && this.bloccata.equals(that.getDirezioneBloccata());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + this.chiave.hashCode() + this.bloccata.hashCode();
	}
}
