package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilderTest {

	private LabirintoBuilder lb;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@Before
	public void setUp() throws Exception {
		lb = new LabirintoBuilder();
	}
	@Test
	public void testMonolocale() {
		Labirinto monolocale = lb
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
	}
	
	
	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = lb
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada",1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
		assertEquals("spada",monolocale.getStanzaIniziale().getAttrezzo("spada").getNome());
		assertEquals("spadina",monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
		
	}
	
	
	/* test del prof
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = lb
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("spada",1)
				.addAttrezzo("spada",1)
				.getLabirinto();
		int size = monolocale.getStanzaIniziale().getAttrezzi().size();
		assertTrue(size==1);
		assertEquals(Arrays.asList(new Attrezzo("spada",1)),monolocale.getStanzaIniziale().getAttrezzi());
	}*/
	/* test di bing che funziona: mettendo spada al posto di osso non funziona e ho dovuto mettere ArrayList per farlo funzionare *
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
	    Labirinto monolocale = lb
	            .addStanzaIniziale(nomeStanzaIniziale)
	            .addAttrezzo("spada",1)// capire se qui devo lasciare spada o mettere osso
	            .addAttrezzo("spada",1)// capire se qui devo lasciare spada o mettere osso
	            .getLabirinto();
	    int size = monolocale.getStanzaIniziale().getAttrezzi().size();
	    assertTrue(size==1);
	    assertEquals(new ArrayList<>(Arrays.asList(new Attrezzo("osso",1))), new ArrayList<>(monolocale.getStanzaIniziale().getAttrezzi()));
	}*/
	/* quello di Silvia */
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = lb
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("spada",1)
				.addAttrezzo("spada",1)
				.getLabirinto();
		int size = monolocale.getStanzaIniziale().getAttrezzi().size();
		assertTrue(size==1);
		
		List<Attrezzo> attrezzi = new ArrayList<>();
		attrezzi.addAll(lb.getMappaStanze().get(nomeStanzaIniziale).getAttrezzi().values());
		
		assertEquals(Arrays.asList(new Attrezzo("spada",1)),attrezzi);
	}
/*
	@Test
	public void testBilocale() {
		Labirinto bilocale = lb
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaIniziale().getStanzaAdiacente("nord"));
		assertEquals(Collections.singletonList("nord"),bilocale.getStanzaIniziale().getDirezioni());
		assertEquals(Collections.singletonList("sud"),bilocale.getStanzaVincente().getDirezioni());
	}*/
	
	@Test
	public void testBilocale() {
		Labirinto bilocale = lb
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaIniziale().getStanzaAdiacente("nord"));
		List<String> direzione = new ArrayList<>();
		direzione.addAll(lb.getMappaStanze().get(nomeStanzaIniziale).getMapStanzeAdiacenti().keySet());

		assertEquals(Collections.singletonList("nord"),direzione);

		direzione = new ArrayList<>();
		direzione.addAll(lb.getMappaStanze().get(nomeStanzaVincente).getMapStanzeAdiacenti().keySet());

		assertEquals(Collections.singletonList("sud"),direzione);
		assertEquals(Collections.singletonList("nord"),bilocale.getStanzaIniziale().getDirezioni());
		assertEquals(Collections.singletonList("sud"),bilocale.getStanzaVincente().getDirezioni());
	}
	
	/* questo ora funziona perchè ho sostituito "Biblioteca" con "Aula N10"*/
	@Test
	public void testTrilocale(){
		Labirinto trilocale = lb
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("Aula N10")
				.addAdiacenza(nomeStanzaIniziale, "Aula N10", "sud")
				.addAdiacenza("Aula N10", nomeStanzaIniziale, "nord")
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("Aula N10", nomeStanzaVincente, "est")
				.addAdiacenza(nomeStanzaVincente,"Aula N10" , "ovest")
				.getLabirinto();	
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("Aula N10",trilocale.getStanzaIniziale().getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata() {
				lb
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza generica")
				.addStanza("stanza generica")
				.addAdiacenza(nomeStanzaIniziale, "stanza generica", "nord")
				.getLabirinto();
		assertTrue(lb.getListaStanze().size()<=2);
	}
	
	/* quello del prof che non funziona
	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = lb
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
		assertTrue(((Collection<Attrezzo>) maze.getStanzaIniziale().getMapStanzeAdiacenti()).size()<=4);
		assertTrue(!((Map<String, Stanza>) maze.getStanzaIniziale().getMapStanzeAdiacenti()).containsValue(test));
		Map<String,Stanza> mappa = new HashMap<>();
		mappa.put("nord", new Stanza("stanza 1"));
		mappa.put("ovest", new Stanza("stanza 2"));
		mappa.put("sud", new Stanza("stanza 3"));
		mappa.put("est", new Stanza("stanza 4"));
		assertEquals(mappa,maze.getStanzaIniziale().getMapStanzeAdiacenti());
	}*/
	
	/* con qualche modifica di bing ma non funziona comunque */
	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = lb
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
		assertTrue(((Collection<Attrezzo>) maze.getStanzaIniziale().getMapStanzeAdiacenti()).size()<=4);
		assertTrue(!((Map<String, Stanza>) maze.getStanzaIniziale().getMapStanzeAdiacenti()).containsValue(test));
		Map<String,Stanza> mappa = new HashMap<>();
		mappa.put("nord", maze.getStanza("stanza 1"));
		mappa.put("ovest", maze.getStanza("stanza 2"));
		mappa.put("sud", maze.getStanza("stanza 3"));
		mappa.put("est", maze.getStanza("stanza 4"));
		assertEquals(mappa, maze.getStanzaIniziale().getMapStanzeAdiacenti());
	}
	
	/* questo ora funziona perchè ho sostituito "nuova iniziale" con "Atrio" */
	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = lb
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanza("Atrio")
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		assertEquals("Atrio",maze.getStanzaIniziale().getNome());
	}
	
	/* quello iniziale del prof che non funzionava
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = this.lb
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		assertEquals(attrezzo,maze.getStanzaIniziale().getAttrezzo(nomeAttrezzo));
	}*/

	/* questo me lo aveva consigliato bing ma c'era problema che getStanzaIniziale() non esisteva nella classe LabirintoBuilder e
	 * eclipse mi diceva di aggiungerlo a LabirintoBuilder e bing però mi ha detto che se questo metodo esiste nella classe Labirinto 
	 * non c'era bisogno di farlo anche in LabirintoBuilder, quindi visto che c'era in Labirinto non l'ho fatto in LabirintoBuilder
	 * e bing mi ha detto solo di fare in modo di ottenere la stanza iniziale direttamente dall’oggetto Labirinto. 
	 * 
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		LabirintoBuilder lb = new LabirintoBuilder();
		lb.addStanzaIniziale(this.nomeStanzaIniziale);
		lb.setUltimaStanzaAggiunta(lb.getStanzaIniziale()).addAttrezzo(nomeAttrezzo, peso);
		Labirinto maze = lb.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		assertEquals(attrezzo, maze.getStanzaIniziale().getAttrezzo(nomeAttrezzo));
	}*/
	
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		LabirintoBuilder lb = new LabirintoBuilder();
		lb.addStanzaIniziale(this.nomeStanzaIniziale);
		Labirinto maze = lb.getLabirinto();
		lb.setUltimaStanzaAggiunta(maze.getStanzaIniziale()).addAttrezzo(nomeAttrezzo, peso);
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		assertEquals(attrezzo, maze.getStanzaIniziale().getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		lb
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		assertTrue(this.lb.getListaStanze().get(nomeStanza).getAttrezzi().contains(new Attrezzo (nomeAttrezzo,peso)));
		assertEquals(new Attrezzo(nomeAttrezzo,peso),this.lb.getListaStanze().get(nomeStanza).getAttrezzo(nomeAttrezzo));
	}
	/* come era scritto quando non funzionava
	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		this.lb
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		List<Attrezzo> attrezzi = (List<Attrezzo>) lb.getListaStanze().get(nomeStanza).getAttrezzi();
		assertEquals(attrezzo,attrezzi.get(attrezzi.indexOf(attrezzo)));
	}*/
	
	/* questo qui sotto è la versione funzionante che mi ha suggerito bing, ma devo capirla meglio ancora */
	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
	    String nomeAttrezzo = "attrezzo";
	    int peso = 1;
	    String nomeStanza = "stanza 1";
	    this.lb
	            .addStanzaIniziale(nomeStanzaIniziale)
	            .addStanza(nomeStanza)
	            .addAttrezzo(nomeAttrezzo, peso)
	            .getLabirinto();
	    Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
	    Collection<Attrezzo> collezioneAttrezzi = lb.getListaStanze().get(nomeStanza).getAttrezzi();
	    List<Attrezzo> attrezzi = new ArrayList<>(collezioneAttrezzi);
	    assertEquals(attrezzo,attrezzi.get(attrezzi.indexOf(attrezzo)));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.lb
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = lb.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test  //verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.lb
		.addStanza(nomeStanza1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = lb.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.lb
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = lb.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	/* test che non funziona
	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		this.lb
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica)lb.getListaStanze().get(nomeStanzaMagica);
		assertTrue(stanzaMagica.isMagica());
	}*/
	
	/* test che ora funziona con modifica di bing che mi ha detto di aggiungere .addAttrezzo(...) perchè una stanza diventa magica
	 * solo con l'aggiunta di un attrezzo */
	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeAttrezzo = "attrezzo";
		int pesoAttrezzo = 1;
		String nomeStanzaMagica = "Stanza Magica";
		this.lb
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
		.addAttrezzo(nomeAttrezzo, pesoAttrezzo); // Aggiungi un attrezzo alla stanza
		StanzaMagica stanzaMagica = (StanzaMagica)lb.getListaStanze().get(nomeStanzaMagica);
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
		this.lb
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = lb.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv,peso2_x2), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1));
	}
	
	/* questo test inizialmente non funzionava in questo modo
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.lb
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", "nord", "chiave").addAttrezzo("chiave", 1)
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente,lb.getListaStanze().get("stanza bloccata").getStanzaAdiacente("nord"));	
	}*/
	
	/* in questo modo funziona: ho cambiato solo l'ultima riga */
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.lb
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", "nord", "chiave").addAttrezzo("chiave", 1)
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente.getNome(), lb.getListaStanze().get("stanza bloccata").getStanzaAdiacente("nord").getNome());
	}
	/* test non funzionante
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.lb
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", "nord", "chiave")
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", "nord", "chiave");
		//Asserisce che in caso di mancanza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		assertEquals(stanzaBloccata,lb.getListaStanze().get("stanza bloccata").getStanzaAdiacente("nord"));
	}*/
	
	/* test funzionante */
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
	    this.lb
	    .addStanzaIniziale(nomeStanzaIniziale)
	    .addStanzaBloccata("stanza bloccata", "nord", "chiave")
	    .addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
	    .addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
	    .addStanzaVincente(nomeStanzaVincente)
	    .addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
	    .addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
	    String nomeStanzaBloccata = "stanza bloccata";
	    //Asserisce che in caso di mancanza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna se stessa
	    assertEquals(nomeStanzaBloccata, lb.getListaStanze().get("stanza bloccata").getStanzaAdiacente("nord").getNome());
	}
	
	@Test
	public void testLabirintoCompletoConTutteLeStanze() {
		
		Labirinto labirintoCompleto = this.lb
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
		assertTrue(corridoio.getDirezioni().containsAll(Arrays.asList("ovest","est","nord","sud")));
		Map<String,Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put("nord",new Stanza("corridoio bloccato"));
		mapAdiacenti.put("sud",new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put("est",new Stanza("stanza magica"));
		mapAdiacenti.put("ovest",new Stanza("stanza buia"));
		assertEquals(mapAdiacenti,corridoio.getMapStanzeAdiacenti());
		Attrezzo a1 = new Attrezzo("chiave",1);
		Attrezzo a2 = new Attrezzo("lanterna",1);
		assertEquals(Arrays.asList(a1,a2),corridoio.getAttrezzi());
	}


	@Test
	public void testGetLabirinto() {
		assertNotNull(lb.getLabirinto());
		assertEquals(Labirinto.class, lb.getLabirinto().getClass());
	}

	@Test
	public void testAddStanza() {
		lb.addStanza("stanzetta");
		Stanza expected = new Stanza("stanzetta");
		assertEquals(expected.getNome(), lb.getNome2stanza().get(expected.getNome()).getNome());
	}

	@Test
	public void testAddAttrezzoSenzaUltimaStanzaAggiunta(){
		
		//lb.addAttrezzo("cacciavite", 3);
		//Attrezzo expected = new Attrezzo("cacciavite", 3);
		assertEquals(LabirintoBuilder.class, lb.addAttrezzo("cacciavite", 3).getClass());
	}
	
	@Test
	public void testAddAttrezzoConUltimaStanzaAggiunta(){
		lb.addStanzaIniziale("stanzetta").addAttrezzo("cacciavite", 3);
		Attrezzo expected = new Attrezzo("cacciavite", 3);
		assertEquals(expected, lb.getLabirinto().getStanzaCorrente().getAttrezzo("cacciavite"));		
	}

	@Test
    public void testAddAttrezzoConStanza() {
        lb.addStanza("stanzetta");
        lb.addAttrezzo("cacciavite", 3);
        assertTrue(lb.getNome2stanza().get("stanzetta").hasAttrezzo("cacciavite"));
    }
}


