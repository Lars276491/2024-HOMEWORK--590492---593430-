package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Classe che si occupa di gestire le interazioni I/O
 * 
 * @author 589489 e 589300
 * 
 * @version v1.0
 */

public class IOConsole implements IO {

	private Scanner scannerDiLinee;
	
	public IOConsole(Scanner scanner) {
		this.scannerDiLinee = scanner;
	}
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	public String leggiRiga() {
			scannerDiLinee = new Scanner(System.in);
			String riga = scannerDiLinee.nextLine();
			return riga;
		
	}
}
