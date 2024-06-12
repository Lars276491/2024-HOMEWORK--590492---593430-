package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;


import org.junit.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.Labirinto;

public class FabbricaDiComandiRiflessivaTest {

	@Test
	public void testComandoNonValido() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("nulla");
		assertEquals("Comando non valido", comando.getNome());
	}
	
	@Test
	public void testComandoVai() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("vai nord");
		assertEquals("Comando vai", comando.getNome());
	}
	
	@Test
	public void testComandoVaiSenzaDirezione() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("vai");
		assertNull(comando.getParametro());
	}

	@Test
	public void testComandoVaiConDirezione() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("vai nord");
		assertEquals("nord", comando.getParametro());
	}
	
	@Test
	public void testComandoPrendi() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("prendi osso");
		assertEquals("Comando prendi", comando.getNome());

	}
	
	@Test
	public void testComandoPrendiSenzaParametro() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("prendi");
		assertNull(comando.getParametro());

	}
	
	@Test
	public void testComandoPrendiConParametro() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("prendi osso");
		assertEquals("osso", comando.getParametro());

	}
	
	@Test
	public void testComandoPosa() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("posa osso");
		assertEquals("Comando posa", comando.getNome());

	}
	
	@Test
	public void testComandoPosaSenzaParametro() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("posa");
		assertNull(comando.getParametro());

	}
	
	@Test
	public void testComandoPosaConParametro() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("posa osso");
		assertEquals("osso", comando.getParametro());

	}

}
