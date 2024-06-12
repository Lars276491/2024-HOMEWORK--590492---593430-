package it.uniroma3.diadia.personaggi;

import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
/*
public class Strega extends AbstractPersonaggio {
    private static final String MESSAGGIO_TRASFERIMENTO = "Ahahah! Con un pizzico di magia ti ho spostato ";
    private static final String MESSAGGIO_SCUSE = "Oggi non ho voglia di incantesimi...";

    public Strega(String nome, String presentazione) {
        super(nome, presentazione);
    }

    @Override
    public String agisci(Partita partita) {
        Stanza stanzaCorrente = partita.getStanzaCorrente();
        Stanza stanzaDestinazione;
        String msg;

        if (!this.haSalutato()) {
            stanzaDestinazione = stanzaCorrente.getStanzaConMenoAttrezzi();
            msg = MESSAGGIO_TRASFERIMENTO + "nella stanza meno fornita!";
        } else {
            stanzaDestinazione = stanzaCorrente.getStanzaConPiuAttrezzi();
            msg = MESSAGGIO_TRASFERIMENTO + "nella stanza più fornita!";
        }

        partita.setStanzaCorrente(stanzaDestinazione);
        return msg;
    }
}*/

/*Devi aggiungere il metodo riceviRegalo(Attrezzo, Partita) perché la tua classe AbstractPersonaggio è una classe astratta che definisce questo metodo come 
 * astratto. In Java, quando una classe è dichiarata astratta, essa può contenere metodi astratti, ovvero metodi senza un’implementazione concreta.

Le classi che estendono una classe astratta devono fornire un’implementazione per tutti i metodi astratti della classe base, altrimenti anche la classe derivata 
deve essere dichiarata astratta. Ecco perché Eclipse ti segnala un errore: le classi Cane e Strega estendono AbstractPersonaggio ma non forniscono 
un’implementazione per il metodo astratto riceviRegalo.

In altre parole, il contratto della classe astratta AbstractPersonaggio impone che ogni classe concreta che la estende deve sapere come “ricevere un regalo”. 
Se non vuoi che Cane e Strega abbiano questa capacità, potresti considerare di rimuovere il metodo astratto dalla classe AbstractPersonaggio o di fornire 
un’implementazione predefinita (non astratta) in essa. Tuttavia, se il metodo riceviRegalo è essenziale per la logica del tuo gioco, allora dovrai implementarlo 
in tutte le classi derivate. */

public class Strega extends AbstractPersonaggio {
    private static final String MESSAGGIO_SE_NON_SALUTATA= "Ti meriti di andare nella stanza con meno attrezzi!!";
    private static final String MESSAGGIO_SE_SALUTATA = "Ti concedo di andare nella stanza con più attrezzi!!";

    public Strega(String nome, String presentazione) {
        super(nome, presentazione);
    }

    @Override
	public String agisci(Partita partita) {
		String msg;
		List<Stanza> stanzeAdiacenti = partita.getStanzaCorrente().getStanzeAdiacenti();

		Stanza maxAtrezzi = stanzeAdiacenti.get(0);
		Stanza minAtrezzi = stanzeAdiacenti.get(0);

		for(Stanza s : stanzeAdiacenti) {
			if(s != null) {
				if(s.getNumeroAttrezzi() > maxAtrezzi.getNumeroAttrezzi())
					maxAtrezzi = s;
				if(s.getNumeroAttrezzi() < minAtrezzi.getNumeroAttrezzi())
					minAtrezzi = s;
			}
		}

		if(this.haSalutato()) {
			partita.setStanzaCorrente(maxAtrezzi);
			msg = MESSAGGIO_SE_SALUTATA;
		} else {
			partita.setStanzaCorrente(minAtrezzi);
			msg = MESSAGGIO_SE_NON_SALUTATA;
		}

		return msg;
	}
/* come ho visto su notion */
    @Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "AHAHAHAHAHAHHA";
	}

}