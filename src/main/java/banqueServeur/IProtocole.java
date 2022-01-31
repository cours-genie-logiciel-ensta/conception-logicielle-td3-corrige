package banqueServeur;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Une API qui représente un protocole
 */
public interface IProtocole {

	public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream);

}
