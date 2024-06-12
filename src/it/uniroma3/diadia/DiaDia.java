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
	
//	static final public String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};

	private Partita partita;
	private IO io;
/*
	public DiaDia(IO io) {
		this.io = io;
		Labirinto labirinto = Labirinto.newBuilder().getLabirinto(); 
		this.partita = new Partita(labirinto); 
	}*/

	public DiaDia(IO io, Labirinto labirinto) {
		this.io=io;
		this.partita = new Partita(labirinto); 
	}

	public void gioca() throws Exception {
		String istruzione; 
		//		Scanner scannerDiLinee;
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = io.leggiRiga();

		}while (!processaIstruzione(istruzione) );

	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	/*
	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			System.out.println("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			System.out.println("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}*/
	
	/**System.in
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(/*this.io)*/);
		/*
		try {*/
		comandoDaEseguire = factory.costruisciComando(istruzione);
		/*
		} catch (Exception cne) {
			comandoDaEseguire = factory.costruisciComando("NonValido");
		} catch (Exception npe) {
			comandoDaEseguire = factory.costruisciComando("NonValido");
		}*/
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}
	//	private boolean processaIstruzione(String istruzione) {
	//		Comando comandoDaEseguire = new Comando(istruzione);
	//		if(comandoDaEseguire.getNome()==null) {
	//			io.mostraMessaggio("Non hai inserito alcun comando!");
	//			return false;
	//		}
	//		else if (comandoDaEseguire.getNome().equals("fine")) {
	//			this.fine(); 
	//			return true;
	//		} 
	//		else if (comandoDaEseguire.getNome().equals("prendi"))
	//			this.prendi(comandoDaEseguire.getParametro());
	//		else if (comandoDaEseguire.getNome().equals("posa"))
	//			this.posa(comandoDaEseguire.getParametro());
	//		else if (comandoDaEseguire.getNome().equals("vai"))
	//			this.vai(comandoDaEseguire.getParametro());
	//		else if (comandoDaEseguire.getNome().equals("aiuto"))
	//			this.aiuto();
	//		else
	//			io.mostraMessaggio("Comando sconosciuto");
	//		if (this.partita.vinta()) {
	//			io.mostraMessaggio("Hai vinto!");
	//			return true;
	//		} else
	//			return false;
	//	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	//	private void aiuto() {
	//		for(int i=0; i< elencoComandi.length; i++) 
	//			io.mostraMessaggio(elencoComandi[i]+" ");
	//		io.mostraMessaggio("");
	//	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	//	private void vai(String direzione) {
	//		if(direzione==null)
	//			io.mostraMessaggio("Dove vuoi andare ?");
	//		Stanza prossimaStanza = null;
	//		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
	//		if (prossimaStanza == null)
	//			io.mostraMessaggio("Direzione inesistente");
	//		else {
	//			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
	//			int cfu = this.partita.getGiocatore().getCfu();
	//			this.partita.getGiocatore().setCfu(cfu--);
	//		}
	//		io.mostraMessaggio("Stanza corrente:");
	//		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	//		io.mostraMessaggio("Borsa:");
	//		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	//
	//	}

	//	private void prendi(String nomeAttrezzo) {
	//		Attrezzo a = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
	//		this.partita.getGiocatore().getBorsa().addAttrezzo(a);
	//		this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(a);
	//	}

	//	private void posa(String nomeAttrezzo) {
	//		Attrezzo a = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
	//		
	//		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(a);
	//		this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
	//	}
	/**
	 * Comando "Fine".
	 * @throws Exception 
	 */
	//	private void fine() {
	//		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	//	}

	public static void main(String[] argc) throws Exception {
	//	Scanner scanner = new Scanner(System.in);
		IO console = new IOConsole(/*scanner*/);
//		List pippo = new ArrayList();
//		pippo.add(new Integer(0));
//		pippo.add(new String("a"));
//		System.out.println(pippo);
		
		Labirinto labirinto = Labirinto.newBuilder("file.txt").getLabirinto();
//										new LabirintoBuilder()
//										.addStanzaIniziale("Atrio")
//										.addCane("Rex", "Sono un cane, bau!")
//										.addAttrezzo("martello", 3)
//										.addStanzaVincente("Biblioteca")
//										.addAdiacenza("Atrio", "Biblioteca", "nord")
//										.addStanza("Bagno")
//										.addAdiacenza("Atrio", "Bagno", "sud")
//										.addAdiacenza("Bagno", "Atrio", "nord")
//										.addStrega("Varana", "Ajo, sono una strega sarda!")
//										.addStanza("Studio")
//										.addAttrezzo("pala", 2)
//										.addAttrezzo("scalpello", 34)
//										.addAdiacenza("Studio", "Bagno", "sud")
//										.addAdiacenza("Bagno", "Studio", "nord")
//										//.addMago("Merlino", "Ciao, sono mago Merlino", new Attrezzo("bacchetta", 3))
//										.getLabirinto();
		DiaDia gioco = new DiaDia(console, labirinto);
		gioco.gioca();
	//	scanner.close();
	}

}
/*
	public static void main(String[] args) throws Exception {
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
				.getLabirinto();*
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
		
		
	}
	

}*/
