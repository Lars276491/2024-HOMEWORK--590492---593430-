package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	
	public ComandoNonValido() {
		super(null);
	}

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Comando sconosciuto");

	}

	@Override
	public String getNome() {
		return "Comando non valido";
	}

}
