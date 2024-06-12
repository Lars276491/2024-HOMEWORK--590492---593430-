package it.uniroma3.diadia;

import java.util.List;
import java.util.ArrayList;

public class IOSimulator implements IO {

	private int numeroRighelette;
	private List<String> sequenzaComandi;

	public IOSimulator(List<String> sequenzaComandi) {
		this.numeroRighelette = 0;
		this.sequenzaComandi = new ArrayList<>(sequenzaComandi);
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	@Override
	public String leggiRiga() {
		if(this.sequenzaComandi.isEmpty()) {
			this.mostraMessaggio("Lista comandi vuota");
			return "fine";
		}
		if (this.numeroRighelette == this.sequenzaComandi.size()) {
			this.mostraMessaggio("Superati numero di comandi automatici");
			return "fine";
		}else
			this.mostraMessaggio(this.sequenzaComandi.get(numeroRighelette));
		return this.sequenzaComandi.get(numeroRighelette++);

	}

}
