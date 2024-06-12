package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
public class ComandoGuarda extends AbstractComando{
	@Override
	public void esegui(Partita partita) {
		System.out.println(partita.getGiocatore().getBorsa().toString());
		System.out.println(partita.getStanzaCorrente().toString());
	}
	@Override
	public String getNome() {
		return "guarda";
	}
}
