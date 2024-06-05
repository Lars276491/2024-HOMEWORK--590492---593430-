package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
public class ComandoNonValido extends AbstractComando{
	@Override
	public void esegui(Partita partita) {
		System.out.println("Comando non valido");
	}
	@Override
	public String getNome() {
		return "Comando non valido";
	}
}
