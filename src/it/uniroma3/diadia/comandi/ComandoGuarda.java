package it.uniroma3.diadia.comandi;

import java.util.List;
import java.util.SortedSet;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuarda extends AbstractComando{

	public ComandoGuarda() {
		super(null);
	}

	@Override
	public void esegui(Partita partita, IO io) {
		StringBuilder s = new StringBuilder();
		s.append(partita.getGiocatore().getCfu());
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		io.mostraMessaggio(s.toString());
	}

	@Override
	public String getNome() {
		return "Comando guarda";
	}

}
