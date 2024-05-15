package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	
	private StanzaBuia stanza;
	private Attrezzo lanterna;
	
	@Before
	public void setUp(){
		stanza=new StanzaBuia("StanzaBuia", "lanterna");
		lanterna=new Attrezzo("lanterna", 1);
	}
	
	@Test
	public void testGetDescrizioneSenzaLanterna() {
		String e = "qui c'è buio pesto";
		assertEquals(e, stanza.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConAttrezzo() {
		stanza.addAttrezzo(lanterna);
		assertEquals(stanza.toString(), stanza.getDescrizione());
	}

}
