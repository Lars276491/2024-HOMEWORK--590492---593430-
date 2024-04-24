package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{

	private String parametro;

	@Override
	public void esegui(Partita partita) {
		System.out.println(partita.getGiocatore().getBorsa().toString());
		System.out.println(partita.getStanzaCorrente().toString());
	}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}

	@Override
	public boolean sconosciuto() {
		return false;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
}
