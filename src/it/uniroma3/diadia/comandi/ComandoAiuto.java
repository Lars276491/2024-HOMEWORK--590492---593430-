package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO; 
import it.uniroma3.diadia.IOConsole; 
import it.uniroma3.diadia.Partita;
public class ComandoAiuto extends AbstractComando{
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
}
