package ch.berufsbildungscenter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ch.berufsbildungscenter.rmi.Client;
import ch.berufsbildungscenter.projekt.User;

public class LoggedInWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3892660882088306231L;
	private JPanel contentPane;
	Border border = BorderFactory.createLineBorder(Color.GREEN, 1);
	Font font = new Font("Arial", Font.PLAIN, 18);
	FontMetrics metr = this.getFontMetrics(font);
	JButton b = new JButton(new ImageIcon("/recources/images/button.png"));
	private JPanel panel_2 = new JPanel();
	private JButton btnPaddleWhlen = new JButton("Paddle w\u00E4hlen");
	private User users = new User();
	private String chal;
	private JButton ok = new JButton("OK");
	private JTextField textArea = new JTextField();
	private JButton btnAbmelden = new JButton("Abmelden");
	private Timer timer;
	private JButton btnShop = new JButton("Paddel auswählen");
	private User user = Client.getInstance().getUser();

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public LoggedInWindow(User u) {

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		this.getBtnPaddleWhlen().addActionListener(this);
		this.getOk().addActionListener(this);
		this.getBtnShop().addActionListener(this);
		this.getBtnAbmelden().addActionListener(this);
		timer = new Timer();

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 0, 364, 44);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblAngemeldetAls = new JLabel("Angemeldet als:");
		lblAngemeldetAls.setBounds(0, 18, 100, 33);
		lblAngemeldetAls.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblAngemeldetAls);

		JLabel lblNewLabel = new JLabel("test");
		lblNewLabel.setBounds(102, 18, 150, 33);
		lblNewLabel.setFont(font);
		panel_1.add(lblNewLabel);

		JLabel lblOnline = new JLabel("Online");
		lblOnline.setBounds(260, 11, 46, 14);
		panel_1.add(lblOnline);

		panel_1.add(b);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(LoggedInWindow.class
				.getResource("/images/button.png")));
		lblOnline.setBounds(270, 11, 46, 14);
		btnNewButton_1.setBorder((BorderFactory.createEmptyBorder(15, 15, 15,
				15)));
		btnNewButton_1.setBorderPainted(true);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusPainted(false);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					e.getWindow().dispose();
					Client.getInstance().connect().logout(Client.getInstance());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnNewButton_1.setBounds(275, 7, 89, 23);
		panel_1.add(btnNewButton_1);

		this.getPanel_2().setBorder(new LineBorder(new Color(0, 0, 0)));
		this.getPanel_2().setBounds(212, 79, 162, 201);
		this.getPanel_2().setLayout(null);
		contentPane.add(this.getPanel_2());

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 291, 364, 42);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		btnShop.setBounds(0, 11, 140, 23);
		panel_3.add(btnShop);

		

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 344, 364, 57);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblChallange = new JLabel("Challenge: ");
		lblChallange.setBounds(10, 11, 86, 14);
		panel_4.add(lblChallange);

		JButton btnNewButton = new JButton("Rangliste");
		btnNewButton.setBounds(251, 0, 113, 23);
		panel_4.add(btnNewButton);
		textArea.setBounds(106, 11, 100, 15);
		panel_4.add(textArea);

		ok.setBounds(106, 34, 80, 23);
		panel_4.add(ok);

		btnAbmelden.setBounds(251, 34, 113, 23);
		panel_4.add(btnAbmelden);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 79, 179, 201);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCoins = new JLabel("Coins:");
		lblCoins.setBounds(10, 11, 121, 14);
		panel.add(lblCoins);

		JLabel lblPunkte = new JLabel("Punkte:");
		lblPunkte.setBounds(10, 36, 121, 14);
		panel.add(lblPunkte);

		JLabel lblGewonneneSpiele = new JLabel("Gewonnene Spiele:");
		lblGewonneneSpiele.setBounds(10, 63, 121, 14);
		panel.add(lblGewonneneSpiele);

		JLabel lblGespielteSpiele = new JLabel("Gespielte Spiele:");
		lblGespielteSpiele.setBounds(10, 88, 121, 14);
		panel.add(lblGespielteSpiele);

		JLabel lblWinlose = new JLabel("Win/Lose:");
		lblWinlose.setBounds(10, 113, 121, 14);
		panel.add(lblWinlose);

		JButton btnSpielstatistik = new JButton("Spielstatistik");
		btnSpielstatistik.setBounds(10, 167, 121, 23);
		panel.add(btnSpielstatistik);

		JLabel lblPaddle = new JLabel("Paddle:");
		lblPaddle.setBounds(10, 138, 121, 14);
		panel.add(lblPaddle);

		JLabel lblNewLabel_1 = new JLabel("21");
		lblNewLabel_1.setBounds(133, 11, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel label = new JLabel("1072");
		label.setBounds(133, 36, 46, 14);
		panel.add(label);

		JLabel label_1 = new JLabel("3");
		label_1.setBounds(133, 61, 46, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel("6");
		label_2.setBounds(133, 88, 46, 14);
		panel.add(label_2);

		JLabel label_3 = new JLabel("1");
		label_3.setBounds(133, 113, 46, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel("0");
		label_4.setBounds(133, 142, 46, 14);
		panel.add(label_4);

		JLabel lblStatistik = new JLabel("Statistik");
		lblStatistik.setBounds(10, 55, 73, 14);
		contentPane.add(lblStatistik);

		JLabel lblOnlinePlayers = new JLabel("Online Players");
		lblOnlinePlayers.setBounds(212, 55, 100, 14);
		contentPane.add(lblOnlinePlayers);

	}


	public void actionPerformed(ActionEvent e) {



		if (e.getSource().equals(getBtnAbmelden())) {

			try {
				this.dispose();
				Client.getInstance().connect().logout(Client.getInstance());
				System.exit(0);
			} catch (RemoteException e1) {
				e1.printStackTrace();

			}
		}
		
	}

	public JPanel getPanel_2() {
		return panel_2;
	}

	public void setPanel_2(JPanel panel_2) {
		this.panel_2 = panel_2;
	}

	public JButton getBtnPaddleWhlen() {
		return btnPaddleWhlen;
	}

	public void setBtnPaddleWhlen(JButton btnPaddleWhlen) {
		this.btnPaddleWhlen = btnPaddleWhlen;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public String getChal() {
		return chal;
	}

	public void setChal(String chal) {
		this.chal = chal;
	}

	public JTextField getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextField textArea) {
		this.textArea = textArea;
	}

	public JButton getOk() {
		return ok;
	}

	public void setOk(JButton ok) {
		this.ok = ok;
	}

	public JButton getBtnAbmelden() {
		return btnAbmelden;
	}

	public void setBtnAbmelden(JButton btnAbmelden) {
		this.btnAbmelden = btnAbmelden;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public JButton getBtnShop() {
		return btnShop;
	}

	public void setBtnShop(JButton btnShop) {
		this.btnShop = btnShop;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
}
