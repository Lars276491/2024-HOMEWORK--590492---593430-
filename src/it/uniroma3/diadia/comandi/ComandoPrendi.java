package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private String nomeAttrezzo;

	public ComandoPrendi(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	public ComandoPrendi() {
		this(null);
	}
	
	/**
	 * Comando prendi
	 * 
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita, IO io) {
		if (this.nomeAttrezzo == null)
			io.mostraMessaggio("Che attrezzo vuoi prendere?");
		if (partita.getStanzaCorrente().hasAttrezzo(this.nomeAttrezzo) != true)
			io.mostraMessaggio("Attrezzo non è presente nella stanza");
		else {
			Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(this.nomeAttrezzo);
			if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
				partita.getStanzaCorrente().removeAttrezzo(attrezzo);
				io.mostraMessaggio("Hai preso l'oggetto dalla stanza e inserito nella borsa");
			} else {
				io.mostraMessaggio("La borsa non può contenere questo attrezzo se no è troppo pesante");
			}
		}
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
		return "Comando prendi";
	}

}
