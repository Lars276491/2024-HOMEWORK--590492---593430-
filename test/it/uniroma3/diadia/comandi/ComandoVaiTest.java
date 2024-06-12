package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComandoVaiTest {
	private Partita partita;
	private ComandoVai comandoVai;
	private Labirinto labirinto;
	@Before
	public void setUp() {
		labirinto= new Labirinto();
		partita = new Partita(labirinto);
		comandoVai = new ComandoVai();
	}

	@Test
	public void testEsegui() {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		comandoVai.esegui(partita);
		assertNotEquals(stanzaCorrente, partita.getStanzaCorrente());
	}

	@Test
	public void testGetNome() {
		assertEquals("Comando vai", comandoVai.getNome());
	}

	@Test
	public void testGetParametro() {
		assertEquals("nord", comandoVai.getParametro());
	}

	@Test
	public void testSetParametro() {
		comandoVai.setParametro("sud");
		assertEquals("sud", comandoVai.getParametro());
	}
}
