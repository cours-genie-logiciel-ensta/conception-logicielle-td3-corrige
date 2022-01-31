package banqueServeur;

public class DemandeDepotSimple implements IDemandeDepot{

	
	public int demandeDepot(int unDepot, IBanque b) {
        int valeurDeposee = unDepot;
        b.getLeCompte().setSomme(b.getLeCompte().getSomme() + unDepot);
		b.faireOperation("Depot simple " + unDepot);
        return valeurDeposee;
	}

}