package banqueServeur;

public class MainServeur {

	public static void main(String[] args) {
		Banque maBanque = new Banque (10000,new DemandeRetraitSimple(), new DemandeDepotSimple());
		/* BanqueGUI remplace MontantBanqueGUI des TPs precedents*/
		BanqueGUI banqueGUI = new BanqueGUI(maBanque);
		banqueGUI.initGui();
		System.out.println("== TD4 ==\n"+maBanque);
		maBanque.ouvrirBanque();

	}
}
