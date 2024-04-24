package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{
	private String nomeAttrezzo;

	public ComandoPrendi(String parametro) {
		this.nomeAttrezzo=parametro;
	}

	IO io = new IOConsole();	

	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo==null)
			io.mostraMessaggio("Che attrezzo vuoi prendere");
		Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);

		if(!partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) 
			io.mostraMessaggio("Impossibile prendere attrezzo");
		else {
			partita.getStanzaCorrente().removeAttrezzo(attrezzo);
			io.mostraMessaggio("L'attrezzo è stato aggiunto alla borsa");
		}
	}

	@Override
	public String getNome() {
		return "Comando prendi";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public boolean sconosciuto() {
		return this.nomeAttrezzo == null || this.nomeAttrezzo.trim().isEmpty();
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
}
