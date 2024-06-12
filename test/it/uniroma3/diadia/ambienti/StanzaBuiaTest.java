package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	@Test
	public void testStanzaBuia() {
		StanzaBuia s = new StanzaBuia("stanza", "lanterna");
		assertEquals("qui c'è un buio pesto", s.getDescrizione());
	}
	
	@Test
	public void testStanzaLuminosa() {
		StanzaBuia s = new StanzaBuia("stanza", "lanterna");
		s.addAttrezzo(new Attrezzo("lanterna", 2));
		assertNotEquals("qui c'è un buio pesto", s.getDescrizione());
	}
	
	@Test
	public void testStanzaBuiaAttrezzoSbagliata() {
		StanzaBuia s = new StanzaBuia("stanza", "lanterna");
		s.addAttrezzo(new Attrezzo("osso", 1));
		assertEquals("qui c'è un buio pesto", s.getDescrizione());
	}
	
	@Test
	public void testStanzeNomeUgualeConTipiDinamiciUgualiAttrezziChiaviUguali() {
		Stanza s1 = new StanzaBuia("cantina", "lanterna");
		Stanza s2 = new StanzaBuia("cantina", "lanterna");
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testStanzeNomeUgualeConTipiDinamiciUgualiAttrezziChiaveDiversi() {
		Stanza s1 = new StanzaBuia("cantina", "lanterna");
		Stanza s2 = new StanzaBuia("cantina", "chiave");
		assertFalse(s1.equals(s2));
	}
	
	@Test
	public void testStanzeDiverseConTipiDinamiciUguali() {
		Stanza s1 = new StanzaBuia("cantina", "lanterna");
		Stanza s2 = new StanzaBloccata("soggiorno", "sud", "telecomando");
		assertFalse(s1.equals(s2));
	}
	
	@Test
	public void testStanzeConTipiDinamiciDiversi() {
		Stanza s1 = new StanzaBuia("cantina","lanterna");
		Stanza s2 = new Stanza("cantina");
		assertFalse(s1.equals(s2));
	}
	
	@Test
	public void testStanzeDiverseConTipiDinamiciDiversi() {
		Stanza s1 = new StanzaBuia("cantina", "lanterna");
		Stanza s2 = new Stanza("soggiorno");
		assertFalse(s1.equals(s2));
	}


}
