package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;
/*
public class Cane extends AbstractPersonaggio {
    private static final String MESSAGGIO_MORSO = "Arrabbiato e affamato, ti ho morso!";
    private static final String MESSAGGIO_SCUSE = "Oggi sono buono, non mordo... per ora.";

    public Cane(String nome, String presentazione) {
        super(nome, presentazione);
    }

    @Override
    public String agisci(Partita partita) {
        String msg;
        if (partita.getGiocatore().getCFU() > 0) {
            partita.getGiocatore().diminuisciCFU();
            msg = MESSAGGIO_MORSO;
        } else {
            msg = MESSAGGIO_SCUSE;
        }
        return msg;
    }
}*/

public class Cane extends AbstractPersonaggio {
    private static final String MESSAGGIO_MORSO = "Arrabbiato e affamato, ti ho morso!";
    private static final String MESSAGGIO_SCUSE = "Oggi sono buono, non mordo... per ora.";

    public Cane(String nome, String presentazione, Attrezzo attrezzo, Attrezzo Cibo) {
        super(nome, presentazione);
    }
    
    public Cane(String nome, String presentazione) {
    	super(nome, presentazione);
    }

    @Override
    public String agisci(Partita partita) {
        Giocatore giocatore = partita.getGiocatore();
        if (giocatore.getCfu() > 0) {
            giocatore.setCfu(giocatore.getCfu() - 1); // Assumiamo che ogni morso riduca di 1 CFU
            return MESSAGGIO_MORSO;
        } else {
            return MESSAGGIO_SCUSE;
        }
    }
    
    @Override
    public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
        if (attrezzo.getNome().equals("cibo preferito")) {
            partita.getStanzaCorrente().removeAttrezzo(attrezzo); // Il cane butta a terra l'attrezzo
            return getNome() + " gioisce per il suo cibo preferito e lascia cadere " + attrezzo.getNome();
        } else {
            partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1); // Il cane morde
            return getNome() + " non apprezza il regalo e ti morde, perdendo un CFU!";
        }
    }
    
    /* confrontare con quelli di notion e capire alla fine quali mettere */
}
