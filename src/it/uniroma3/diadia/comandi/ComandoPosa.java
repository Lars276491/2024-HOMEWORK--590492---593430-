package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.*;
public class ComandoPosa extends AbstractComando{
	IO io = new IOConsole();	
	@Override
	public void esegui(Partita partita) {
		if(partita == null)
			return;
		if(super.getParametro()==null)
			io.mostraMessaggio("Che attrezzo vuoi posare");
		else {
			Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(super.getParametro());
			if(partita.getStanzaCorrente().addAttrezzo(attrezzo))
				io.mostraMessaggio("L'attrezzo è stato posato nella stanza");
			else {
				partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
				io.mostraMessaggio("Impossibile posare attrezzo");
			}
		}
	}
	@Override
	public String getNome() {
		return "Comando posa";
	}
}
