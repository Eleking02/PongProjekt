package ch.berufsbildungscenter.rmi;

import java.awt.BorderLayout;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.berufsbildungscenter.db.PongController;
import ch.berufsbildungscenter.projekt.TAdapter;
import ch.berufsbildungscenter.projekt.Brett;
import ch.berufsbildungscenter.projekt.Muenze;
import ch.berufsbildungscenter.projekt.User;

public class ValidatorKlasse extends UnicastRemoteObject implements Validator {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4765828222915569917L;
	private List<Brett> bretter = new ArrayList<Brett>();
	private List<Muenze> coins = new ArrayList<Muenze>();
	private TAdapter tastenAd;
	private JPanel consolepanel;
	private JFrame consoleframe;
	private JLabel consolelabel;
	private List<Client> clients = new ArrayList<Client>();
	private Client test;
	private Map<Client, Client> einladen = new HashMap<Client, Client>();
	private Map<Client, Client> bestätigungen = new HashMap<Client, Client>();

	public ValidatorKlasse() throws RemoteException {
		this.setConsoleframe(new JFrame());
		this.getConsoleframe().setTitle("Server");
		this.getConsoleframe().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getConsoleframe().setSize(700, 800);
		this.setConsolelabel(new JLabel("<html>"));
		this.setConsolepanel(new JPanel());
		this.getConsolelabel().setHorizontalTextPosition(SwingConstants.LEFT);
		this.getConsolepanel().add(this.getConsolelabel());
		this.getConsoleframe().add(this.getConsolepanel(), BorderLayout.WEST);
		this.getConsoleframe().setVisible(true);



	}
	
	@Override
	public void deleteGame(String besitzer) throws RemoteException {
		// TODO
		
	}


	
	
	@Override
	public String regTry(User u) throws RemoteException {
		String result;
		result = PongController.getInstance().regTry(u, this.getConsolelabel());
		return result;
		
	}

	@Override
	public void tellBrettInformation(int p1Paddle, int p2Paddle,
			String ersteller) {
		// TODO
	}
	
	@Override
	public User getData(User u) throws RemoteException {
		u = PongController.getInstance().findUser(u);
		return u;
	}

	@Override
	public synchronized List<User> getAllUser() throws RemoteException {
		try {
			return PongController.getUserDao().findAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<User> GetOnlineUser() throws RemoteException {
		// TODO

		return null;
	}

	@Override
	public void tellPosition(int Xcor, int Ycor, int spielerNr, String ersteller)
			throws RemoteException {
		// TODO
	}

	@Override
	public int synScore(int spielerNr, String ersteller) throws RemoteException {
		// TODO
		return 0;
	}

	@Override
	public int getOpponentsYcor(int ownPlayerNr, String ersteller)
			throws RemoteException {

		// TODO
		return 0;

	}

	@Override
	public int syncBall(char cor, String ersteller) throws RemoteException {

		// TODO
		return 0;
	}

	@Override
	public int syncCoin(char cor, String ersteller) throws RemoteException {
		// TODO
		return 0;
	}

	@Override
	public int syncCoinWert(int wert, String ersteller) throws RemoteException {
		// TODO
		
		return 0;
	}

	@Override
	public void logout(Client c) throws RemoteException {
		this.getConsolelabel().setText(
				this.getConsolelabel().getText() + c.getUser().getID_user()
						+ " hat den Server verlassen<br/>");
		
		// TODO

	}

	@Override
	public synchronized String login(User u, Client c) throws RemoteException {
		String result;
		result = PongController.getInstance().login(u);
		c.setUser(u);
		u.setClient(c);
		
		if (result.equals("OK")) {

				this.getConsolelabel()
						.setText(
								(this.getConsolelabel().getText()+ c.getUser().getID_user() + " hat den Server betreten<br/>"));
				return result;
			
			
		} else {
			return result;
		}
	}

	@Override
	public void einladen(Client c, Client c2) throws RemoteException {
		// TODO
	}

	@Override
	public Client checkEinladen(Client c) throws RemoteException {
		// TODO
		
		return null;
	}

	@Override
	public void annehmen(Client ich, Client gegner) throws RemoteException {
		// TODO

	}

	@Override
	public Client checkBestätigungen(Client ich) throws RemoteException {
		// TODO
		return null;
	}
	
	@Override
	public void syncPause(String ersteller) throws RemoteException {
		// TODO	
	}
	
	@Override
	public int synCoinStats(int spielerNr, String ersteller)
			throws RemoteException {
		// TODO
		return 0;
	}
	
	@Override
	public User refreshPaddle(User u) throws RemoteException {
		u=PongController.getInstance().refreshPaddle(u);
		return u;
	}

	
	@Override
	public User updateUser(User u) throws RemoteException {
		u = PongController.getInstance().updateUser(u);
		return u;
	}
	
	@Override
	public User updateUserPaddle(User u) throws RemoteException {
		u = PongController.getInstance().updateUserPaddle(u);
		return u;
	}

	public TAdapter getTastenAd() {
		return tastenAd;
	}

	public void setTastenAd(TAdapter tastenAd) {
		this.tastenAd = tastenAd;
	}

	public JPanel getConsolepanel() {
		return consolepanel;
	}

	public void setConsolepanel(JPanel consolepanel) {
		this.consolepanel = consolepanel;
	}

	public JFrame getConsoleframe() {
		return consoleframe;
	}

	public void setConsoleframe(JFrame consoleframe) {
		this.consoleframe = consoleframe;
	}

	public JLabel getConsolelabel() {
		return consolelabel;
	}

	public void setConsolelabel(JLabel consolelabel) {
		this.consolelabel = consolelabel;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Client getTest() {
		return test;
	}

	public void setTest(Client test) {
		this.test = test;
	}

	public Map<Client, Client> getEinladen() {
		return einladen;
	}

	public void setEinladen(Map<Client, Client> einladen) {
		this.einladen = einladen;
	}

	public List<Brett> getBretter() {
		return bretter;
	}

	public void setBretter(List<Brett> bretter) {
		this.bretter = bretter;
	}

	public Map<Client, Client> getBestätigungen() {
		return bestätigungen;
	}

	public void setBestätigungen(Map<Client, Client> bestätigungen) {
		this.bestätigungen = bestätigungen;
	}

	public List<Muenze> getCoins() {
		return coins;
	}

	public void setCoins(List<Muenze> coins) {
		this.coins = coins;
	}

	@Override
	public void test(String msg) throws RemoteException {
		System.out.println(msg);		
	}







}
