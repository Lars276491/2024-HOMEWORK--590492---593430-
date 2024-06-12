package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{

	private String comando;

	public AbstractComando(String comando) {
		this.comando = comando;

	}
	
	public AbstractComando() {
		this(null);
	}
	

	@Override
	public abstract void esegui(Partita partita, IO io);
	
	@Override
	public void setParametro(String parametro) {
		this.comando = parametro;

	}

	@Override
	public String getParametro() {
		return this.comando;
	}
	
	@Override
	public abstract String getNome();
}
