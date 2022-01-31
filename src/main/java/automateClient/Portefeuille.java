package automateClient;

public class Portefeuille {

	
	int somme = 0;
		
	public Portefeuille( int uneSomme) {
		super();
		this.somme = uneSomme;
	}

	public int getSomme() {
		return somme;
	}

	public void setSomme(int uneSomme) {
		this.somme = uneSomme;
	}

	public void setArgentPoche(int uneSomme) {
        System.out.println("Somme déposée dans le porteFeuille "+ uneSomme );
		this.setSomme( uneSomme );
	}

	public String toString() {
		return " Somme du portefeuille = " + somme;
	}
	    
}
