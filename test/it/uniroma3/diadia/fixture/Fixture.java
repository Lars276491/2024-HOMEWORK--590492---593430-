package it.uniroma3.diadia.fixture;

import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Fixture {
	public static IOSimulator creaSimulazionePartitaEGiocaEasy(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = new LabirintoBuilder("labirinto")
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.addAdiacenza("Biblioteca", "Atrio", Direzione.sud)
				.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		return io;
	}

	public static IOSimulator creaSimulazionePartitaEGiocaHard(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = new LabirintoBuilder("labirinto")
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.addAdiacenza("Biblioteca", "Atrio", Direzione.sud)
				.addStanza("Bagno")
				.addAdiacenza("Bagno", "Atrio", Direzione.sud)
				.addAdiacenza("Atrio", "Bagno", Direzione.nord)
				.addStanza("Studio")
				.addAdiacenza("Studio", "Atrio", Direzione.est)
				.addAdiacenza("Atrio", "Studio", Direzione.ovest)
				.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		return io;
	}

	public static IOSimulator creaSimulazionePartitaEGiocaMonolocale(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto monolocale = new LabirintoBuilder("labirinto")
				.addStanzaIniziale("salotto") 
				.addStanzaVincente("salotto") 
				.getLabirinto();
		DiaDia gioco = new DiaDia(monolocale, io);
		gioco.gioca();
		return io;
	}
	
	
	public static IOSimulator creaSimulazionePartitaEGiocaBilocale(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto bilocale = new LabirintoBuilder("labirinto")
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10) 
				.addAdiacenza("salotto", "camera", Direzione.nord) 
				.getLabirinto();
		DiaDia gioco = new DiaDia(bilocale, io);
		gioco.gioca();
		return io;
	}
	
	public static IOSimulator creaSimulazionePartitaEGiocaTrilocale(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto trilocale = new LabirintoBuilder("labirinto")
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola",1) 
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", Direzione.nord)
				.addAdiacenza("cucina", "camera", Direzione.est)
				.getLabirinto();
		DiaDia gioco = new DiaDia(trilocale, io);
		gioco.gioca();
		return io;
	}

	public static Attrezzo creaAttrezzoEAggiugniAStanza(Stanza stanzaDaRiempire, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		stanzaDaRiempire.addAttrezzo(attrezzo);
		return attrezzo;
	}

}
