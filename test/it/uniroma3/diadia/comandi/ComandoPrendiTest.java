package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComandoPrendiTest {
	private Partita partita;
	private ComandoPrendi comando;
	private Attrezzo attrezzo;
	private Labirinto labirinto;

	@Before
	public void setUp() {
		labirinto = new Labirinto();
		partita = new Partita(labirinto);
		comando = new ComandoPrendi();
		attrezzo = new Attrezzo("attrezzo", 1);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
	}
	@Test
	public void testComandoPrendiNull() {
		comando.setParametro("martello");
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}
	
	@Test
	public void testEsegui() {
		comando.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}

	@Test
	public void testGetNome() {
		assertEquals("Comando prendi", comando.getNome());
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
