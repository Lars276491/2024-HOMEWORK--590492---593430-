package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class AbstractPersonaggioTest {

	@Test
	public void testClasseMago() {
		AbstractPersonaggio p = new Mago("Merlino", "Sono il mago Merlino", new Attrezzo("bacchetta", 2));
		assertEquals("Merlino", p.getNome());
		assertEquals(new Attrezzo("bacchetta", 2), p.getAttrezzo());
		assertFalse(p.haSalutato());
		Partita partita = new Partita(Labirinto.newBuilder().getLabirinto());
		partita.getStanzaCorrente().setPersonaggio(p);
		assertEquals(p, partita.getStanzaCorrente().getPersonaggio());
		p.agisci(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("bacchetta"));
		p.saluta();
		assertTrue(p.haSalutato());
		assertEquals("Mi spiace, ma non ho piu' nulla...", p.agisci(partita));
		p.riceviRegalo(new Attrezzo("spada", 4), partita);
		assertEquals(new Attrezzo("spada", 2), partita.getStanzaCorrente().getAttrezzo("spada"));
	}
	
	@Test
	public void testClasseCane() {
		AbstractPersonaggio p = new Cane("Fido", new Attrezzo("osso", 1));
		assertEquals("Fido", p.getNome());
		assertEquals(new Attrezzo("osso", 1), p.getAttrezzo());
		assertFalse(p.haSalutato());
		Partita partita = new Partita(Labirinto.newBuilder().getLabirinto());
		partita.getStanzaCorrente().setPersonaggio(p);
		assertEquals(p, partita.getStanzaCorrente().getPersonaggio());
		p.agisci(partita);
		new Giocatore();
		assertEquals(Giocatore.getCfuIniziali()-1, partita.getGiocatore().getCfu());
		p.saluta();
		assertTrue(p.haSalutato());
		assertEquals("BAU, BAU!!! ARGGGGG... ACK" + '\n' +
				"(Hai perso un CFU)", p.agisci(partita));
		p.riceviRegalo(new Attrezzo("spada", 4), partita);
		assertEquals(new Attrezzo("spada", 4), partita.getStanzaCorrente().getAttrezzo("spada"));
		assertEquals(Giocatore.getCfuIniziali()-3, partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testClasseStrega() {
		AbstractPersonaggio p = new Strega("Badibi", "Sono la strega Badibi");
		assertEquals("Badibi", p.getNome());
		assertFalse(p.haSalutato());
		Partita partita = new Partita(Labirinto.newBuilder().getLabirinto());
		Stanza stanzaIniziale = partita.getStanzaCorrente();
		partita.getStanzaCorrente().setPersonaggio(p);
		p.agisci(partita);
		assertEquals(new Stanza("Aula N10"), partita.getStanzaCorrente());
		partita.setStanzaCorrente(stanzaIniziale);
		p.saluta();
		assertTrue(p.haSalutato());
		assertEquals(new Stanza("Atrio"), partita.getStanzaCorrente());
		partita.setStanzaCorrente(stanzaIniziale);
		p.riceviRegalo(new Attrezzo("spada", 4), partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

}
