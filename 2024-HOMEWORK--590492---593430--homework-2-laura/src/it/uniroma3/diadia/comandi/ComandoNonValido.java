package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{

	@Override
	public void esegui(Partita partita) {
		System.out.println("Comando non valido");
	}

	@Override
	public String getNome() {
		return "Comando non valido";
	}

	@Override
	public String getParametro() {
		return null; // This command does not require a parameter
	}

	@Override
	public boolean sconosciuto() {
		return true; // This command is unknown
	}

	@Override
	public void setParametro(String parametro) {
		// This command does not require a parameter, so this method does nothing
	}
}
