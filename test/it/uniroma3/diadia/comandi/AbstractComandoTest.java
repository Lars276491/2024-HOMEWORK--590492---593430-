package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class AbstractComandoTest {
	private AbstractComando comando;

    @Before
    public void setUp() {
        // Creiamo una sottoclasse concreta di AbstractComando per i test
        comando = new AbstractComando() {
            @Override
            public void esegui(Partita partita) {
                // Implementazione di prova
            }

            @Override
            public String getNome() {
                return "comandoTest";
            }
        };
 
    }

    @Test
    public void testSetParametro() {
        String parametro = "parametroTest";
        comando.setParametro(parametro);
        assertEquals(parametro, comando.getParametro());
    }

    @Test
    public void testGetNome() {
        assertEquals("comandoTest", comando.getNome());
    }
}

