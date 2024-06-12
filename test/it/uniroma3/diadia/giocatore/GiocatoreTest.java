package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class GiocatoreTest {
	
	@Test
	public void testGetGiocatoreGeneratoDallaPartita() {
		assertNotNull(new Partita(Labirinto.newBuilder().getLabirinto()).getGiocatore());
	}
	
	@Test
	public void testGetCfuModificati() {
		Giocatore g = new Giocatore();
		g.setCfu(15);
		assertEquals(15, g.getCfu());
	}
	
	@Test
	public void testGetBorsa() {
		assertNotNull(new Giocatore().getBorsa());
	}
	
	@Test
	public void testGetBorsaPiena() {
		Giocatore g = new Giocatore();
		Attrezzo a = new Attrezzo("foglio di carta", 1);
		for(int i = 0; i < 10; i++) {
			a = new Attrezzo("foglio di carta", 1);
			g.getBorsa().addAttrezzo(a);
		}
		
		assertEquals(g.getBorsa().getPesoMax(), g.getBorsa().getPeso());
		
		for(int i = 0; i < 9; i++)
			assertEquals(a, g.getBorsa().getContenutoOrdinatoPerPeso().get(i));
	}

}
