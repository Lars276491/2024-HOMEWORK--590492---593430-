package it.uniroma3.diadia.comandi;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FabbricaDiComandiFisarmonicaTest {
	private FabbricaDiComandiFisarmonica fabbrica;

	@Before
	public void setUp() {
		fabbrica = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testCostruisciComando() {
		Comando comando = fabbrica.costruisciComando("vai nord");
		assertTrue(comando instanceof ComandoVai);
		assertEquals("nord", comando.getParametro());

		comando = fabbrica.costruisciComando("prendi attrezzo");
		assertTrue(comando instanceof ComandoPrendi);
		assertEquals("attrezzo", comando.getParametro());

		comando = fabbrica.costruisciComando("posa attrezzo");
		assertTrue(comando instanceof ComandoPosa);
		assertEquals("attrezzo", comando.getParametro());

		comando = fabbrica.costruisciComando("aiuto");
		assertTrue(comando instanceof ComandoAiuto);

		comando = fabbrica.costruisciComando("fine");
		assertTrue(comando instanceof ComandoFine);

		comando = fabbrica.costruisciComando("guarda");
		assertTrue(comando instanceof ComandoGuarda);

		comando = fabbrica.costruisciComando("non_valido");
		assertTrue(comando instanceof ComandoNonValido);
	}
}
