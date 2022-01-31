package banqueServeur;

public class DemandeDepotTemporise implements IDemandeDepot{
	
	public int demandeDepot(int unDepot, IBanque b) {
        int valeurDeposee = unDepot;
        try { // attendre 3 secondes
        	Thread.sleep(3000);
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
        b.getLeCompte().setSomme(b.getLeCompte().getSomme() + unDepot);
        b.faireOperation("Depot temporise " + unDepot);
        return valeurDeposee;
	}
}