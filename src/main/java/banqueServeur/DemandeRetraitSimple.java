package banqueServeur;

/**
 * Implantation de la stratégie de retrait simple
 *
 */
public class DemandeRetraitSimple implements IDemandeRetrait {

	@Override
	public int demandeRetrait(int unRetrait, IBanque b) {
		int valeurRetiree;
		int reste = b.getLeCompte().getSomme() - unRetrait;
		if (reste > 0) {
			b.getLeCompte().setSomme(reste);
			valeurRetiree = unRetrait;
		} else {
			valeurRetiree = b.getLeCompte().getSomme();
			b.getLeCompte().setSomme(0);
		}
		b.faireOperation("Retrait " + valeurRetiree);
		return valeurRetiree;

	}

}