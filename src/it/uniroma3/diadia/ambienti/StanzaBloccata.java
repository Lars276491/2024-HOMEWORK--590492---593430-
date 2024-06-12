package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	public Direzione nomeDirezioneBloccata;
	public String nomeAttrezzoSbloccante;

	public StanzaBloccata(String nome, Direzione nomeDirezioneBloccata, String nomeAttrezzoSbloccante) {
		super(nome);
		this.nomeDirezioneBloccata=nomeDirezioneBloccata;
		this.nomeAttrezzoSbloccante=nomeAttrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione dir) {
		if(dir.equals(nomeDirezioneBloccata) && !this.hasAttrezzo(nomeAttrezzoSbloccante))
			return this;
		else
			return super.getStanzaAdiacente(dir);
	}

	@Override
	public String getDescrizione() {
		String bloccata="La direzione "+ nomeDirezioneBloccata +" è bloccata. Prendi il " + nomeAttrezzoSbloccante + " e posalo nella stanza.";
		if(this.hasAttrezzo(nomeAttrezzoSbloccante))
			return super.getDescrizione();
		else
			return bloccata;
	}

}