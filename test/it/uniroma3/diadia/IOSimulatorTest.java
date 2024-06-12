package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class IOSimulatorTest {

	private final String nomeStanzaIniziale = "atrio";
	private final String nomeStanzaVincente = "biblioteca";

	@Test
	public void testPartitaBilocale() {
		String[] seq = {"guarda","vai nord"};
		IO io = new IOSimulator(Arrays.asList(seq));
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();
		io.mostraMessaggio("Partita vincente"+'\n');
		DiaDia diadia = new DiaDia(bilocale, io);
		diadia.gioca();
		io.mostraMessaggio("");
	}

	@Test
	public void testPartitaPersa0Cfu() {
		List<String> s = new ArrayList<>(20);
		for(int i = 0; i < 20; i++)
			s.add("vai est");

		IO io = new IOSimulator(s);
		io.mostraMessaggio("Partita perdente"+'\n');
		DiaDia diadia = new DiaDia(Labirinto.newBuilder().getLabirinto(), io);
		diadia.gioca();
		io.mostraMessaggio("");
	}

	@Test
	public void testPartitaOssoPreso() {
		String[] seq = {"guarda", "prendi osso", "guarda", "vai nord"};
		IO io = new IOSimulator(Arrays.asList(seq));
		io.mostraMessaggio("Partita con osso preso"+'\n');
		DiaDia diadia = new DiaDia(Labirinto.newBuilder().getLabirinto(), io);
		diadia.gioca();
		io.mostraMessaggio("");
	}

	@Test
	public void testPartitaOssoPresoEPosatoELanternaPresa() {
		String[] seq = {"guarda", "prendi osso", "guarda", "vai sud", "posa osso",
				"prendi lanterna", "guarda", "vai nord", "vai nord"};
		IO io = new IOSimulator(Arrays.asList(seq));
		io.mostraMessaggio("Partita con osso preso e posato"+'\n');
		DiaDia diadia = new DiaDia(Labirinto.newBuilder().getLabirinto(), io);
		diadia.gioca();
		io.mostraMessaggio("");
	}

	@Test
	public void testPartitaMonolocale() {
		IO io = new IOSimulator(new ArrayList<String>());
		io.mostraMessaggio("Partita monolocale");
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
		DiaDia diadia = new DiaDia(monolocale, io);
		diadia.gioca();
	}

	@Test
	public void testPartitaConStanzaBloccata() {
		String [] seq = {"guarda", "vai nord", "guarda", "vai nord", "vai sud",
				"guarda", "prendi chiave", "vai nord", "posa chiave", "vai nord"};
		IO io = new IOSimulator(Arrays.asList(seq));

		io.mostraMessaggio("Partita con stanza bloccata");

		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("chiave", 1)
				.addStanzaBloccata("cantina", "nord", "chiave")
				.addAdiacenza(nomeStanzaIniziale, "cantina", "nord")
				.addAdiacenza("cantina", nomeStanzaIniziale, "sud")
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("cantina", nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, "cantina", "sud").getLabirinto();

		DiaDia diadia = new DiaDia(labirinto, io);
		diadia.gioca();
	}

	@Test
	public void testPartitaConStanzaBuia() {
		String[] seq ={"guarda", "vai est", "guarda", "vai ovest", "guarda",
				"prendi lanterna", "vai est", "posa lanterna", "guarda", "vai nord"};
		IO io = new IOSimulator(Arrays.asList(seq));

		io.mostraMessaggio("Partita con stanza bloccata");

		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("lanterna", 1)
				.addStanzaBuia("cantina", "lanterna")
				.addAdiacenza(nomeStanzaIniziale, "cantina", "est")
				.addAdiacenza("cantina", nomeStanzaIniziale, "ovest")
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("cantina", nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, "cantina", "sud").getLabirinto();

		DiaDia diadia = new DiaDia(labirinto, io);
		diadia.gioca();
	}
	
	@Test
	public void testPartitaConTutteLeStanza() {
		String[] seq = {"guarda","prendi piombo", "vai nord", "guarda", "prendi chiave", "posa piombo",
				"prendi chiave", "prendi lanterna", "vai est", "posa chiave", "guarda", "prendi chiave",
				"posa chiave", "guarda", "prendi evaihc", "vai ovest", "vai nord","posa evaihc", "guarda",
				"prendi evaihc", "vai sud", "vai est", "posa evaihc", "guarda", "prendi chiave", "vai ovest",
				"vai ovest", "vai ovest", "guarda", "posa lanterna", "guarda", "vai est",
				"vai nord", "posa chiave", "vai nord", "guarda", "vai nord"};
		IO io = new IOSimulator(Arrays.asList(seq));
		
		Labirinto labirintoCompleto = new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("piombo", 10)
				.addStanzaVincente(nomeStanzaVincente)
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato","nord","chiave")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia","lanterna")
				.addStanza("Aula 1")
				.addAdiacenza(nomeStanzaIniziale, "corridoio", "nord")
				.addAdiacenza("corridoio", nomeStanzaIniziale, "sud")
				.addAdiacenza("corridoio", "corridoio bloccato", "nord")
				.addAdiacenza("corridoio bloccato", "corridoio", "sud")
				.addAdiacenza("corridoio bloccato", "Aula 1", "nord")
				.addAdiacenza("Aula 1", "corridoio bloccato", "sud")
				.addAdiacenza("Aula 1", nomeStanzaVincente,"nord")
				.addAdiacenza(nomeStanzaVincente, "Aula 1", "sud")
				.addAdiacenza("corridoio", "stanza magica", "est")
				.addAdiacenza("stanza magica", "corridoio", "ovest")
				.addAdiacenza("corridoio", "stanza buia", "ovest")
				.addAdiacenza("stanza buia", "corridoio", "est")
				.getLabirinto();
		
		DiaDia diadia = new DiaDia(labirintoCompleto, io);
		diadia.gioca();
	}

}
