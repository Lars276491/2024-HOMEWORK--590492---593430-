package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta implements Comando {

    private IO io;

    @Override
    public void esegui(Partita partita) {
        AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
        if (personaggio != null) {
            String messaggio = personaggio.saluta();
            io.mostraMessaggio(messaggio);
        } else {
            io.mostraMessaggio("Non c'è nessun personaggio con cui salutare.");
        }
    }

    @Override
    public String getNome() {
        return "saluta";
    }

    @Override
    public void setParametro(String parametro) {
        // Questo comando non richiede parametri
    }

    @Override
    public String getParametro() {
        // Questo comando non richiede parametri
        return null;
    }
}
