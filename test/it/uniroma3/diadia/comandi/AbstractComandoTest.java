package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractComandoTest {

	@Test
	public void testComandoAiuto() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("aiuto");
		assertEquals("Comando aiuto", comando.getNome());

	}
	
	@Test
	public void testComandoFine() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("fine");
		assertEquals("Comando fine", comando.getNome());

	}
	
	@Test
	public void testComandoGuarda() {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		Comando comando = fabbrica.costruisciComando("guarda");
		assertEquals("Comando guarda", comando.getNome());

	}
	
	@Test
	public void testMetodiAbstractComando() {
		/*Usiamo un oggetto di ComandoAiuto per testare i metodi di AbstractComando*/
		AbstractComando c = new ComandoAiuto();
		c.setParametro("parametro settato");
		assertEquals("parametro settato", c.getParametro());
	}
}
