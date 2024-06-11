package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComandoPosaTest {
	private Partita partita;
	private Comando comando;
	private Attrezzo attrezzo;
	private Labirinto labirinto;

	@Before
	public void setUp() {
		labirinto = new Labirinto();
		partita = new Partita(labirinto);
		comando = new ComandoPosa();
		attrezzo = new Attrezzo("attrezzo", 1);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}
	
	@Test
	public void testAttrezzoPosato() {
		Attrezzo martello = new Attrezzo("martello", 6);
		partita.getGiocatore().getBorsa().addAttrezzo(martello);
		comando.setParametro("martello");
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	@Test
	public void testAttrezzoPosatoNull() {
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	
	@Test
	public void testGetNome() {
		assertEquals("Comando posa", comando.getNome());
	}

	@Test
	public void testGetParametro() {
		assertEquals("attrezzo", comando.getParametro());
	}

	@Test
	public void testSetParametro() {
		comando.setParametro("altro_attrezzo");
		assertEquals("altro_attrezzo", comando.getParametro());
	}
}
