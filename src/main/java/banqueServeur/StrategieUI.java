package banqueServeur;
import java.util.*;

public class StrategieUI implements Observer{

	public static final String retraitSimple = "RetraitSimple";
	public static final String retraitPlafond = "RetraitPlafond";
	public static final String depotSimple = "DepotSimple";
	public static final String depotTemporise = "DepotTemporise";
	
	BanqueGUI myGui;
	Banque laBanque;
	
	
	public StrategieUI( Banque b , BanqueGUI bg ){
		myGui = bg;
		b.addObserver( this );
		laBanque = b;
	}
	
	public void setStrategieRetrait( String s ) {
		laBanque.setStrategyretrait( s );
	}
	
	public void quitter() {
		System.exit(0);
		
	}
	public void setStrategieDepot( String s ) {
		laBanque.setStrategydepot( s );
	}

	
	public void update(Observable o, Object arg) {
		
		myGui.getOperationCourante().setText(String.valueOf(((Banque) o).getTypeOperation()));
		
	}
}
