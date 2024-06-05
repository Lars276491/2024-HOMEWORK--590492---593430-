package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;
public class ComandoFine extends AbstractComando{
	IO io= new IOConsole();
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Grazie di aver giocato!");
	}
	@Override
	public String getNome() {
		return "Comando fine";
	}
}
