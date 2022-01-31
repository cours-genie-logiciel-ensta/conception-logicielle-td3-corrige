package banqueServeur;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;


public class ProtocoleRetraitDepot implements IProtocole {

	public void execute( IContext c , InputStream unInput , OutputStream unOutput ) {
		
		Banque b = (Banque)c;
		String inputReq;
		BufferedReader is = new BufferedReader(new InputStreamReader(
				unInput));
		PrintStream os = new PrintStream(unOutput);
		try {
			String valeurExpediee = "";
			if ((inputReq = is.readLine()) != null) {
				System.out.println(" Ordre Recu " + inputReq);
				String chaines[] = inputReq.split(" ");
				if (chaines[0].contentEquals("retrait")) {
					int valeur = (new Integer(chaines[1])).intValue();
					int valeurRetrait = b.demandeRetrait(valeur);
					valeurExpediee = String.valueOf(valeurRetrait);
					System.out.println(" Retrait dans serveur "	+ valeurExpediee);
				}
				if (chaines[0].contentEquals("depot")) {
					int valeur = (new Integer(chaines[1])).intValue();
					int valeurDepot = b.demandeDepot(valeur);
					valeurExpediee = String.valueOf(valeurDepot);
					System.out.println(" Depot dans serveur " + valeurExpediee);
				}
				os.println(valeurExpediee);
			}
		} catch ( Exception e) {
			System.out.println(" Pb d'exception ");
		}			
	}
}
