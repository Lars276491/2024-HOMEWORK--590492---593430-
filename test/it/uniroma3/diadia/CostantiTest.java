package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

public class CostantiTest {
	
	@Test
	public void testFileNonCreato() {
		Costanti c = new Costanti();
		assertEquals(10, c.getDefaultPesoMaxBorsa());
		assertEquals(20, c.getCfuIniziali());
		c.setDefaultPesoMaxBorsa(20);
	}
	
	@Test
	public void testFileCreatoConPesoBorsaModificato() {
		Costanti c = new Costanti();
		assertEquals(20, c.getDefaultPesoMaxBorsa());
		assertEquals(20, c.getCfuIniziali());
		
	}

}
