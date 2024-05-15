package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	public String nomeDirezioneBloccata;
	public String nomeAttrezzoSbloccante;

	public StanzaBloccata(String nome, String nomeDirezioneBloccata, String nomeAttrezzoSbloccante) {
		super(nome);
		this.nomeDirezioneBloccata=nomeDirezioneBloccata;
		this.nomeAttrezzoSbloccante=nomeAttrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(String dir) {
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