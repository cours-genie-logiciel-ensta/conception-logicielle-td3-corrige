package banqueServeur;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Représente une banque gérant un compte bancaire particulier
 *
 */
public class Banque implements IBanque {

	private int port = 6665;
	private int hport = 7777;
	/** Les serveurs pour la mise en oeuvre de connexion a distance */
	public ArrayList<ServeurTCP> serveurs = new ArrayList<ServeurTCP>();
	/** Le compte en Banque */
	private CompteBancaire leCompte;

	/** Le type d'operation : depot ou retrait */
	private String typeOperation;
	private IDemandeRetrait strategyretrait;
	private IDemandeDepot strategydepot;

	private ArrayList<String> historiqueOperations;

	/**
	 * Support du mécanisme Observable/Observer
	 */
	private final PropertyChangeSupport pcSupport;

	public Banque(int uneSomme) {

		pcSupport = new PropertyChangeSupport(this);

		leCompte = new CompteBancaire(uneSomme);
		typeOperation = "Aucune operation";
		historiqueOperations = new ArrayList<String>();
	}

	public Banque(int uneSomme, IDemandeRetrait stratR, IDemandeDepot stratD) {
		this(uneSomme);
		strategyretrait = stratR;
		strategydepot = stratD;
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return pcSupport;
	}

	public void ouvrirBanque() {
		serveurs.add(new ServeurTCP(this, new ProtocoleRetraitDepot(), port));
		serveurs.add(new ServeurTCP(this, new ProtocoleAfficherHistorique(), hport));
		for (ServeurTCP s : serveurs) {
			s.start();
		}
	}

	@Override
	public String toString() {
		return "La Banque possede un compte avec la somme de " + getLeCompte().getSomme();
	}

	@Override
	public synchronized int demandeRetrait(int unRetrait) {
		return strategyretrait.demandeRetrait(unRetrait, this);
	}

	@Override
	public synchronized int demandeDepot(int unDepot) {
		return strategydepot.demandeDepot(unDepot, this);
	}

	public String getTypeOperation() {
		return typeOperation;
	}

	@Override
	public CompteBancaire getLeCompte() {
		return leCompte;
	}

	@Override
	public void setTypeOperation(String s) {
		typeOperation = s;
	}

	@Override
	public void faireOperation(String string) {

		String dernierTypeOperation = typeOperation;
		int valeurInitiale = leCompte.getSomme();

		this.historiqueOperations.add(string);
		this.setTypeOperation(string);

		pcSupport.firePropertyChange("somme", valeurInitiale, leCompte.getSomme());
		pcSupport.firePropertyChange("typeOperation", dernierTypeOperation, typeOperation);

	}

	public void setStrategyretrait(String typeRetrait) {
		if (typeRetrait.equals(StrategieUI.retraitSimple))
			strategyretrait = new DemandeRetraitSimple();
		else if (typeRetrait.equals(StrategieUI.retraitPlafond))
			strategyretrait = new DemandeRetraitPlafond(100);
	}

	public void setStrategydepot(String typeDepot) {
		if (typeDepot.equals(StrategieUI.depotSimple))
			strategydepot = new DemandeDepotSimple();
		else if (typeDepot.equals(StrategieUI.depotTemporise))
			strategydepot = new DemandeDepotTemporise();
	}

	public ArrayList<String> getHistoriqueOperations() {
		return historiqueOperations;
	}

}
