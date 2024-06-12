package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.*;

public class LabirintoBuilderTest {
	private LabirintoBuilder labirintoBuilder;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@Before
	public void setUp() throws Exception {
		labirintoBuilder = new LabirintoBuilder();
	}

	@Test
	public void testMonolocale() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada",1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
		assertEquals("spada",monolocale.getStanzaIniziale().getAttrezzo("spada").getNome());
		assertEquals("spadina",monolocale.getStanzaIniziale().getAttrezzo("spadina").getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("spada",1)
				.addAttrezzo("spada",1)
				.getLabirinto();
		int size = monolocale.getStanzaIniziale().getAttrezzi().size();
		assertTrue(size==1);
		assertEquals(Arrays.asList(new Attrezzo("spada",1)),monolocale.getStanzaIniziale().getAttrezzi());
	}
	
	@Test
	public void testBilocale() {
		Labirinto bilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaIniziale().getStanzaAdiacente("nord"));
		assertTrue(bilocale.getStanzaIniziale().getDirezioni().contains(Direzioni.nord));
		assertTrue(bilocale.getStanzaVincente().getDirezioni().contains(Direzioni.sud));;
	}
	
	@Test
	public void testTrilocale(){
		Labirinto trilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenza("biblioteca", nomeStanzaIniziale, "nord")
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenza(nomeStanzaVincente,"biblioteca" , "ovest")
				.getLabirinto();	
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata() {
				labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza generica")
				.addStanza("stanza generica")
				.addAdiacenza(nomeStanzaIniziale, "stanza generica", "nord")
				.getLabirinto();
		assertTrue(labirintoBuilder.getListaStanze().size()<=2);
	}
	
	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza 1")
				.addStanza("stanza 2")
				.addStanza("stanza 3")
				.addStanza("stanza 4")
				.addStanza("stanza 5")
				.addAdiacenza(nomeStanzaIniziale, "stanza 1", "nord")
				.addAdiacenza(nomeStanzaIniziale, "stanza 2", "ovest")
				.addAdiacenza(nomeStanzaIniziale, "stanza 3", "sud")
				.addAdiacenza(nomeStanzaIniziale, "stanza 4", "est")
				.addAdiacenza(nomeStanzaIniziale, "stanza 5", "nord-est") // non dovrebbe essere aggiunta
				.getLabirinto();
				Stanza test = new Stanza("stanza 5");
		assertNull(maze.getStanzaIniziale().getStanzaAdiacente("nord-est"));
		assertTrue(maze.getStanzaIniziale().getMapStanzeAdiacenti().size()<=4);
		assertTrue(!maze.getStanzaIniziale().getMapStanzeAdiacenti().containsValue(test));
		Map<Direzioni,Stanza> mappa = new HashMap<>();
		mappa.put(Direzioni.nord, new Stanza("stanza 1"));
		mappa.put(Direzioni.ovest, new Stanza("stanza 2"));
		mappa.put(Direzioni.sud, new Stanza("stanza 3"));
		mappa.put(Direzioni.est, new Stanza("stanza 4"));
		assertEquals(mappa,maze.getStanzaIniziale().getMapStanzeAdiacenti());
	}
	
	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale")
				.getLabirinto();
		assertEquals("nuova iniziale",maze.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = this.labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		assertEquals(attrezzo,maze.getStanzaIniziale().getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		assertTrue(this.labirintoBuilder.getListaStanze().get(labirintoBuilder.getListaStanze().indexOf(new Stanza(nomeStanza))).getAttrezzi().contains(new Attrezzo (nomeAttrezzo,peso)));
		assertEquals(new Attrezzo(nomeAttrezzo,peso),this.labirintoBuilder.getListaStanze().get(labirintoBuilder.getListaStanze().indexOf(new Stanza(nomeStanza))).getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		List<Attrezzo> attrezzi = this.labirintoBuilder.getListaStanze().get(labirintoBuilder.getListaStanze().indexOf(new Stanza(nomeStanza))).getAttrezzi();
		assertEquals(attrezzo,attrezzi.get(attrezzi.indexOf(attrezzo)));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		List<Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(listaStanze.indexOf(new Stanza(nomeStanza1))).getAttrezzo(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(listaStanze.indexOf(new Stanza(nomeStanza1))).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test  //verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		List<Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(listaStanze.indexOf(new Stanza(nomeStanza2))).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(listaStanze.indexOf(new Stanza(nomeStanza2))).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo2, peso2);
		List<Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(listaStanze.indexOf(new Stanza(nomeStanza1))).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(listaStanze.indexOf(new Stanza(nomeStanza2))).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica) this.labirintoBuilder.getListaStanze().get(labirintoBuilder.getListaStanze().size()-1);
		assertTrue(stanzaMagica.isMagica());
	}
	
	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2*2;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		List<Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new StanzaMagica(nomeStanzaMagica, sogliaMagica), listaStanze.get(0));
		assertEquals(new Attrezzo(nomeAttrezzo2Inv,peso2_x2), listaStanze.get(0).getAttrezzo(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1), listaStanze.get(0).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", "nord", "chiave").addAttrezzo("chiave", 1)
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente,labirintoBuilder.getListaStanze().get(1).getStanzaAdiacente("nord"));	
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", "nord", "chiave")
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", "nord", "chiave");
		//Asserisce che in caso di mancanza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		assertEquals(stanzaBloccata,labirintoBuilder.getListaStanze().get(1).getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testLabirintoCompletoConTutteLeStanze() {
		
		Labirinto labirintoCompleto = this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
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
		assertEquals(nomeStanzaIniziale, labirintoCompleto.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, labirintoCompleto.getStanzaVincente().getNome());
		Stanza corridoio = labirintoCompleto.getStanzaIniziale().getStanzaAdiacente("nord");
		assertEquals("corridoio",corridoio.getNome());
		assertTrue(corridoio.getDirezioni().containsAll(Arrays.asList(Direzioni.ovest,Direzioni.est,Direzioni.nord,Direzioni.sud)));
		Map<Direzioni,Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put(Direzioni.nord,new StanzaBloccata("corridoio bloccato","nord","chiave"));
		mapAdiacenti.put(Direzioni.sud,new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put(Direzioni.est,new StanzaMagica("stanza magica", 1));
		mapAdiacenti.put(Direzioni.ovest,new StanzaBuia("stanza buia","lanterna"));
		assertEquals(mapAdiacenti.keySet(),corridoio.getMapStanzeAdiacenti().keySet());
		assertTrue(new ArrayList<Stanza>(mapAdiacenti.values()).containsAll(corridoio.getMapStanzeAdiacenti().values()));
		Attrezzo a1 = new Attrezzo("chiave",1);
		Attrezzo a2 = new Attrezzo("lanterna",1);
		assertEquals(Arrays.asList(a1,a2),corridoio.getAttrezzi());
	}

	/*TEST ESTESI DI DOMINIO
	 * 			|
	 * 			|
	 * 			V				*/


	//raccoglie tutte le asserzioni necessarie a verificare l'intero stato della partita in un dato momento
	public void asserzioniVerificaStatoFinalePartita(Partita p, int expectedCFU, int expectedPesoBorsa) {
		assertEquals(expectedCFU, p.getGiocatore().getCfu());
		assertEquals(expectedPesoBorsa, p.getGiocatore().getBorsa().getPeso());

	}

	//creazione labirinti ad hoc
	public Labirinto trilocaleBloccato() {
		Labirinto l= new LabirintoBuilder()
				.addStanzaIniziale("iniziale").addAttrezzo("chiave", 1)
				.addStanzaBloccata("bloccata","est", "chiave")
				.addStanzaVincente("vincente")
				.addAdiacenza("iniziale", "bloccata", "nord")
				.addAdiacenza("bloccata", "iniziale", "sud")
				.addAdiacenza("bloccata", "vincente", "est")
				.addAdiacenza("vincente", "bloccata", "ovest")
				.getLabirinto();
		return l;
	}

	public Labirinto trilocaleBuio() {
		Labirinto l= new LabirintoBuilder()
				.addStanzaIniziale("iniziale").addAttrezzo("torcia", 1)
				.addStanzaBuia("buia", "torcia")
				.addStanzaVincente("vincente")
				.addAdiacenza("iniziale", "buia", "nord")
				.addAdiacenza("buia", "iniziale", "sud")
				.addAdiacenza("buia", "vincente", "est")
				.addAdiacenza("vincente", "buia", "ovest")
				.getLabirinto();
		return l;
	}

	public Labirinto complessoConCorridoi() {
		Labirinto l = new LabirintoBuilder()
				.addStanzaIniziale("iniziale")
				.addStanza("corridoio1")
				.addAdiacenza("iniziale", "corridoio1","sud")
				.addAdiacenza("corridoio1", "iniziale","nord")

				.addStanzaBuia("s1","faro").addAttrezzo("ascia", 2)
				.addAdiacenza("corridoio1", "s1", "est")
				.addAdiacenza("s1", "corridoio1", "ovest")

				.addStanza("s2").addAttrezzo("lingotti", 7)
				.addAdiacenza("corridoio1", "s2", "ovest")
				.addAdiacenza("s2", "corridoio1", "est")

				.addStanzaBloccata("corridoio2", "est", "ascia").addAttrezzo("pelucheGigante", 4)
				.addAdiacenza("corridoio1", "corridoio2", "sud")
				.addAdiacenza("corridoio2", "corridoio1", "nord")

				.addStanzaVincente("vincente")
				.addAdiacenza("corridoio2", "vincente", "est")
				.addAdiacenza("vincente", "corridoio2","ovest")

				.addStanzaMagica("s3",1).addAttrezzo("faro", 5)
				.addAdiacenza("corridoio2", "s3", "sud")
				.addAdiacenza("s3", "corridoio2", "nord")

				.getLabirinto();

		return l;
	}


	@Test
	public void testEstesoTrilocaleBloccato() {
		Labirinto labirinto = trilocaleBloccato();

		String[] seq = {"guarda","vai nord","vai est","vai sud","prendi chiave","vai nord","posa chiave","guarda","vai est"};
		IO io = new IOSimulator(Arrays.asList(seq));

		Partita partita = new Partita(labirinto);

		DiaDia diadia= new DiaDia(partita,io);
		diadia.gioca();

		asserzioniVerificaStatoFinalePartita(partita, 15, 0);

	}

	@Test
	public void testEstesoTrilocaleBuio() {
		Labirinto labirinto=trilocaleBuio();
		String[] seq1= {"vai nord","vai est"};
		String[] seq2= {"guarda","prendi torcia","vai nord","posa torcia","guarda","vai est"};

		Partita partita1 = new Partita(labirinto);
		Partita partita2 = new Partita(labirinto);

		//prima run
		IO io = new IOSimulator(Arrays.asList(seq1));

		DiaDia diadia = new DiaDia(partita1,io);
		diadia.gioca();
		asserzioniVerificaStatoFinalePartita(partita1, 18, 0);

		//seconda run
		io = new IOSimulator(Arrays.asList(seq2));

		diadia=new DiaDia(partita2,io);
		diadia.gioca();
		asserzioniVerificaStatoFinalePartita(partita2, 18, 0);

	}
	
	@Test
	public void testEstesoComplessoConCorridoi() {

		String[] seq1= {"vai sud","vai est", "vai ovest","vai ovest","prendi lingotti",
				"vai est","vai sud","vai sud","prendi faro","posa lingotti","prendi faro",
				"vai nord", "vai nord","vai est","posa faro","prendi ascia","vai ovest",
				"vai sud","posa ascia","prendi pelucheGigante", "vai est"};

		String[] seq2= {"vai sud","vai sud", "vai sud","prendi faro","vai nord",
				"vai nord","vai est","posa faro","prendi ascia","vai ovest",
				"vai ovest","prendi lingotti", "vai est", "vai sud", "posa ascia",
				"vai est"};
		
		String[] seq3= {"vai sud","vai est", "prendi ascia","vai ovest","vai sud","vai sud",
				"posa ascia","prendi faro","prendi ascia","guarda","prendi aicsa",
				"posa aicsa","posa faro","prendi ascia","vai nord","posa ascia","vai est"};
				
		
		Partita partita1 = new Partita(complessoConCorridoi());
		Partita partita2 = new Partita(complessoConCorridoi());
		Partita partita3 = new Partita(complessoConCorridoi());


		//run1
		IO io = new IOSimulator(Arrays.asList(seq1));

		DiaDia diadia= new DiaDia(partita1,io);
		diadia.gioca();
		asserzioniVerificaStatoFinalePartita(partita1, 7, 4);

		//run2
		io = new IOSimulator(Arrays.asList(seq2));

		diadia= new DiaDia(partita2,io);
		diadia.gioca();
		asserzioniVerificaStatoFinalePartita(partita2, 9, 7);

		//run3
		io = new IOSimulator(Arrays.asList(seq3));

		diadia= new DiaDia(partita3,io);
		diadia.gioca();
		asserzioniVerificaStatoFinalePartita(partita3, 13, 0);
	}
}
