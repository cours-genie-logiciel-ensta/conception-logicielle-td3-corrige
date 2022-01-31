package banqueServeur;
import java.util.Observable;
import java.util.Observer;

public class SommeUI implements Observer {

	BanqueGUI myGui;
	
	
	public SommeUI( Banque b , BanqueGUI bg ){
		myGui = bg;
		b.addObserver( this );
		
	}
	
	
	public void update(Observable o, Object arg) {
		
		myGui.getTexteSommeBanque().setText(String.valueOf(((Banque) o).getLeCompte().getSomme()));
		System.out.println("Somme mise a jour : " + arg + " - - - - " + o);
		//myGui.getTexteSommeDemandee().setText(arg.toString());
		
	}
}
