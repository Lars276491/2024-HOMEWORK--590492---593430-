package it.uniroma3.diadia.comandi;
//import it.uniroma3.diadia.*;
import it.uniroma3.diadia.IO; // su notion mette così
import it.uniroma3.diadia.IOConsole; //l'ho importato io perchè sennò quando facevamo IO io=new IOConsole() dava errore
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	/* su notion mette così*/
//	static final public String[] ELENCO_COMANDI = {"vai", "aiuto", "fine","prendi", "posa", "guarda"};
//	private IO io;  
//	private final static String NOME = "aiuto";
	/*questo è come abbiamo fatto noi*/
	IO io= new IOConsole();
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};

	/* come fa notion */
	/*
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< ELENCO_COMANDI.length; i++) 
			io.mostraMessaggio(ELENCO_COMANDI[i]+" ");
		io.mostraMessaggio("");
	}*/

	/* come abbiamo fatto noi*/
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
	/* questo lo fa su notion 
	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}*/

	@Override
	public void setParametro(String parametro) {
		// This command does not require a parameter, so this method does nothing
		// TODO Auto-generated method stub // come sta scritto su notion
	}
}
