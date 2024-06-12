package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class ComandoPosaTest {

private Scanner scanner;
	
	@Before
	public void setUp() {
		scanner = new Scanner(System.in);
		try {
			
		}finally {
			scanner.close();
		}
	}
	
	@Test
	public void testSetParametroNull() {
		ComandoPosa p = new ComandoPosa(null);
		p.setParametro(null);
		assertNull(p.getParametro());
	}

	@Test
	public void testSetParametroNome() {
		ComandoPosa p = new ComandoPosa(null);
		p.setParametro("attrezzo");
		assertTrue(p.getParametro().equals("attrezzo"));
	}
	
	@Test
	public void testPosaUnAttrezzo() {
		Partita partita = new Partita(Labirinto.newBuilder().getLabirinto());
		Comando p = new ComandoPosa("spada");
		/*spada presente in borsa*/
		Attrezzo spada = new Attrezzo("spada", 3);
		partita.getGiocatore().getBorsa().addAttrezzo(spada);
		p.esegui(partita, new IOConsole(scanner));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("spada"));
		
	}
	
	@Test
	public void testPosaAttrezzoNonPresenteInBorsa() {
		Partita partita = new Partita(Labirinto.newBuilder().getLabirinto());
		Comando p = new ComandoPosa("martello");
		/*spada presente in borsa*/
		Attrezzo spada = new Attrezzo("spada", 3);
		partita.getGiocatore().getBorsa().addAttrezzo(spada);
		p.esegui(partita, new IOConsole(scanner));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
		
	}
	
	@Test
	public void testPosaAttrezziMultipli() {
		Labirinto l = new LabirintoBuilder().addStanzaIniziale("atrio")
				.addStanzaVincente("biblioteca")
				.addAdiacenza("atrio", "biblioteca", "nord")
				.addAdiacenza("biblioteca", "atrio", "sud")
				.getLabirinto();
		Partita partita = new Partita(l);
		Comando p = new ComandoPosa("martello");
		/*spada presente in borsa*/
		Attrezzo spada = new Attrezzo("spada", 3);
		partita.getGiocatore().getBorsa().addAttrezzo(spada);
		/*martello presente in borsa*/
		Attrezzo martello = new Attrezzo("martello", 3);
		partita.getGiocatore().getBorsa().addAttrezzo(martello);
		/*posa il martello*/
		p.esegui(partita, new IOConsole(scanner));
		/*posa la spada*/
		p = new ComandoPosa("spada");
		p.esegui(partita, new IOConsole(scanner));
		
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
		assertEquals(martello, partita.getStanzaCorrente().getAttrezzi().get(0));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertEquals(spada, partita.getStanzaCorrente().getAttrezzi().get(1));
	}
}
