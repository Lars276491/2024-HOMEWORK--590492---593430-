package it.uniroma3.diadia;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.giocatore.*;

public class PartitaTest {
	private Partita p;
	private Labirinto l;
	private Giocatore g;
	private Stanza s;


	@Before
	public void setUp() {
		l = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.getLabirinto();
		 p = new Partita(l);
		 s = new Stanza("Stanza");

	}

	//Test per il metoodo isFinita
	@Test 
	public void testPartitaNonFinitaAllInizio() {
		assertFalse(p.isFinita());
	}

	@Test
	public void testPartitaFinitaQuandoVinta() {
		Stanza stanzaVincente = l.getStanzaVincente();
		p.setStanzaCorrente(stanzaVincente);
		assertTrue(p.isFinita());
	}

	@Test
	public void testPartitaFinitaQuandoZeroCfu() {
		g.setCfu(0); 
		assertTrue(p.isFinita());
	}
	//Test per il metodo getStanzaCorrenteAllInizio
	@Test
	public void testGetStanzaCorrenteAllInizio() {
		assertEquals(l.getStanzaIniziale(), p.getStanzaCorrente());
	}

	@Test
	public void testGetStanzaCorrenteDopoSet() {
		Stanza stanza1 = new Stanza("stanza 1");
		p.setStanzaCorrente(stanza1);
		assertEquals(stanza1, p.getStanzaCorrente());

		Stanza stanza2 = new Stanza("stanza 2");
		p.setStanzaCorrente(stanza2);
		assertEquals(stanza2, p.getStanzaCorrente());

		Stanza stanza3 = new Stanza("stanza 3");
		p.setStanzaCorrente(stanza3);
		assertEquals(stanza3, p.getStanzaCorrente());
	}

	@Test
	public void testGetStanzaCorrenteDopoSetNull() {
		p.setStanzaCorrente(null);
		assertNull(p.getStanzaCorrente());
	}

	@Test
	public void testGetStanzaCorrenteRestituisceStanzaVincente() {
		p.setStanzaCorrente(l.getStanzaVincente());
		assertEquals("Biblioteca", p.getStanzaCorrente().getNome());
	}
	//Test per il metodo vinta
	@Test
	public void testVintaDopoCreazione() {
		assertFalse(p.vinta());
	}

	@Test
	public void testVintaDopoSetStanzaCorrenteNonVincente() {
		Stanza stanza1 = new Stanza("stanza 1");
		p.setStanzaCorrente(stanza1);
		assertFalse(p.vinta());
	}

	@Test
	public void testVintaDopoSetStanzaCorrenteVincente() {
		p.setStanzaCorrente(l.getStanzaVincente());
		assertTrue(p.vinta());
	}
	//Test per il metodo getLabirinto
	@Test
	public void testGetLabirinto() {
		assertEquals(l, p.getLabirinto());
	}

	//Test per il metodo getGiocatore
	@Test
	public void tesGetGiocatore() {
		assertEquals(g, p.getGiocatore());
	}


}
