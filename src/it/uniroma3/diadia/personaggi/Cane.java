package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static final String SALUTO = "BAU! (Ciao, sono un cane ma non sono affettuoso, ti avverto...)";
	
	private static final String MORSO = "BAU, BAU!!! ARGGGGG... ACK" + '\n' +
			"(Hai perso un CFU)";
	
	public Cane(String nome, Attrezzo attrezzo) {
		super(nome, SALUTO, attrezzo);

	}
	
	public Cane(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione, attrezzo);

	}
	
	public Cane() {
		super(null, null, null);

	}
	
	@Override
	public String saluta() {
		super.saluta();
		return SALUTO;
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo a, Partita partita) {
		this.setAttrezzo(a);
		partita.getStanzaCorrente().addAttrezzo(partita.getStanzaCorrente().getPersonaggio().getAttrezzo());
		return "BAU!" + " " + this.agisci(partita);
			
	}

}
