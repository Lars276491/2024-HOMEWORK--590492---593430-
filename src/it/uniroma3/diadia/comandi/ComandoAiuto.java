package it.uniroma3.diadia.comandi;

import java.io.InputStream;
import java.io.InputStreamReader;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = { "vai", "prendi", "posa", "aiuto", "guarda", "interagisci", "saluta", "regala", "fine" };
	
	public ComandoAiuto() {
		super(null);
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita, IO io) {
		
		String str = "";
		for (int i = 0; i < elencoComandi.length; i++)
			str = str + elencoComandi[i] + " ";
		io.mostraMessaggio(str);

		
	}

	@Override
	public String getNome() {
		return "Comando aiuto";
	}

}
