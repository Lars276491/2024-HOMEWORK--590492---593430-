package it.uniroma3.diadia;

import java.io.*;
import java.net.URI;
import java.util.*;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze bloccate nel formato <nomeStanza> <direzioneBloccata> <chiave>*/
	private static final String STANZE_BLOCCATE_MARKER = "Stanze bloccate:";

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze buie nel formato <nomeStanza> <chiave>*/
	private static final String STANZE_BUIE_MARKER = "Stanze buie:";    

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze bloccate nel formato <nomeStanza> <sogliaMagica>*/
	private static final String STANZE_MAGICHE_MARKER = "Stanze magiche:";    

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei personaggi da collocare nel formato <tipoPersoanggio> <nomePersonaggio> <nomeAttrezzo> <pesoAttrezzo> <nomeStanza>*/
	private static final String PERSONAGGI_MARKER = "Personaggi:";

	/* prefisso della riga contenente le specifiche delle presentazioni dei personaggi da collocare rispettando l'ordine di aggiunta dei personaggi*/
	private static final String PRESENTAZIONI_MARKER = "Presentazioni:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Stanze bloccate: prigione nord chiave
		Stanze buie: cantina lampada
		Stanze magiche: 
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11
		Personaggi: Merlino bastone 2 N10
		Presentazioni: Ciao sono il mago Merlino e' un piacere fare la tua conoscenza

	 */
	private LineNumberReader reader;
	private LabirintoBuilder costruttoreLabirinto;


	public CaricatoreLabirinto(URI nomeFile) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(new File(nomeFile)));
		this.costruttoreLabirinto = new LabirintoBuilder();
	}

	public CaricatoreLabirinto(StringReader strReader) {
		this.reader = new LineNumberReader(strReader);
		this.costruttoreLabirinto = new LabirintoBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECollocaPersonaggi();
			this.leggiECollocaPresentazioni();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length()+1);
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);

		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			String direzioneBloccata = null;
			String chiave = null; 
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza bloccata."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata della stanza "+nomeStanza+"."));
				direzioneBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la chiave della stanza bloccata "+nomeStanza+"."));
				chiave = scannerLinea.next();
			}
			this.costruttoreLabirinto.addStanzaBloccata(nomeStanza, direzioneBloccata, chiave);
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);

		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			String chiave = null; 
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza buia."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la chiave della stanza buia "+nomeStanza+"."));
				chiave = scannerLinea.next();
			}
			this.costruttoreLabirinto.addStanzaBuia(nomeStanza, chiave);
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);

		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			String magia = null; 
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza magica."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la chiave della stanza magica "+nomeStanza+"."));
				magia = scannerLinea.next();
			}
			this.costruttoreLabirinto.addStanzaMagica(nomeStanza, Integer.parseInt(magia));
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			costruttoreLabirinto.addStanza(nomeStanza);
		}
	}

	private void leggiECollocaPresentazioni() throws FormatoFileNonValidoException  {
		String presentazioni = this.leggiRigaCheCominciaPer(PRESENTAZIONI_MARKER);
		List<String> listaPresentazioni = separaStringheAlleVirgole(presentazioni);
		for(int i = 0; i < listaPresentazioni.size(); i++) {
			costruttoreLabirinto.getPersonaggi().get(i).setPresentazione(listaPresentazioni.get(i));;
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		costruttoreLabirinto.addStanzaIniziale(nomeStanzaIniziale);
		costruttoreLabirinto.addStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);

		for(String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String tipoPersonaggio = null;
			String nomePersonaggio = null;
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo di un personaggio."));
				tipoPersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo del personaggio "+nomePersonaggio+"."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il personaggio "+nomePersonaggio+"."));
				nomeStanza = scannerLinea.next();

			}				
			this.costruttoreLabirinto.addPersonaggio(tipoPersonaggio, nomePersonaggio, nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.costruttoreLabirinto.getListaStanze().get(this.costruttoreLabirinto.getListaStanze().indexOf(new Stanza(nomeStanza))).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		for(Stanza stanza : this.costruttoreLabirinto.getListaStanze())
			if(stanza.getNome().equals(nomeStanza))
				return true;
		return false;
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiFrase = new Scanner(specificheUscite)) {			
			scannerDiFrase.useDelimiter(", ");
			while (scannerDiFrase.hasNext()) {
				Scanner scannerDiLinea = new Scanner(scannerDiFrase.next());
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next();

				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.costruttoreLabirinto.getListaStanze().get(costruttoreLabirinto.getListaStanze().indexOf(new Stanza(stanzaDa)));
		Stanza arrivoA = this.costruttoreLabirinto.getListaStanze().get(costruttoreLabirinto.getListaStanze().indexOf(new Stanza(nomeA)));
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
		costruttoreLabirinto.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.costruttoreLabirinto.getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return this.costruttoreLabirinto.getStanzaVincente();
	}

	public Labirinto getLabirinto() {
		return this.costruttoreLabirinto.getLabirinto();
	}

	public LabirintoBuilder getcostruttoreLabirinto() {
		return this.costruttoreLabirinto;
	}
}