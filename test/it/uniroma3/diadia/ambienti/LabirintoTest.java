package it.uniroma3.diadia.ambienti;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	private Labirinto l = new Labirinto();
	private final Stanza si = new Stanza("Atrio");
	private final Stanza sd = new Stanza("Stanza diversa");
	private final Stanza sv = new Stanza("Biblioteca");
	
	@Before
	public void setUp() {
		l = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
	}
	
	@Test
	public void testGetStanzaIniziale() {
		assertEquals(si.getNome(), l.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertEquals(sv.getNome(), l.getStanzaVincente().getNome());
	}
	
	@Test
	public void testStanzaInzialeDiversoAtrio() {
		assertFalse(l.getStanzaIniziale().getNome()== sd.getNome());
	}
	
	
}
