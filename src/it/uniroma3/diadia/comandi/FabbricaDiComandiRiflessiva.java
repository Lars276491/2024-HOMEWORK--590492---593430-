package it.uniroma3.diadia.comandi;

import java.util.Scanner;

/* ho sostituito FabbricaDiComandiFisarmonica con quella riflessiva nel codice (poi ricontrollare che non mi sia sfuggito da qualche parte) e poi
 * capire se devo fare questa sostituzione anche nei test e in caso farla */

 
 /* questo qui sotto è il copia e incolla dalle slides, capire se devo modificare qualcosa oppure se va bene così*
public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	public Comando costruisciComando(String istruzione) {//qui dopo (String istruzione) c'era throws exception e marco mi ha detto di levarla, capire se va bene
		Scanner scannerDiParole = new Scanner(istruzione); // es. ‘vai sud’
		String nomeComando = null; // es. ‘vai’
		String parametro = null; // es. ‘sud’
		Comando comando = null;

		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();//prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();//seconda parola: eventuale parametro
		if(nomeComando!=null) // harold mi stava dicendo di scriverlo
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) ); 
		// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoV’
		nomeClasse.append( nomeComando.substring(1) ) ;
		// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’
		try {
			comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			comando=new ComandoNonValido();
		} // marco dice che anche se qui da errore chissenefrega perchè comunque funziona e va bene così
		comando.setParametro(parametro);
		return comando;
	} 
}*/

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	/* notion lo mette per ora commento
	private IO io;
	public FabbricaDiComandiRiflessiva(IO io) {
		this.io = io;
	}*/
	
	public Comando costruisciComando(String istruzione) { //throws Exception {
		Scanner scannerDiParole = new Scanner(istruzione); // es. â€˜vai sudâ€™
		String nomeComando = null; // es. â€˜vaiâ€™
		String parametro = null; // es. â€˜sudâ€™
		Comando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();//prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();//seconda parola: eventuale parametro
		
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
		// es. nomeClasse: â€˜it.uniroma3.diadia.comandi.ComandoVâ€™
		nomeClasse.append( nomeComando.substring(1) ) ;
		// es. nomeClasse: â€˜it.uniroma3.diadia.comandi.ComandoVaiâ€™
		comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
		comando.setParametro(parametro);
	//	comando.setIo(io); notion lo mette per ora commento
		return comando;
	}	
}