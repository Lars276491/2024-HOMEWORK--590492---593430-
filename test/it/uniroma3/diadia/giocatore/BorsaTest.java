package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BorsaTest {
	
	private final Attrezzo a = new Attrezzo("prova", 2);
	private final Borsa b = new Borsa();
	private final Attrezzo ascia = new Attrezzo("ascia", 5);
	private final Attrezzo spadone = new Attrezzo("spadone", 12);
	
	
	@Test
	public void testAddAttrezzo() {
		assertTrue(b.addAttrezzo(a));
	}
	
	@Test
	public void testAddAttrezzoNull() {
		assertFalse(b.addAttrezzo(null));
	}
	
	@Test
	public void testAddAttrezzoDiversoNull() {
		assertNotNull(b.addAttrezzo(a));
	}
	
	@Test
	public void testGetPesoMax() {
		assertFalse(b.getPesoMax()== 5);		
	}
	
	@Test
	public void testAddAttrezzoPesoMinoreDiDieci() {
		assertTrue(b.addAttrezzo(ascia));
	}
	
	@Test
	public void testAddAttrezzoPesoMaggioreDiDieci() {
		assertFalse(b.addAttrezzo(spadone));
	}

}
