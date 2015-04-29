package ch.berufsbildungscenter.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ch.berufsbildungscenter.rmi.Client;
import ch.berufsbildungscenter.projekt.User;

public class LoginView extends JFrame implements ActionListener {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 9117533360052163884L;

	// JTextField
	protected JTextField username = new JTextField();

	// JPasswordField
	protected JPasswordField password = new JPasswordField();

	// JButton
	protected JButton registrierenButton = new JButton("Registrieren");
	protected JButton loginbButton = new JButton("Login");

	// JLabel
	protected JLabel usernameLabel = new JLabel("Username");
	protected JLabel passwordLabel = new JLabel("Passwort");
	protected JLabel titelLabel = new JLabel("Pong Login");
	public static JFrame frame = new JFrame();
	private JFrame me;



	public LoginView() {
		this.setMe(this);
		this.setTitle("Pong");
		this.setBounds(500, 400, 419, 238);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		this.setResizable(false);

		titelLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		titelLabel.setBounds(48, 11, 141, 23);
		this.add(titelLabel);

		usernameLabel.setBounds(48, 45, 102, 24);
		this.add(usernameLabel);

		username.setBounds(160, 47, 197, 20);
		this.add(username);
		username.setColumns(10);

		passwordLabel.setBounds(48, 76, 102, 24);
		this.add(passwordLabel);

		password.setBounds(160, 78, 197, 20);
		this.add(password);

		registrierenButton.setBounds(160, 120, 106, 23);
		registrierenButton.addActionListener(this);
		registrierenButton.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					setVisible(false);
					new RegistrierenView(getMe());
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		this.add(registrierenButton);

		loginbButton.setBounds(273, 120, 84, 23);
		loginbButton.addActionListener(this);
		loginbButton.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					String user = getUsername().getText();
					@SuppressWarnings("deprecation")
					String passwort = getPassword().getText();
					User u = new User();
					u.setID_user(user);
					u.setPassword(passwort);
					Client.getInstance().login(u);
					
					
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		this.add(loginbButton);

		this.setVisible(true);
	}

	public static void close(){
		 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(this.getLoginbButton())) {

			String user = this.getUsername().getText();
			@SuppressWarnings("deprecation")
			String passwort = this.getPassword().getText();
			User u = new User();
			u.setID_user(user);
			u.setPassword(passwort);
			Client.getInstance().login(u);

		}
		if (e.getSource().equals(this.getRegistrierenButton())) {
			
			this.setVisible(false);
			new RegistrierenView(this);
			
		}
	}

	public JTextField getUsername() {
		return username;
	}

	public void setUsername(JTextField username) {
		this.username = username;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public JButton getRegistrierenButton() {
		return registrierenButton;
	}

	public void setRegistrierenButton(JButton registrierenButton) {
		this.registrierenButton = registrierenButton;
	}

	public JButton getLoginbButton() {
		return loginbButton;
	}

	public void setLoginbButton(JButton loginbButton) {
		this.loginbButton = loginbButton;
	}

	public JLabel getUsernameLabel() {
		return usernameLabel;
	}

	public void setUsernameLabel(JLabel usernameLabel) {
		this.usernameLabel = usernameLabel;
	}

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public JLabel getTitelLabel() {
		return titelLabel;
	}

	public void setTitelLabel(JLabel titelLabel) {
		this.titelLabel = titelLabel;
	}

	public JFrame getMe() {
		return me;
	}

	public void setMe(JFrame me) {
		this.me = me;
	}



}
