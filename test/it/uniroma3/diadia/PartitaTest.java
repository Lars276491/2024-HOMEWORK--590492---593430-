package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import static org.junit.Assert.*;
import org.junit.*;

public class PartitaTest {

	@Test
	public void testGetStanzaCorrenteIniziale() {
		assertNotNull(new Partita(Labirinto.newBuilder().getLabirinto()).getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaCorrenteAggiornata() {
		Partita partita = new Partita(Labirinto.newBuilder().getLabirinto());
		Stanza stanza = new Stanza("Magazzino");
		partita.setStanzaCorrente(stanza);
		assertEquals(stanza, partita.getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", new Partita(Labirinto.newBuilder().getLabirinto()).getLabirinto().getStanzaVincente().getNome());
	}

	@Test
	public void testIsNotFinita() {
		assertFalse(new Partita(Labirinto.newBuilder().getLabirinto()).isFinita());
	}
	
	@Test
	public void testIsFinita() {
		Partita partitaFinita = new Partita(Labirinto.newBuilder().getLabirinto());
		partitaFinita.setFinita();
		assertTrue(partitaFinita.isFinita());
	}
	
	@Test
	public void testIsVinta() {
		String nomeStanzaIniziale = "atrio";
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
		assertTrue(new Partita(monolocale).vinta());
	}
	

	@Test
	public void testIsNotVinta() {
		String nomeStanzaIniziale = "atrio";
		String nomeStanzaVincente = "biblioteca";
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente )
				.getLabirinto();
		assertFalse(new Partita(bilocale).vinta());
	}

	@Test
	public void testPartitaConStanzaBloccata() {
		String nomeStanzaIniziale = "atrio";
		String nomeStanzaVincente = "giardino";
		Labirinto trilocale =new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale )
				.addAttrezzo("libro antico", 5)
				.addStanzaBloccata("biblioteca", "est", "libro antico")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenza("biblioteca", nomeStanzaIniziale, "nord")
				.addStanzaVincente(nomeStanzaVincente )
				.addAdiacenza("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenza(nomeStanzaVincente,"biblioteca" , "ovest")
				.getLabirinto();
		Partita p = new Partita(trilocale);
		assertEquals(new Stanza(nomeStanzaIniziale), p.getStanzaCorrente());
		/*spostamento in biblioteca*/
		p.setStanzaCorrente(p.getStanzaCorrente().getStanzaAdiacente("sud"));
		assertEquals("biblioteca", p.getStanzaCorrente().getNome());
		/*stanza ad est inacessibile*/
		assertEquals("biblioteca", p.getStanzaCorrente().getStanzaAdiacente("est").getNome());
	}
	
	@Test
	public void testPartitaConStanzaSbloccata() {
		String nomeStanzaIniziale = "atrio";
		String nomeStanzaVincente = "giardino";
		Labirinto trilocale =new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale )
				.addAttrezzo("libro antico", 5)
				.addStanzaBloccata("biblioteca", "est", "libro antico")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenza("biblioteca", nomeStanzaIniziale, "nord")
				.addStanzaVincente(nomeStanzaVincente )
				.addAdiacenza("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenza(nomeStanzaVincente,"biblioteca" , "ovest")
				.getLabirinto();
		Partita p = new Partita(trilocale);
		assertEquals(new Stanza(nomeStanzaIniziale), p.getStanzaCorrente());
		/*rimozione dell'attrezzo*/
		assertTrue(p.getStanzaCorrente().removeAttrezzo(new Attrezzo("libro antico", 5)));
		/*spostamento in biblioteca*/
		p.setStanzaCorrente(p.getStanzaCorrente().getStanzaAdiacente("sud"));
		assertEquals("biblioteca", p.getStanzaCorrente().getNome());
		/*stanza ad est inacessibile*/
		assertEquals("biblioteca", p.getStanzaCorrente().getStanzaAdiacente("est").getNome());
		p.getStanzaCorrente().addAttrezzo(new Attrezzo("libro antico", 5));
		/*stanza ad est accessibile*/
		assertEquals(nomeStanzaVincente, p.getStanzaCorrente().getStanzaAdiacente("est").getNome());
	}
	
	@Test
	public void testPartitaConStanzaBuia() {
		String nomeStanzaIniziale = "atrio";
		String nomeStanzaVincente = "giardino";
		Labirinto trilocale =new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale )
				.addAttrezzo("lanterna", 2)
				.addStanzaBuia("biblioteca", "lanterna")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenza("biblioteca", nomeStanzaIniziale, "nord")
				.addStanzaVincente(nomeStanzaVincente )
				.addAdiacenza("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenza(nomeStanzaVincente,"biblioteca" , "ovest")
				.getLabirinto();
		Partita p = new Partita(trilocale);
		assertEquals(new Stanza(nomeStanzaIniziale), p.getStanzaCorrente());
		/*spostamento in biblioteca*/
		p.setStanzaCorrente(p.getStanzaCorrente().getStanzaAdiacente("sud"));
		assertEquals("biblioteca", p.getStanzaCorrente().getNome());
		/*Impossibile vedere attorno*/
		assertEquals("qui c'è un buio pesto", p.getStanzaCorrente().getDescrizione());
	}
	
	@Test
	public void testPartitaConStanzaIlluminata() {
		String nomeStanzaIniziale = "atrio";
		String nomeStanzaVincente = "giardino";
		Labirinto trilocale =new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale )
				.addAttrezzo("lanterna", 2)
				.addStanzaBuia("biblioteca", "lanterna")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenza("biblioteca", nomeStanzaIniziale, "nord")
				.addStanzaVincente(nomeStanzaVincente )
				.addAdiacenza("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenza(nomeStanzaVincente,"biblioteca" , "ovest")
				.getLabirinto();
		Partita p = new Partita(trilocale);
		assertEquals(new Stanza(nomeStanzaIniziale), p.getStanzaCorrente());
		/*rimozione lanterna per spostarla nella stanza buia*/
		p.getStanzaCorrente().removeAttrezzo(new Attrezzo("lanterna", 2));
		/*spostamento in biblioteca*/
		p.setStanzaCorrente(p.getStanzaCorrente().getStanzaAdiacente("sud"));
		assertEquals("biblioteca", p.getStanzaCorrente().getNome());
		/*Impossibile vedere attorno*/
		assertEquals("qui c'è un buio pesto", p.getStanzaCorrente().getDescrizione());
		/*Inserimento lanterna e possibilità di vedere*/
		p.getStanzaCorrente().addAttrezzo(new Attrezzo("lanterna", 2));
		assertNotEquals("qui c'è un buio pesto", p.getStanzaCorrente().getDescrizione());
	}
}
