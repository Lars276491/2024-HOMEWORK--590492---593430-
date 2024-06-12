package it.uniroma3.diadia.attrezzi;
import it.uniroma3.diadia.ambienti.Stanza;
/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Attrezzo implements Comparable<Attrezzo>{

	private String nome;
	private int peso;
	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}
	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}
	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}
	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
	
	@Override
	public boolean equals (Object o) {
		if (o instanceof Attrezzo) {
			Attrezzo a = (Attrezzo) o;
			return this.getNome().equals(a.getNome()) && this.getPeso() == a.getPeso();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
	    int result = 17;
	    result = 31 * result + this.getNome().hashCode();
	    result = 31 * result + this.getPeso();
	    return result;
	}

	@Override
	public int compareTo(Attrezzo that) {
		int cmp = this.getNome().compareTo(that.getNome());
		if(cmp == 0) {
			cmp = this.getPeso() - that.getPeso();
		}
		return cmp;
	}
}
