package banqueServeur;
import java.util.ArrayList;
import java.util.Observable;

/** La banque : dans cet exercice, une banque correspond a un compte bancaire unique,
 *  pour des raisons didactiques */
public class Banque extends Observable implements IBanque {

	private int port = 6666;
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
	
	public Banque(int uneSomme) {
		leCompte = new CompteBancaire( uneSomme );
		typeOperation = "Aucune operation";
		historiqueOperations = new ArrayList<String>();
	}

	public Banque(int uneSomme, IDemandeRetrait stratR, IDemandeDepot stratD) {
		this(uneSomme);
		strategyretrait = stratR;	
		strategydepot = stratD;	
	}

	
	public void ouvrirBanque() {
		serveurs.add(new ServeurTCP(this, new ProtocoleRetraitDepot(), port ));
		serveurs.add(new ServeurTCP(this, new ProtocoleAfficherHistorique(), hport));
		for(ServeurTCP s : serveurs ) {
				s.start();
		}
	}

	public String toString() {
		return "La Banque possede un compte avec la somme de " + getLeCompte().getSomme();
	}

	public synchronized int demandeRetrait(int unRetrait) {
		return strategyretrait.demandeRetrait(unRetrait, this);
	}

	public synchronized int demandeDepot(int unDepot) {
		return strategydepot.demandeDepot(unDepot, this);
	}

	
	public String getTypeOperation() {
		return typeOperation;
	}

	public CompteBancaire getLeCompte() {
		return leCompte;
	}

	public void setTypeOperation(String s) {
		typeOperation = s;
	}

	public void faireOperation(String string) {
		this.historiqueOperations.add(string);
		this.setTypeOperation(string);
		this.setChanged();
		this.notifyObservers();
	}

	public void setStrategyretrait(String typeRetrait) {
		if (typeRetrait.equals(StrategieUI.retraitSimple))
			strategyretrait = new DemandeRetraitSimple();
		else if (typeRetrait.equals(StrategieUI.retraitPlafond)) 
			strategyretrait = new DemandeRetraitPlafond(100);
	}
	
	public void setStrategydepot( String typeDepot ) {
		if (typeDepot.equals(StrategieUI.depotSimple))
			strategydepot = new DemandeDepotSimple();
		else if (typeDepot.equals(StrategieUI.depotTemporise)) 
			strategydepot = new DemandeDepotTemporise();
	}
	
	public ArrayList<String> getHistoriqueOperations() {
		return historiqueOperations;
	}
	
}
