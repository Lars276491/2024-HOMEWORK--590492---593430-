package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import java.io.StringReader;
import java.net.URISyntaxException;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class CaricatoreLabirintoTest {

	private static String testoLabirinto = "Stanze: biblioteca, N10, N11\n"
			+ "Stanze bloccate: prigione nord chiave\n"
			+ "Stanze buie: cantina lampada\n"
			+ "Stanze magiche: \n"
			+ "Inizio: N10\n"
			+ "Vincente: N11\n"
			+ "Attrezzi: martello 10 biblioteca, pinza 2 N10\n"
			+ "Uscite: biblioteca nord N10, biblioteca sud N11\n"
			+ "Personaggi: Mago Merlino bastone 2 N10\n"
			+ "Presentazioni: Ciao sono il mago Merlino è un piacere fare la tua conoscenza";
	private static String testoLabirintoNonValido ="Stanze bloccate: prigione nord chiave\n"
			+ "Stanze buie: cantina lampada\n"
			+ "Stanze magiche: \n"
			+ "Inizio: N10\n"
			+ "Vincente: N11\n"
			+ "Attrezzi: martello 10 biblioteca, pinza 2 N10\n"
			+ "Uscite: biblioteca nord N10, biblioteca sud N11\n"
			+ "Personaggi: Mago Merlino bastone 2 N10\n"
			+ "Presentazioni: Ciao sono il mago Merlino è un piacere fare la tua conoscenza";
	
	@Test(expected = FormatoFileNonValidoException.class)
	public void testFileNonValido() throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto lab = new CaricatoreLabirinto(new StringReader(testoLabirintoNonValido));
		lab.carica();
	}
	
	@Test
	public void testLetturaStanzaIniziale() throws FormatoFileNonValidoException, FileNotFoundException {
		CaricatoreLabirinto lab = null;
			try {
				lab = new CaricatoreLabirinto(CaricatoreLabirinto.class.getResource("labirinto.txt").toURI());
				lab.carica();
				assertEquals(new Stanza("N10"), lab.getStanzaIniziale());
			} catch (FileNotFoundException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	@Test
	public void testLetturaStanzaVincente() throws FormatoFileNonValidoException {
		CaricatoreLabirinto lab = new CaricatoreLabirinto(new StringReader(testoLabirinto));
		lab.carica();
		assertEquals(new Stanza("N11"), lab.getStanzaVincente());
	}
	
	@Test
	public void testLetturaStanze() throws FormatoFileNonValidoException {
		CaricatoreLabirinto lab = null;
		try {
			lab = new CaricatoreLabirinto(CaricatoreLabirinto.class.getResource("labirinto.txt").toURI());
			lab.carica();
			LabirintoBuilder l = lab.getcostruttoreLabirinto();
			assertTrue(l.getListaStanze().contains(new Stanza("N10")));
			assertTrue(l.getListaStanze().contains(new Stanza("N11")));
			assertTrue(l.getListaStanze().contains(new Stanza("biblioteca")));
			assertTrue(l.getListaStanze().contains(new Stanza("prigione")));
		} catch (FileNotFoundException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}
