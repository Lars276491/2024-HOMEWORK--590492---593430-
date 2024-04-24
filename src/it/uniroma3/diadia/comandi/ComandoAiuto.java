package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	
	IO io= new IOConsole();
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
	}

	@Override
	public String getNome() {
		return "Comando aiuto";
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
