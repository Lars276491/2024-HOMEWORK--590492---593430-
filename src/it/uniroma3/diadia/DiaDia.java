package it.uniroma3.diadia;

import java.net.URISyntaxException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO e 589489 e 589300 (da un'idea di Michael Kolling and
 *         David J. Barnes)
 * 
 * @version v1.0
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO io;

	public DiaDia(Labirinto labirinto, IO console) {
		this.partita = new Partita(labirinto);
		this.io = console;
	}

	public DiaDia(IO console) {
		this(Labirinto.newBuilder().getLabirinto(), console);
	}

	public DiaDia(Partita partita, IO console) {
		this.partita = partita;
		this.io = console;
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
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		if (istruzione.isEmpty())
			return false;
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita, this.io);
		if (this.partita.vinta())
			io.mostraMessaggio("Complimenti, hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i tuoi CFU...");

		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		Scanner scannerDiLinee = new Scanner(System.in);
		try {
			IO console = new IOConsole(scannerDiLinee);
			Labirinto labirinto;
			labirinto = Labirinto.newBuilder().getLabirinto();
			DiaDia gioco = new DiaDia(labirinto, console);
			gioco.gioca();
		} finally {
			scannerDiLinee.close();
		}
	}
}
