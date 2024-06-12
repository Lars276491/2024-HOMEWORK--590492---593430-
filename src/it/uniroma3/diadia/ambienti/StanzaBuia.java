package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	private String attrezzoLuminoso;

	public StanzaBuia(String nome, String attrezzoLuminoso) {
		super(nome);
		this.attrezzoLuminoso = attrezzoLuminoso;
	}
	
	public String getChiave() {
		return this.attrezzoLuminoso;
	}

	@Override

	public String getDescrizione() {
		if (this.hasAttrezzo(this.attrezzoLuminoso))
			return super.getDescrizione();
		else
			return "qui c'è un buio pesto";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		StanzaBuia that = (StanzaBuia) obj;
		return super.equals(obj) && this.attrezzoLuminoso.equals(that.getChiave());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + this.attrezzoLuminoso.hashCode();
	}
}
