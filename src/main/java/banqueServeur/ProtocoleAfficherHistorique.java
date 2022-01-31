package banqueServeur;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;


public class ProtocoleAfficherHistorique implements IProtocole {	

	public void execute(IContext c, InputStream unInput,
			OutputStream unOutput) {
		Banque b = (Banque)c;
		String inputReq;
		BufferedReader is = new BufferedReader(new InputStreamReader(
				unInput));
		PrintStream os = new PrintStream(unOutput);
		try {
			if ((inputReq = is.readLine()) != null) {
				System.out.println(" AfficherHistorique Msg Recu " + inputReq);
				String chaines[] = inputReq.split(" ");
				System.out.println(" AfficherHistorique Ordre Recu " + chaines[0]);
				if (chaines[0].contentEquals(automateClient.Historique.requeteHisto)) {
					String outputString = "Liste des operations :\n";
					for (String h : b.getHistoriqueOperations()){
						outputString = outputString + h + "\n" ;
					}
					System.out.println(" Liste a envoyer : \n " + outputString );
					os.println(outputString);
					os.flush();

				} else {
					os.println("Erreur : Vous ne pouvez acceder au serveur de l'historique de la banque \n" );						
				}
			}
		} catch (Exception e ){ 
			e.printStackTrace();
		}
	}


}
