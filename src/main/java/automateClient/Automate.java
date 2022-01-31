package automateClient;

import java.util.Observable;

/* Une personne a la fois se presente a un automate */
public class Automate extends Observable implements IAutomate {

	private int port = 6666;
	private Portefeuille porteMonnaie;
	private ClientTCP monClientTCP;

	public Automate(int sommeenpoche) {
		monClientTCP = new ClientTCP("localhost", port);
		porteMonnaie = new Portefeuille(sommeenpoche);
	}

	public void setArgentEnPoche(int uneSomme) {
		porteMonnaie.setArgentPoche(uneSomme);
	}

	public int getArgentEnPoche() {
		return porteMonnaie.getSomme();
	}

	public void depotPoche(int unDepot) {
		this.setArgentEnPoche(porteMonnaie.getSomme() + unDepot);
		System.out.println("Somme en poche finale " + porteMonnaie.getSomme());
	}

	@Override
	public void retraitPoche(int unRetrait) {
		System.out.println("Avant  " + porteMonnaie.getSomme() + " - " + unRetrait);
		porteMonnaie.setArgentPoche(porteMonnaie.getSomme() - unRetrait);
		System.out.println("Somme en poche finale " + porteMonnaie.getSomme());
	}

	public int demandeRetrait(int laSomme) {
		int valeurRetrait = 0;
		String valeurTransmise = monClientTCP.transmettreChaine("retrait " + laSomme);
		valeurRetrait = Integer.parseInt(valeurTransmise);

		depotPoche(valeurRetrait);

		setChanged();
		notifyObservers();
		return valeurRetrait;
	}

	public int demandeDepot(int laSomme) {
		int valeurDepot = 0;
		String valeurTransmise = monClientTCP.transmettreChaine("depot " + laSomme);
		valeurDepot = Integer.parseInt(valeurTransmise);

		retraitPoche(valeurDepot);

		setChanged();
		notifyObservers();
		return valeurDepot;
	}

	@Override
	public boolean connexionBanque() {
		return monClientTCP.connecterAuServeur();
	}

	@Override
	public void deconnexionBanque() {
		monClientTCP.deconnecterDuServeur();
	}

	@Override
	public String toString() {
		return "Somme en poche : " + porteMonnaie;
	}

}
