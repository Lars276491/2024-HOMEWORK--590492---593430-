package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComandoPosaTest {
	private Partita partita;
	private ComandoPosa comandoPosa;
	private Attrezzo attrezzo;
	private Labirinto labirinto;

	@Before
	public void setUp() {
		labirinto = new Labirinto();
		partita = new Partita(labirinto);
		comandoPosa = new ComandoPosa("attrezzo");
		attrezzo = new Attrezzo("attrezzo", 1);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}

	@Test
	public void testEsegui() {
		comandoPosa.esegui(partita);
		//da rivedere con Sara
	}

	@Test
	public void testGetNome() {
		assertEquals("Comando posa", comandoPosa.getNome());
	}

	@Test
	public void testSconosciuto() {
		assertFalse(comandoPosa.sconosciuto());
	}

	@Test
	public void testGetParametro() {
		assertEquals("attrezzo", comandoPosa.getParametro());
	}

	@Test
	public void testSetParametro() {
		comandoPosa.setParametro("altro_attrezzo");
		assertEquals("altro_attrezzo", comandoPosa.getParametro());
	}
}
