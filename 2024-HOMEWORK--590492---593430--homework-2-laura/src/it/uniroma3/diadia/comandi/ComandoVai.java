package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

import it.uniroma3.diadia.giocatore.Giocatore;//come fa su notion perchè poi usa Giocatore

public class ComandoVai implements Comando {
	private String direzione;

	public ComandoVai(String direzione) {
		this.direzione = direzione;
	}

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(this.direzione == null) {
			System.out.println("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(prossimaStanza == null) {
			System.out.println("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		System.out.println(partita.getStanzaCorrente().getNome());
		/* queste due righe le ho prese da notion */
		/*
		Giocatore giocatore = partita.getGiocatore();
		giocatore.setCfu(giocatore.getCfu() - 1);*/

		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1); //come abbiamo fatto noi
	}

	@Override
	public String getNome() {
		return "Comando vai";
	}


	@Override
	public boolean sconosciuto() {
		return this.direzione == null || this.direzione.trim().isEmpty();
	}
	@Override
	public String getParametro() {
		return this.direzione;
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	

}