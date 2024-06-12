package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest	{
	
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
	public void testEseguiStessaStanza() {
		Partita p = new Partita(Labirinto.newBuilder().getLabirinto());
		Stanza s = p.getStanzaCorrente();
		Comando v = new ComandoVai(null);
		v.esegui(p, new IOConsole(scanner));
		assertEquals(s, p.getStanzaCorrente());
		
	}
	
	@Test
	public void testEseguiStanzaSud() {
		Partita p = new Partita(Labirinto.newBuilder().getLabirinto());
		Stanza s = p.getStanzaCorrente().getStanzaAdiacente("sud");
		Comando v = new ComandoVai("sud");
		v.esegui(p, new IOConsole(scanner));
		assertEquals(s, p.getStanzaCorrente());
	}
	
	@Test
	public void testEseguiComandoNonValido() {
		Partita p = new Partita(Labirinto.newBuilder().getLabirinto());
		Stanza s = p.getStanzaCorrente();
		Comando v = new ComandoVai("sud-est");
		v.esegui(p, new IOConsole(scanner));
		assertEquals(s, p.getStanzaCorrente());
	}
	
	@Test
	public void testEseguiStanzaNord() {
		Partita p = new Partita(Labirinto.newBuilder().getLabirinto());
		Stanza s = p.getStanzaCorrente().getStanzaAdiacente("nord");
		Comando v = new ComandoVai("nord");
		v.esegui(p, new IOConsole(scanner));
		assertEquals(s, p.getStanzaCorrente());
	}
	
	@Test
	public void testEseguiStanzaEstEst() {
		/*doppio spostamento ad est*/
		Partita p = new Partita(Labirinto.newBuilder().getLabirinto());
		Stanza s = p.getStanzaCorrente().getStanzaAdiacente("est").getStanzaAdiacente("est");
		Comando v = new ComandoVai("est");
		v.esegui(p, new IOConsole(scanner));
		v.setParametro("est");
		v.esegui(p, new IOConsole(scanner));
		assertEquals(s, p.getStanzaCorrente());
	}

}
