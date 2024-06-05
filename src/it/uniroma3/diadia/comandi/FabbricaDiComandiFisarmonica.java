/*
package it.uniroma3.diadia.comandi;
import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {

	public Comando costruisciComando(String istruzione) {

		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;

		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		if (nomeComando == null)
			comando = new ComandoNonValido();
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai();
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi();
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa();
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto();
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine();
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda();
		else comando = new ComandoNonValido();
		comando.setParametro(parametro);
		return comando;

	}
}*/

/* versione di bing che non crea elenco esplicito dei comandi (CAPIRE SE DEVO LASCIARE QUESTO O LA VERSIONE SOTTO) */
package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;

		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro

		if (nomeComando != null) {
			try {
				String nomeClasse = "it.uniroma3.diadia.comandi.Comando" + nomeComando.substring(0, 1).toUpperCase() + nomeComando.substring(1).toLowerCase();
				Class<?> classeComando = Class.forName(nomeClasse);
				comando = (Comando) classeComando.getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				comando = new ComandoNonValido();
			}
		} else {
			comando = new ComandoNonValido();
		}

		comando.setParametro(parametro);
		return comando;
	}
}

/* versione di bing che si differenzia da quella sopra perchè crea un elenco esplicito dei comandi. Un modo per farlo 
 * potrebbe essere utilizzare una mappa (ad esempio, HashMap) dove la chiave è il nome del comando e il valore è la classe 
 * del comando corrispondente.(CAPIRE SE DEVO LASCIARE QUESTO O LA VERSIONE SOPRA)
 */
package it.uniroma3.diadia.comandi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
    private Map<String, Class<? extends Comando>> mappaComandi;

    public FabbricaDiComandiFisarmonica() {
        mappaComandi = new HashMap<>();
        mappaComandi.put("vai", ComandoVai.class);
        mappaComandi.put("prendi", ComandoPrendi.class);
        mappaComandi.put("posa", ComandoPosa.class);
        mappaComandi.put("aiuto", ComandoAiuto.class);
        mappaComandi.put("fine", ComandoFine.class);
        mappaComandi.put("guarda", ComandoGuarda.class);
    }

    public Comando costruisciComando(String istruzione) {
        Scanner scannerDiParole = new Scanner(istruzione);
        String nomeComando = null;
        String parametro = null;
        Comando comando = null;

        if (scannerDiParole.hasNext())
            nomeComando = scannerDiParole.next(); // prima parola: nome del comando
        if (scannerDiParole.hasNext())
            parametro = scannerDiParole.next(); // seconda parola: eventuale parametro

        if (nomeComando != null && mappaComandi.containsKey(nomeComando)) {
            try {
                comando = mappaComandi.get(nomeComando).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                comando = new ComandoNonValido();
            }
        } else {
            comando = new ComandoNonValido();
        }

        comando.setParametro(parametro);
        return comando;
    }
}