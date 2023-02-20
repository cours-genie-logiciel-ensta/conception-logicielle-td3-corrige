package automateClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Représente un client TCP : cette classe s'appuie principalement sur un objet
 * {@link Socket}, et s'initialise par un nom de serveur et un numéro de port
 */
public class ClientTCP {

	private int numeroPort;

	private String nomServeur;

	private Socket socketServeur;

	private PrintStream socOut;

	private BufferedReader socIn;

	/**
	 * Création d'un nouveau {@link ClientTCP} avec un nom de serveur et un numéro
	 * de port
	 * 
	 * @param unNomServeur
	 * @param unNumero
	 */
	public ClientTCP(String unNomServeur, int unNumero) {
		numeroPort = unNumero;
		nomServeur = unNomServeur;
	}

	public boolean connecterAuServeur() {
		boolean ok = false;
		try {
			System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
// le numero IP conrrespond au numero 
			InetAddress adr = InetAddress.getByName("172.18.129.2");

			socketServeur = new Socket(adr, numeroPort);
			socOut = new PrintStream(socketServeur.getOutputStream());
			socIn = new BufferedReader(new InputStreamReader(socketServeur.getInputStream()));
			ok = true;
		} catch (UnknownHostException e) {
			System.err.println("Serveur inconnu : " + e);

		} catch (ConnectException e) {
			System.err.println("Exception lors de la connexion:" + e);
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("Exception lors de l'echange de donnees:" + e);
		}
		System.out.println("Connexion faite");
		return ok;
	}

	public void deconnecterDuServeur() {
		try {
			System.out.println("[ClientTCP] CLIENT : " + socketServeur);
			socOut.close();
			socIn.close();
			socketServeur.close();
		} catch (Exception e) {
			System.err.println("Exception lors de la deconnexion :  " + e);
		}
	}

	public String transmettreChaine(String uneChaine) {
		String msgServeur = null;
		try {
			System.out.println("Requete client : " + uneChaine);
			socOut.println(uneChaine);
			socOut.flush();
			msgServeur = socIn.readLine();
			System.out.println("Reponse serveur : " + msgServeur);

		} catch (UnknownHostException e) {
			System.err.println("Serveur inconnu : " + e);
		} catch (IOException e) {
			System.err.println("Exception entree/sortie:  " + e);
			e.printStackTrace();
		}
		return msgServeur;
	}

	/* A utiliser pour ne pas deleguer la connexion aux interfaces GUI */
	public String transmettreChaineConnexionPonctuelle(String uneChaine) {
		String msgServeur = null;
		String chaineRetour = "";
		System.out.println("\nClient connexionTransmettreChaine " + uneChaine);
		if (connecterAuServeur() == true) {
			try {
				socOut.println(uneChaine);
				socOut.flush();
				msgServeur = socIn.readLine();
				while (msgServeur != null && msgServeur.length() > 0) {
					chaineRetour += msgServeur + "\n";
					msgServeur = socIn.readLine();
				}
				System.out.println("Client msgServeur " + chaineRetour);
				deconnecterDuServeur();
			} catch (Exception e) {
				System.err.println("Exception lors de la connexion client:  " + e);
			}
		} else {
			System.err.println("Connexion echouee");
		}
		return chaineRetour;
	}

}
