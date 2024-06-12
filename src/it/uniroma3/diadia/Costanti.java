package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

public class Costanti {
	private int soglia_magica_default = 3;
	private int default_peso_max_borsa = 10;
	private int cfu_iniziali = 20;
	
	private Properties properties;
	private Reader fileReader;
	private Writer fileWriter;
	
	public Costanti() {
		this.properties = new Properties();
		try {
			this.fileReader = new FileReader("diadia.properties.txt");
			this.fileWriter = new FileWriter("diadia.properties.txt");
			this.properties.load(this.fileReader);
			soglia_magica_default = Integer.parseInt(this.properties.getProperty("soglia_magica_default"));
			default_peso_max_borsa = Integer.parseInt(this.properties.getProperty("default_peso_max_borsa"));
			cfu_iniziali = Integer.parseInt(this.properties.getProperty("cfu_iniziali"));
		} catch (IOException e) {
			try {
				this.fileWriter = new FileWriter("diadia.properties.txt");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				this.fileReader = new FileReader("diadia.properties.txt");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			this.properties.setProperty("soglia_magica_default", Integer.toString(soglia_magica_default));
			this.properties.setProperty("default_peso_max_borsa", Integer.toString(default_peso_max_borsa));
			this.properties.setProperty("cfu_iniziali", Integer.toString(cfu_iniziali));
			try {
				this.properties.store(fileWriter, "Costanti gioco diadia");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(this.fileReader != null)
				try {
					this.fileReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(this.fileWriter != null)
				try {
					this.fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public int getSogliaMagicaDefault() {
		return soglia_magica_default;
	}

	public int getDefaultPesoMaxBorsa() {
		return default_peso_max_borsa;
	}

	public int getCfuIniziali() {
		return cfu_iniziali;
	}

	public void setSogliaMagicaDefault(int soglia_magica_default) {
		this.soglia_magica_default = soglia_magica_default;
	}

	public void setDefaultPesoMaxBorsa(int default_peso_max_borsa) {
		this.default_peso_max_borsa = default_peso_max_borsa;
	}

	public void setCfuIniziali(int cfu_iniziali) {
		this.cfu_iniziali = cfu_iniziali;
	}
	
	
	
}
