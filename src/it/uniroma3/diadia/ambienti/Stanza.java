package it.uniroma3.diadia.ambienti;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */
public class Stanza {
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Map<String,Attrezzo> attrezzi;    
	private int numeroAttrezzi;

	private Map<String,Stanza> stanzeAdiacenti;  

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroAttrezzi = 0;
		this.stanzeAdiacenti = new HashMap<>(NUMERO_MASSIMO_DIREZIONI);
		this.attrezzi = new HashMap<>(NUMERO_MASSIMO_ATTREZZI);
	}
	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		this.stanzeAdiacenti.put(direzione, stanza);
	}
	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}
	
	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}
	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}
	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return 
	 * @return la collezione di attrezzi nella stanza.
	 */
	/* qui ho cambiato Collection<Attrezzi> con
	 * Map<String, Attrezzo> perchè con collection
	 * era generico (capire meglio perchè ho dovuto 
	 * togliere collection e mettere Map<String, Attrezzo>
	 */
	public  Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}
	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false altrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null) {
			if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
				this.attrezzi.put(attrezzo.getNome(), attrezzo);
				return true;
			}
		}
		return false;
	}
	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampandone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.getDirezioni())
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		/* ho modificato qui perchè prima avevo fatto
		 * for(Attrezzo attrezzo : this.getAttrezzi()){
		 * 		if(attrezzo!=null)
					risultato.append(attrezzo.toString()+" ");
		   } e avendo cambiato sopra con public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi; dovevo cambiare anche questo for
	}
		 */
		for (String nomeAttrezzo : this.getAttrezzi().keySet()) {
			Attrezzo attrezzo=this.getAttrezzi().get(nomeAttrezzo);
			if(attrezzo!=null)
				risultato.append(attrezzo.toString()+" ");
		}
		return risultato.toString();
	}
	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	
	}
	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);	
	}
	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		//verifico che ci sia
		if(attrezzo != null)
		return this.attrezzi.remove(attrezzo.getNome()) != null;
		return false;
	}
	public  Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}
	
	/* questo ritorna la mappa delle stanze adiacenti */
	public Map<String,Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}
	
}




