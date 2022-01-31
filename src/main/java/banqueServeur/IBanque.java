package banqueServeur;

public interface IBanque extends IContext {

	public int demandeRetrait(int unRetrait);

	public int demandeDepot(int unDepot);
    
    public CompteBancaire getLeCompte();

	public void setTypeOperation(String string);

	public void faireOperation(String string); 
     
}