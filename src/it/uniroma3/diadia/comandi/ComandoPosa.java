package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.*;

public class ComandoPosa implements Comando{

	private String nomeAttrezzo;


	public ComandoPosa(String parametro) {
		this.nomeAttrezzo=parametro;
	}

	IO io = new IOConsole();	

	@Override
	public void esegui(Partita partita) {
		if(partita == null)
			return;
		if(nomeAttrezzo==null)
			io.mostraMessaggio("Che attrezzo vuoi posare");
		else {
			Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			if(partita.getStanzaCorrente().addAttrezzo(attrezzo))
				io.mostraMessaggio("L'attrezzo è stato posato nella stanza");
			else {
				partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
				io.mostraMessaggio("Impossibile posare attrezzo");
			}
		}
	}
	

	@Override
	public String getNome() {
		return "Comando posa";
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
