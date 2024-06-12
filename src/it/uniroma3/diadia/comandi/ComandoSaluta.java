package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	private static final String MESSAGGIO_CHI = "Chi dovrei salutare?...";
	private String messaggio;
	
	public ComandoSaluta(String msg) {
		this.messaggio = msg;
	}
	
	public ComandoSaluta() {
		this(null);
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.saluta();
			io.mostraMessaggio(this.messaggio);

		} else
			io.mostraMessaggio(MESSAGGIO_CHI);
		
	}

	@Override
	public String getNome() {
		return "Comando saluta";
	}
	
}
