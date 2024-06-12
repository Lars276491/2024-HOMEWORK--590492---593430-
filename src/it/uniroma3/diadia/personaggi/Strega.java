package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Direzioni;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	public Strega(String nome, String presentaz) {
		super(nome, presentaz, null);
	}
	
	public Strega() {
		super(null, null, null);
	}

	private static final String MESSAGGIO_DONO = "Che gentile che sei! " +
			"con una mia magica azione, ti trasferirò nella stanza adiacente con più attrezzi";
	
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non mi hai salutata, ora ti trasferisco "
			+ " nella stanza adiacente con meno attrezzi...";
	
	
	@Override
	public String agisci(Partita partita) {
		Map<Direzioni, Stanza> m = partita.getStanzaCorrente().getMapStanzeAdiacenti();
		

		if(haSalutato()) {
			Stanza stanzaMax = Collections.max(m.values(), new Comparator<Stanza>() {
				@Override
				public int compare(Stanza s1, Stanza s2) {
					if(s1.getAttrezzi().size() == s2.getAttrezzi().size())
						return s1.getNome().compareTo(s2.getNome());
					return s2.getAttrezzi().size() - s1.getAttrezzi().size();
				}
			});
			partita.setStanzaCorrente(stanzaMax);
			return MESSAGGIO_DONO;
		}else{
			Stanza stanzaMin = Collections.min(m.values(), new Comparator<Stanza>() {
				@Override
				public int compare(Stanza s1, Stanza s2) {
					if(s1.getAttrezzi().size() == s2.getAttrezzi().size())
						return s1.getNome().compareTo(s2.getNome());
					return s2.getAttrezzi().size() - s1.getAttrezzi().size();
				}
			});
			partita.setStanzaCorrente(stanzaMin);
			return MESSAGGIO_SCUSE;
		}
	}


	@Override
	public String riceviRegalo(Attrezzo a, Partita partita) {
		this.setAttrezzo(a);
		return "Grazie del regalo...ahahahahahah";
	}

}
