package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabirintoTest {
	
	@Test
	public void testCostruttoreLabirinto() {
		assertNotNull(Labirinto.newBuilder().getLabirinto());
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", Labirinto.newBuilder().getLabirinto().getStanzaVincente().getNome());
	}

	@Test
	public void testGetStanzaIniziale() {
		assertNotNull(Labirinto.newBuilder().getLabirinto().getStanzaIniziale());
	}
	
	@Test
	public void testNuovaStanzaIniziale() {
		Labirinto lab = Labirinto.newBuilder().getLabirinto();
		lab.setStanzaIniziale(new Stanza("cucina"));
		assertEquals(new Stanza("cucina"), lab.getStanzaIniziale());
	}
	
	@Test
	public void testNuovaStanzaVincente() {
		Labirinto lab = Labirinto.newBuilder().getLabirinto();
		lab.setStanzaVincente(new Stanza("cucina"));
		assertEquals(new Stanza("cucina"), lab.getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaVincenteCaricata() {
		assertEquals("Biblioteca", Labirinto.newBuilder().getStanzaVincente().getNome());
	}
}
