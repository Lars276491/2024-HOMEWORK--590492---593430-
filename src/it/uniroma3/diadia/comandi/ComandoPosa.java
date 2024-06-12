package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {

	private String nomeAttrezzo;

	public ComandoPosa(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	public ComandoPosa() {
		this(null);
	}
	
	/**
	 * Comando "Posa".
	 * 
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita, IO io) {
		if (partita == null)
			return;
		
		if (nomeAttrezzo == null)
			io.mostraMessaggio("Che attrezzo vuoi posare?");
		if (partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			if (partita.getStanzaCorrente().addAttrezzo(attrezzo) == false) {
				io.mostraMessaggio("La stanza ha troppi oggetti. Non lo puoi posare");
				partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			} else
				io.mostraMessaggio("L'attrezzo è stato posato nella stanza");
		} else
			io.mostraMessaggio("Attrezzo non è presente nella borsa");

	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "Comando posa";
	}
}
