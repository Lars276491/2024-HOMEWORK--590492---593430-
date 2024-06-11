package it.uniroma3.diadia;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and
 *         David J. Barnes)
 * 
 * @version v1.0
 */
public class DiaDia {
	
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final public String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};

	private Partita partita;
	private IO io;


	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto);
		this.io = io;
	}

	public DiaDia(IO io) {
		this.io = io;
		Labirinto labirinto = Labirinto.newBuilder().getLabirinto(); 
		this.partita = new Partita(labirinto); 
	}

	public DiaDia(IO io, Labirinto labirinto) {
		this.io=io;
		this.partita = new Partita(labirinto); 
	}

	public void gioca() {
		String istruzione; 
		

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
			
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
	//	FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica(); questo commento poi lo si può levare perchè dobbiamo sostituire fabComandiFisarmonica con la riflessiva
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
				comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			System.out.println("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())

			System.out.println("Hai esaurito i CFU...");

		return this.partita.isFinita();
	}
    
	

	public static void main(String[] args) {
		IO io = new IOConsole();
		Labirinto labirinto=null;
		try {
			labirinto = Labirinto.newBuilder("File.txt").getLabirinto();
		} catch (FileNotFoundException | FormatoFileNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	Labirinto labirinto=new LabirintoBuilder()
				.addStanzaIniziale("LabCampusOne")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("LabCampusOne", "Biblioteca", Direzione.ovest)
				.getLabirinto();*/
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
		
		
	}
	

}
