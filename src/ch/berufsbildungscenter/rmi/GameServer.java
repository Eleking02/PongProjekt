package ch.berufsbildungscenter.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GameServer {

	public static void main(String[] args) {
	    try {
	   
	      Registry reg = LocateRegistry.createRegistry(1246);
	      Validator aValidator = new ValidatorKlasse();
	      
	      reg.rebind("validator", aValidator);
	      
	    } catch (RemoteException e) {
	    	e.printStackTrace();
	    }

}
}
