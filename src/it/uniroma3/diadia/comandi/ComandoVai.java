package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
public class ComandoVai extends AbstractComando {
	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(super.getParametro() == null) {
			System.out.println("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(super.getParametro());
		if(prossimaStanza == null) {
			System.out.println("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		System.out.println(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1); 
	}
	@Override
	public String getNome() {
		return "Comando vai";
	}
}