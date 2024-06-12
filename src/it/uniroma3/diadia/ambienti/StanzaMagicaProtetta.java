package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtetta extends StanzaProtetta {
	final static protected int SOGLIA_MAGICA_DEFAULT = 3;

	protected int contatoreAttrezziPosati;
	protected int sogliaMagica;

	/*
	 * chiama il "vero" costruttore che inizializza una stanzaProtetta (sua
	 * superclasse) e la soglia magica di default
	 */

	public StanzaMagicaProtetta(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagicaProtetta(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	/*
	 * visto che il prof vuole che sfruttiamo la visibilità protected possiamo fare
	 * override del metodo addAttrezzo che acceda direttamente ai campi di
	 * stanzaProtetta (ciò crea un grande accoppiamento!!!)
	 **/

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null) {
			return false;
		}
		this.contatoreAttrezziPosati++;

		// modifico l'attrezzo prima di inserirlo solo se necessario
		if (this.contatoreAttrezziPosati > this.sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
		}

		/*
		 * aggiungo l'attrezzo alla stanzaProtetta (accedendo direttamente ai suoi campi
		 * protected) -----------> ACCOPPIAMENTO! <-----------
		 */

		attrezzi.add(attrezzo);
		return true;

	}

	protected Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		return attrezzo;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		StanzaMagicaProtetta that = (StanzaMagicaProtetta) obj;
		return this.nome.equals(that.getNome()) && this.sogliaMagica == that.sogliaMagica;
	}

	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.nome.hashCode() +  this.sogliaMagica;
	}

}
