package banqueServeur;

/**
 * Implantation de la stratégie de retrait plafonnée
 *
 */
public class DemandeRetraitPlafond implements IDemandeRetrait {

	private int montantmax;

	public DemandeRetraitPlafond(int plafond) {
		montantmax = plafond;
	}

	@Override
	public int demandeRetrait(int unRetrait, IBanque b) {
		int valeurRetiree = 0;
		if (b.getLeCompte().getSomme() - unRetrait > 0) {
			if (unRetrait > montantmax)
				valeurRetiree = montantmax;
			else
				valeurRetiree = unRetrait;
		} else {
			if (b.getLeCompte().getSomme() > 0)
				valeurRetiree = b.getLeCompte().getSomme();

		}
		b.getLeCompte().setSomme(b.getLeCompte().getSomme() - valeurRetiree);
		b.faireOperation("Retrait plafond " + valeurRetiree);
		return valeurRetiree;

	}
}