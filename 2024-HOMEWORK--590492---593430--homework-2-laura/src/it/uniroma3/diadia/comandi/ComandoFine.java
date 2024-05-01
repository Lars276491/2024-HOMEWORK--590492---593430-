package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{

	IO io= new IOConsole();
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Grazie di aver giocato!");
	}

	@Override
	public String getNome() {
		return "Comando fine";
	}

	@Override
	public String getParametro() {
		return null; // This command does not require a parameter
	}

	@Override
	public boolean sconosciuto() {
		return false; // This command is not unknown
	}

	@Override
	public void setParametro(String parametro) {
		// This command does not require a parameter, so this method does nothing
	}
}
