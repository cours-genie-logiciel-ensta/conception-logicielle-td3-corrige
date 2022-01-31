package automateClient;

public class MainClient {

	public static void main(String[] args) {
        Automate monCompte = new Automate (0);
        Historique monHistorique = new Historique();
		ClientGUI monClientGUI = new ClientGUI(monCompte);
		HistoriqueGUI monHistoriqueGUI = new HistoriqueGUI(monHistorique);
		monClientGUI.initGUI();
		monHistoriqueGUI.initGUI();
	}

}
