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

public class RegistrierenView extends JFrame implements ActionListener {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3234824339827138321L;
	// JTextField
	protected JTextField name2 = new JTextField();
	protected JTextField mail = new JTextField();

	// JPasswordField
	protected JPasswordField password = new JPasswordField(16);
	protected JPasswordField passwordConfirm = new JPasswordField(16);

	// JButton
	protected JButton registrierenButton = new JButton("Registrieren");
	protected JButton cancelButton = new JButton("Back");

	// JLabel
	protected JLabel titelLabel = new JLabel("Registration");
	protected JLabel usernameLabel = new JLabel("Username");
	protected JLabel emailLabel = new JLabel("E-mail");
	protected JLabel passwordLabel = new JLabel("Passwort");
	protected JLabel passwordConfirmLabel = new JLabel(
			"Passwort best\u00E4tigen");
	
	//JFrame
	private JFrame frame = new JFrame();
	private JFrame login = new JFrame();



	public RegistrierenView(JFrame login) {
		this.setLogin(login);
		this.getFrame().setTitle("Registration Pong");
		this.getFrame().setBounds(500, 400, 419, 238);
		this.getFrame().getContentPane().setLayout(null);
		this.getFrame().setVisible(true);
		this.getFrame().setResizable(false);

		titelLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		titelLabel.setBounds(48, 11, 141, 23);
		this.getFrame().getContentPane().add(titelLabel);

		usernameLabel.setBounds(48, 45, 102, 24);
		this.getFrame().getContentPane().add(usernameLabel);

		name2.setColumns(10);
		name2.setBounds(181, 47, 176, 20);
		this.getFrame().add(name2);
		

		emailLabel.setBounds(48, 70, 102, 24);
		this.getFrame().add(emailLabel);

		mail.setBounds(181, 72, 176, 20);
		this.getFrame().add(mail);

		passwordLabel.setBounds(48, 96, 102, 24);
		this.getFrame().add(passwordLabel);

		password.setBounds(181, 98, 176, 20);
		this.getFrame().add(password);

		passwordConfirmLabel.setBounds(48, 123, 123, 24);
		this.getFrame().add(passwordConfirmLabel);

		passwordConfirm.setBounds(181, 125, 176, 20);
		this.getFrame().add(passwordConfirm);

		registrierenButton.setBounds(251, 154, 106, 23);
		registrierenButton.addActionListener(this);
		registrierenButton.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					User u = new User();
					String username = name2.getText();
					String email = mail.getText();
					@SuppressWarnings("deprecation")
					String pw = password.getText();
					@SuppressWarnings("deprecation")
					String pwConf = passwordConfirm.getText();
					u.setID_user(username);
					u.setMail(email);
					u.setPassword(pw);
					u.setPasswordConf(pwConf);
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		this.getFrame().add(registrierenButton);
		
		cancelButton.setBounds(181, 154, 65, 23);
		cancelButton.addActionListener(this);
		cancelButton.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					getFrame().dispose();
					getLogin().setVisible(true);
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		this.getFrame().add(cancelButton);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(registrierenButton)) {
			boolean success = false;
			User u = new User();
			String username = this.name2.getText();
			String email = this.mail.getText();
			@SuppressWarnings("deprecation")
			String pw = this.password.getText();
			@SuppressWarnings("deprecation")
			String pwConf = this.passwordConfirm.getText();
			u.setID_user(username);
			u.setMail(email);
			u.setPassword(pw);
			u.setPasswordConf(pwConf);
			success = Client.getInstance().register(u);
			
			if (success){
			getFrame().dispose();
			this.getLogin().setVisible(true);
			}
			
		}
		if (e.getSource().equals(cancelButton)) {
			this.getFrame().dispose();
			this.getLogin().setVisible(true);
		} 
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public JFrame getLogin() {
		return login;
	}
	public void setLogin(JFrame login) {
		this.login = login;
	}

	public void setName(JTextField name) {
		this.name2 = name;
	}
	public JTextField getMail() {
		return mail;
	}
	public void setMail(JTextField mail) {
		this.mail = mail;
	}
	public JPasswordField getPassword() {
		return password;
	}
	public void setPassword(JPasswordField password) {
		this.password = password;
	}
	public JPasswordField getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(JPasswordField passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

}