package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Questa classe modella un comando. Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro su cui si applica il comando. (Ad es.
 * alla riga digitata dall'utente "vai nord" corrisponde un comando di nome
 * "vai" e parametro "nord").
 *
 * @author docente di POO e 589489 e 589300
 * @version v1.0
 */

public interface Comando {

	/**
	 * esecuzione del comando
	 */
	public void esegui(Partita partita, IO io);

	/**
	 * set parametro del comando
	 */
	public void setParametro(String parametro);

	/**
	 * get parametro del comando
	 * 
	 * @return parametro
	 */
	public String getParametro();

	/**
	 * get nome comando
	 * 
	 * @return nome comando
	 */
	public String getNome();
}