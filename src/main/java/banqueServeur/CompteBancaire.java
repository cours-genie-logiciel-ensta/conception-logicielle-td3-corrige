package banqueServeur;

public class CompteBancaire {

	private int somme = 0;

	public CompteBancaire(int somme) {
		this.somme = somme;
	}

	public int getSomme() {
		return somme;
	}

	public void setSomme(int somme) {
		this.somme = somme;
	}

}
