package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
public class ComandoPrendi extends AbstractComando{
	IO io = new IOConsole();	
	@Override
	public void esegui(Partita partita) {
		if(super.getParametro()==null)
			io.mostraMessaggio("Che attrezzo vuoi prendere");
		Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(super.getParametro());

		if(!partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) 
			io.mostraMessaggio("Impossibile prendere attrezzo");
		else {
			partita.getStanzaCorrente().removeAttrezzo(attrezzo);
			io.mostraMessaggio("L'attrezzo è stato aggiunto alla borsa");
		}
	}
	@Override
	public String getNome() {
		return "Comando prendi";
	}
}
