package banqueServeur;

/**
 * Une API simple, qui représente la stratégie (générique) pour un retrait
 *
 */
public interface IDemandeRetrait {

	public int demandeRetrait(int unRetrait, IBanque b);
}