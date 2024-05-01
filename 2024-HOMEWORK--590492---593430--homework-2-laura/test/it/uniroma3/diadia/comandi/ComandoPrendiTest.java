package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComandoPrendiTest {
	private Partita partita;
	private ComandoPrendi comandoPrendi;
	private Attrezzo attrezzo;
	private Labirinto labirinto;

	@Before
	public void setUp() {
		labirinto = new Labirinto();
		partita = new Partita(labirinto);
		comandoPrendi = new ComandoPrendi("attrezzo");
		attrezzo = new Attrezzo("attrezzo", 1);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
	}

	@Test
	public void testEsegui() {
		comandoPrendi.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}

	@Test
	public void testGetNome() {
		assertEquals("Comando prendi", comandoPrendi.getNome());
	}

	@Test
	public void testSconosciuto() {
		assertFalse(comandoPrendi.sconosciuto());
	}

	@Test
	public void testGetParametro() {
		assertEquals("attrezzo", comandoPrendi.getParametro());
	}

	@Test
	public void testSetParametro() {
		comandoPrendi.setParametro("altro_attrezzo");
		assertEquals("altro_attrezzo", comandoPrendi.getParametro());
	}
}
