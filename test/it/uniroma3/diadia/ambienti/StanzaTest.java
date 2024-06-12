package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;



public class StanzaTest {

	private final Stanza stanza1 = new Stanza("stanza1");
	
	private Stanza stanzaIsolata(String nome) {
		return new Stanza(nome);
	}

	private Stanza stanzaCollegata(String nome, String direzione, Stanza camera) {
		Stanza stanza = new Stanza(nome);
		stanza.impostaStanzaAdiacente(direzione, camera);
		return stanza;
	}
	
	@Test
	public void testGetStanzaAdiacenteIsolata() {
		assertNull(this.stanzaIsolata("Stanza isolata").getStanzaAdiacente("nord"));
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneCollegata() {
		assertEquals(this.stanza1, this.stanzaCollegata("stanza2", "nord", stanza1).getStanzaAdiacente("nord"));
	}
	
	@Test 
	public void testGetStanzaAdiacenteDirezioneScollegata() {
		assertNull(this.stanzaCollegata("stanza2", "nord", stanza1).getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testAddAttrezzoNullo() {
		assertFalse(stanza1.addAttrezzo(null));
	}
	
	@Test
	public void testAddAttrezzoValido() {
		assertTrue(stanza1.addAttrezzo(new Attrezzo ("conchiglia", 1)));
	}
	
	@Test
	public void testHasAttrezzoNome() {
		stanza1.addAttrezzo(new Attrezzo ("conchiglia", 1));
		assertTrue(stanza1.hasAttrezzo("conchiglia"));
	}
	
	@Test
	public void testStanzeUguali() {
		Stanza s1 = new Stanza("salone");
		Stanza s2 = new Stanza("salone");
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testStanzeDiverse() {
		Stanza s1 = new Stanza("salone");
		Stanza s2 = new Stanza("soggiorno");
		assertFalse(s1.equals(s2));
	}
	
	@Test
	public void testOttieniAttrezzoDallaLista() {
		Stanza s = new Stanza("cucina");
		Attrezzo cucchiaio = new Attrezzo("cucchiaio", 1);
		s.addAttrezzo(cucchiaio);
		assertEquals(cucchiaio, s.getAttrezzi().get(0));
	}
	
	@Test
	public void testHasAttrezzoPresente() {
		Stanza s = new Stanza("cucina");
		Attrezzo cucchiaio = new Attrezzo("cucchiaio", 1);
		s.addAttrezzo(cucchiaio);
		assertTrue(s.hasAttrezzo("cucchiaio"));
	}
	
	@Test
	public void testHasAttrezzoAssente() {
		Stanza s = new Stanza("cucina");
		Attrezzo cucchiaio = new Attrezzo("cucchiaio", 1);
		s.addAttrezzo(cucchiaio);
		assertFalse(s.hasAttrezzo("martello"));
	}
	
	@Test
	public void testRemoveAttrezzoPresente() {
		Stanza s = new Stanza("cucina");
		Attrezzo cucchiaio = new Attrezzo("cucchiaio", 1);
		s.addAttrezzo(cucchiaio);
		assertTrue(s.removeAttrezzo(new Attrezzo("cucchiaio", 1)));
	}
	
	@Test
	public void testRemoveAttrezzoAssente() {
		Stanza s = new Stanza("cucina");
		Attrezzo cucchiaio = new Attrezzo("cucchiaio", 1);
		s.addAttrezzo(cucchiaio);
		assertFalse(s.removeAttrezzo(new Attrezzo("martello", 2)));
	}
	
	
	
}
