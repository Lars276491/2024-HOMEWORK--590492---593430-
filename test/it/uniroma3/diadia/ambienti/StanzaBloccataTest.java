package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	@Test
	public void testGetDirezioneBloccata() {
		StanzaBloccata s = new StanzaBloccata("stanza", "nord", "chiave");
		assertEquals("nord", s.getDirezioneBloccata());
	}
	
	@Test
	public void testGetNomeChiave() {
		StanzaBloccata s = new StanzaBloccata("stanza", "nord", "chiave");
		assertEquals("chiave", s.getChiave());
	}

	@Test
	public void testStanzaDirezioneAccessibile() {
		StanzaBloccata s = new StanzaBloccata("stanza", "nord", "chiave");
		Stanza stanzaAdiacente = new Stanza("stanza accessibile");
		s.impostaStanzaAdiacente("sud", stanzaAdiacente);
		assertEquals(stanzaAdiacente, s.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testStanzaDirezioneBloccata() {
		StanzaBloccata s = new StanzaBloccata("stanza", "nord", "chiave");
		assertEquals(s, s.getStanzaAdiacente("nord"));
	}

	@Test
	public void testStanzaDirezioneSbloccata() {
		StanzaBloccata s = new StanzaBloccata("stanza", "nord", "chiave");
		Stanza stanzaAdiacente = new Stanza("unlocked");
		s.addAttrezzo(new Attrezzo("chiave", 1));
		s.impostaStanzaAdiacente("nord", stanzaAdiacente);
		assertEquals(stanzaAdiacente, s.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testStanzeNomeUgualeConTipiDinamiciUguali() {
		Stanza s1 = new StanzaBloccata("cantina","sud", "chiave");
		Stanza s2 = new StanzaBloccata("cantina","sud", "chiave");
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testStanzeDiverseConTipiDinamiciUguali() {
		Stanza s1 = new StanzaBloccata("salone", "nord", "forchetta");
		Stanza s2 = new StanzaBloccata("soggiorno", "sud", "telecomando");
		assertFalse(s1.equals(s2));
	}
	
	@Test
	public void testStanzeConTipiDinamiciDiversi() {
		Stanza s1 = new StanzaBloccata("cantina","sud", "chiave");
		Stanza s2 = new Stanza("cantina");
		assertFalse(s1.equals(s2));
	}
	
	@Test
	public void testStanzeDiverseConTipiDinamiciDiversi() {
		Stanza s1 = new StanzaBloccata("salone", "nord", "forchetta");
		Stanza s2 = new Stanza("soggiorno");
		assertFalse(s1.equals(s2));
	}
}
