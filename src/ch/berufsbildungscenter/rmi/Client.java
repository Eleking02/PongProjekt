package ch.berufsbildungscenter.rmi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ch.berufsbildungscenter.gui.LoggedInWindow;
import ch.berufsbildungscenter.gui.LoginView;
import ch.berufsbildungscenter.projekt.TAdapter;
import ch.berufsbildungscenter.projekt.Brett;
import ch.berufsbildungscenter.projekt.Erzeuger;
import ch.berufsbildungscenter.projekt.User;

public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 182521716974491323L;
	private static Client client;
	private String ip;
	private String port;
	private Validator connect;
	private User user;
	private JFrame frame;
	private Erzeuger spiel;
	private Client derGegner;
	private boolean ingame = false;

	private Client() {

		String filename = "Serverdata.csv"; // File mit allen Informationen
		File file = new File(filename);
		String[] server = new String[2];

		try {
			Scanner inputStream = new Scanner(file);
			int i = 0;
			while (inputStream.hasNext()) {
				String data = inputStream.next();
				server[i] = data;
				i++;
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("File nicht gefunden");
		}

		try {
			this.setIp(server[0]);
			this.setPort(server[1]);
			Remote remote = Naming.lookup("rmi://" + server[0] + ":"
					+ server[1] + "/validator");
			Validator validator = (Validator) remote;
			this.setConnect(validator);

		} catch (MalformedURLException me) {
			System.err.println("rmi://" + ip + ":" + port
					+ "/validator is not a valid URL");
		} catch (NotBoundException nbe) {
			System.err.println("Could not find requested object on the server");
		} catch (RemoteException re) {
			System.err.println(re.getMessage());
		}

	}

	public void login(User u) {
		String result = "Error";
		try {
			result = Client.getInstance().connect()
					.login(u, Client.getInstance());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if (result.equals("UsEmpty")) {

			JOptionPane.showMessageDialog(null,
					"Username muss ausgefüllt sein!", "Warnung!",
					JOptionPane.ERROR_MESSAGE);

		} else if (result.equals("PwEmpty")) {

			JOptionPane.showMessageDialog(null,
					"Passwort muss ausgefüllt sein!", "Warnung!",
					JOptionPane.ERROR_MESSAGE);

		} else if (result.equals("UserNotFount")) {

			JOptionPane.showMessageDialog(null, "User nicht gefunden", "Error",
					JOptionPane.ERROR_MESSAGE);

		} else if (result.equals("BereitsAngemeldet")) {

			JOptionPane.showMessageDialog(null, "User bereits angemeldet",
					"Error", JOptionPane.ERROR_MESSAGE);

		} else if (result.equals("OK")) {
			try {

				u = Client.getInstance().connect().getData(u);
				Client.getInstance().setUser(u);
				Client.getInstance().getUser().setClient(Client.getInstance());
				this.getFrame().dispose();
				LoggedInWindow menu = new LoggedInWindow(u);


			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}

	}

	public boolean register(User u) {
		String result = "Error";
		try {
			result = Client.getInstance().connect().regTry(u);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (result.equals("PwEmpty")) {
			JOptionPane.showMessageDialog(null,
					"Passwort muss ausgefüllt sein!", "Warnung!",
					JOptionPane.ERROR_MESSAGE);

			return false;
		} else if (result.equals("UsEmpty")) {
			JOptionPane.showMessageDialog(null,
					"Username muss ausgefüllt sein!", "Warnung!",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (result.equals("EmEmpty")) {
			JOptionPane.showMessageDialog(null, "Email muss ausgfüllt sein!",
					"Warnung!", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (result.equals("FalseMail")) {
			JOptionPane.showMessageDialog(null, "Email ist ung\u00fcltig!",
					"Warnung!", JOptionPane.ERROR_MESSAGE);

			return false;
		} else if (result.equals("UsExists")) {
			JOptionPane.showMessageDialog(null, "Username bereits vergeben!",
					"Warnung!", JOptionPane.ERROR_MESSAGE);

			return false;
		} else if (result.equals("PwFalse")) {
			JOptionPane.showMessageDialog(null,
					"Passwörter stimmen nicht überein", "Warnung!",
					JOptionPane.ERROR_MESSAGE);

			return false;

		} else if (result.equals("falseUsLength")) {
			JOptionPane.showMessageDialog(null,
					"Username muss 4 bis 12 Zeichen beinhalten", "Warnung!",
					JOptionPane.ERROR_MESSAGE);

			return false;

		} else if (result.equals("falsePwLength")) {
			JOptionPane.showMessageDialog(null,
					"Passwort muss 4 bis 16 Zeichen veinhalten", "Warnung!",
					JOptionPane.ERROR_MESSAGE);

			return false;

		} else if (result.equals("falseEmLength")) {
			JOptionPane.showMessageDialog(null,
					"Email darf Maximal 60 Zeichen haben", "Warnung!",
					JOptionPane.ERROR_MESSAGE);

			return false;

		} else if (result.equals("OK")) {
			JOptionPane.showMessageDialog(null,
					"Sie wurden erfolgreich eingetragen!", "Bestätigung",
					JOptionPane.INFORMATION_MESSAGE);

			return true;
		}

		return false;
	}

	
	public static void main(String[] args) {
		Client.getInstance().start();
	}

	public void start() {
		this.setFrame(new LoginView());
	}
	
	public static synchronized Client getInstance() {
		if (client == null) {
			client = new Client();
		}
		return client;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Validator connect() {
		return connect;
	}

	public void setConnect(Validator connect) {
		this.connect = connect;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User u) {
		this.user = u;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Erzeuger getSpiel() {
		return spiel;
	}

	public void setSpiel(Erzeuger spiel) {
		this.spiel = spiel;
	}

	public Client getDerGegner() {
		return derGegner;
	}

	public void setDerGegner(Client derGegner) {
		this.derGegner = derGegner;
	}

	public boolean isIngame() {
		return ingame;
	}

	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}

}
