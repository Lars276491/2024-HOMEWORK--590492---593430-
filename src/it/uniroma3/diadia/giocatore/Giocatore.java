package it.uniroma3.diadia.giocatore;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import it.uniroma3.diadia.Costanti;

/**
 * Questa classe gestisce il giocatore
 * 
 * @see Borsa
 * @author 589489 e 589300
 * @version v1.0
 */

public class Giocatore {

	static final private int CFU_INIZIALI = getCfuIniziali();

	private int cfu;
	private Borsa borsa;

	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public static int getCfuIniziali() {
		int cfuIniziali= 20;

		Properties properties = new Properties();
		Reader fileReader = null;
		Writer fileWriter = null;

		try {
			fileReader = new FileReader("diadia.properties.txt");
			properties.load(fileReader);
			if(properties.getProperty("cfu_iniziali") != null)
				cfuIniziali= Integer.parseInt(properties.getProperty("cfu_iniziali"));
			else
				properties.setProperty("cfu_iniziali", Integer.toString(cfuIniziali));
			return cfuIniziali;
		} catch (IOException e) {
			try {
				return cfuIniziali;
			}finally {
				try {
					fileWriter = new FileWriter("diadia.properties.txt");
					if(fileWriter != null) {
						properties.store(fileWriter, "Costanti gioco diadia");
						fileWriter.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
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

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public Borsa getBorsa() {
		return this.borsa;
	}

}
