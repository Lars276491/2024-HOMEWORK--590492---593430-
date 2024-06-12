package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class StanzaMagicaTest {

	@Test
	public void testAttrezzoNonModificato() {
		Stanza s = new StanzaMagica("stanza magica");
		Attrezzo spada = new Attrezzo("spada", 3);
		s.addAttrezzo(spada);
		assertEquals(spada, s.getAttrezzi().get(0));
	}

	@Test
	public void testAttrezzoNonModificatoAlSecondoTentativo() {
		Stanza s = new StanzaMagica("stanza magica");
		Attrezzo spada = new Attrezzo("spada", 3);
		s.addAttrezzo(spada);
		assertEquals(spada, s.getAttrezzi().get(0));
		s.removeAttrezzo(spada);
		s.addAttrezzo(spada);
		assertEquals(spada, s.getAttrezzi().get(0));
	}

	@Test
	public void testAttrezzoModificatoAlTerzoTentativo() {
		Stanza s = new StanzaMagica("stanza magica", 2);
		Attrezzo spada = new Attrezzo("spada", 3);
		s.addAttrezzo(spada);
		for(int i = 0; i < 2; i++) {
			s.removeAttrezzo(spada);
			s.addAttrezzo(spada);
		}
		assertEquals("adaps", s.getAttrezzi().get(0).getNome());
		assertEquals(6, s.getAttrezzi().get(0).getPeso());
	}
	
	@Test
	public void testAttrezzoModificatoDueVolte() {
		Stanza s = new StanzaMagica("stanza magica", 2);
		Attrezzo spada = new Attrezzo("spada", 3);
		s.addAttrezzo(spada);
		for(int i = 0; i < 3; i++) {
			spada =s.getAttrezzi().get(0);
			s.removeAttrezzo(spada);
			s.addAttrezzo(spada);
		}
		assertEquals("spada", s.getAttrezzi().get(0).getNome());
		assertEquals(12, s.getAttrezzi().get(0).getPeso());
	}
	
	@Test
	public void testStanzeNomeUgualeConTipiDinamiciUgualiContatoreUguale() {
		Stanza s1 = new StanzaMagica("cantina", 2);
		Stanza s2 = new StanzaMagica("cantina", 2);
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testStanzeNomeUgualeConTipiDinamiciUgualiContatoreDiverso() {
		Stanza s1 = new StanzaMagica("cantina", 2);
		Stanza s2 = new StanzaMagica("cantina", 3);
		assertFalse(s1.equals(s2));
	}
	
	@Test
	public void testStanzeDiverseConTipiDinamiciUguali() {
		Stanza s1 = new StanzaMagica("cantina");
		Stanza s2 = new StanzaBloccata("soggiorno", "sud", "telecomando");
		assertFalse(s1.equals(s2));
	}
	
	@Test
	public void testStanzeConTipiDinamiciDiversiStessoNome() {
		Stanza s1 = new StanzaMagica("cantina");
		Stanza s2 = new Stanza("cantina");
		assertFalse(s1.equals(s2));
	}
	
	@Test
	public void testStanzeDiverseConTipiDinamiciDiversiNomeDiverso() {
		Stanza s1 = new StanzaMagica("cantina");
		Stanza s2 = new Stanza("soggiorno");
		assertFalse(s1.equals(s2));
	}
}
