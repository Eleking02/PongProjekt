package ch.berufsbildungscenter.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ch.berufsbildungscenter.projekt.User;

public interface Validator extends Remote {

	public abstract void tellPosition(int Xcor, int Ycor, int spielerNr,
			String ersteller) throws RemoteException;

	public abstract int getOpponentsYcor(int ownPlayerNr, String ersteller)
			throws RemoteException;

	public abstract int syncBall(char Cor, String ersteller)
			throws RemoteException;
	
	public abstract int syncCoin(char Cor, String ersteller) throws RemoteException;
	
	public abstract int syncCoinWert(int wert, String ersteller) throws RemoteException;
	
	public abstract int synScore( int spielerNr,
			String ersteller) throws RemoteException;
	
	public abstract int synCoinStats( int spielerNr,
			String ersteller) throws RemoteException;
	
	public abstract void tellBrettInformation(int p1Paddle, int p2Paddle,
			String ersteller) throws RemoteException;

	public abstract String login(User u, Client c) throws RemoteException;

	public abstract String regTry(User u) throws RemoteException;

	public abstract List<User> getAllUser() throws RemoteException;

	public abstract List<User> GetOnlineUser() throws RemoteException;

	public abstract void logout(Client c) throws RemoteException;

	public abstract void einladen(Client c, Client c2) throws RemoteException;

	public abstract Client checkEinladen(Client c2) throws RemoteException;
	
	public abstract User refreshPaddle(User u) throws RemoteException;

	public abstract void annehmen(Client ich, Client gegner)
			throws RemoteException;

	public abstract Client checkBestätigungen(Client ich)
			throws RemoteException;
	
	public abstract User getData(User u)
			throws RemoteException;
	
	public abstract void syncPause(String ersteller) throws RemoteException;
	
	public abstract User updateUser(User u) throws RemoteException;
	
	public abstract User updateUserPaddle(User u) throws RemoteException;
	
	public abstract void deleteGame(String besitzer) throws RemoteException;


	public abstract void test(String msg) throws RemoteException;

}
