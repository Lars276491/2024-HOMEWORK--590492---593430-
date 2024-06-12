package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	
	private String nome;
	private String presentazione;
	private Attrezzo attrezzo;
	private boolean haSalutato;

	public AbstractPersonaggio(String nome, String presentaz, Attrezzo attrezzo) {
		this.nome = nome;
		this.presentazione = presentaz;
		this.attrezzo = attrezzo;
		this.haSalutato = false;
	}
	
	public AbstractPersonaggio() {
		this(null, null, null);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}

	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");

		risposta.append(this.getNome()+".");
		if (!haSalutato)

			risposta.append(this.presentazione);

		else

			risposta.append("Ci siamo gia' presentati!");

		this.haSalutato = true;
		return risposta.toString();
	}
	
	public void setPresentazione(String presentazione) {
		this.presentazione = presentazione;
	}
	
	public Attrezzo getAttrezzo() {
		return attrezzo;
	}

	public void setAttrezzo(Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}

	@Override
	public String toString() {
		return this.getNome();
	}

	abstract public String agisci(Partita partita);
	
	public abstract String riceviRegalo(Attrezzo a, Partita partita);
}
