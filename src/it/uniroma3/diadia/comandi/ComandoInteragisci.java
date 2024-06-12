package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci implements Comando {
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private String messaggio;

	public ComandoInteragisci(String msg) {
		this.messaggio = msg;
	}

	public ComandoInteragisci() {
		this(null);
	}

	@Override
	public void esegui(Partita partita, IO io) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			io.mostraMessaggio(this.messaggio);

		} else io.mostraMessaggio(MESSAGGIO_CON_CHI);
	}

	public String getMessaggio() {
		return this.messaggio;
	}

	@Override
	public void setParametro(String parametro) {
		this.messaggio = parametro;
	}

	@Override
	public String getParametro() {
		return this.messaggio;
	}
	
	@Override
	public String getNome() {
		return "Comando interagisci";
	}

}