package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala implements Comando {

    private String parametro;
    private IO io;

    @Override
    public void esegui(Partita partita) {
        if (parametro != null && !parametro.isEmpty()) {
            Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(parametro);
            if (attrezzo != null) {
                AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
                if (personaggio != null) {
                    String messaggio = personaggio.riceviRegalo(attrezzo, partita);
                    io.mostraMessaggio(messaggio);
                } else {
                    io.mostraMessaggio("Non c'è nessun personaggio con cui interagire.");
                }
            } else {
                io.mostraMessaggio("Non possiedi un attrezzo con quel nome.");
            }
        } else {
            io.mostraMessaggio("Devi specificare quale attrezzo vuoi regalare.");
        }
    }
    
    @Override
    public String getNome() {
        return "regala";
    }

    @Override
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    @Override
    public String getParametro() {
        return this.parametro;
    }
}