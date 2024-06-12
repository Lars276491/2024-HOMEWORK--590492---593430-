package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import it.uniroma3.diadia.Costanti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {

	private final static int SOGLIA_MAGICA_DEFAULT = getSogliaMagicaDefault();

	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	public static int getSogliaMagicaDefault() {
		int soglia_magica_default = 3;

		Properties properties = new Properties();
		Reader fileReader = null;
		Writer fileWriter = null;

		try {
			fileReader = new FileReader("diadia.properties.txt");
			properties.load(fileReader);
			if(properties.getProperty("soglia_magica_default") != null)
				soglia_magica_default = Integer.parseInt(properties.getProperty("soglia_magica_default"));
			else
				properties.setProperty("soglia_magica_default", Integer.toString(soglia_magica_default));
			return soglia_magica_default;
		} catch (IOException e) {
				return soglia_magica_default;
		} finally {
			try {
				fileWriter = new FileWriter("diadia.properties.txt");
				properties.store(fileWriter, "Costanti gioco diadia");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(fileReader != null)
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(fileWriter != null)
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati > this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		return attrezzo;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		StanzaMagica that = (StanzaMagica) obj;
		return super.equals(obj) && this.sogliaMagica == that.getSogliaMagica();
	}

	public int getSogliaMagica() {
		return this.sogliaMagica;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + this.sogliaMagica;
	}

	public boolean isMagica() {
		return this.sogliaMagica >= 0;
	}
}
