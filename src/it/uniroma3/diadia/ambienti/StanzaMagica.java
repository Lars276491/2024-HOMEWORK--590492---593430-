package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {

	private int contatoreAttrezziPosati;

	private int sogliaMagica;

	private static final int SOGLIA_MAGICA_DEFAULT = 1;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}


	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),
				pesoX2);
		return attrezzo;
	}

	/* il metodo di test testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica ora funziona perchè ho aggiunto a questo metodo
	 * addAttrezzo qui sotto, ho aggiunto = oltre a >, cioè ora ho messo >= prima era solo > e non funzionava*/
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.contatoreAttrezziPosati>=this.sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
		}
		this.contatoreAttrezziPosati++;

		return super.addAttrezzo(attrezzo);
	}

	public boolean isMagica() {
		return this.contatoreAttrezziPosati > this.sogliaMagica;
	}

}
