package ch.berufsbildungscenter.projekt;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import ch.berufsbildungscenter.projekt.TAdapter;
import ch.berufsbildungscenter.projekt.Brett;
import ch.berufsbildungscenter.rmi.Client;


public class Erzeuger extends JFrame {
	private Brett brett;
	private static final long serialVersionUID = 2442180792899974541L;

	public Erzeuger(Brett b) {
		this.setBrett(b);
		add(this.getBrett());
		setTitle("Pong");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Gemeinsam.WIDTH, Gemeinsam.HEIGTH);
		setLocationRelativeTo(null);
		setIgnoreRepaint(true);
		setResizable(false);
		setVisible(true);
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

		

	}
	public static void main(String[] args){
		
		Erzeuger e = new Erzeuger(new Brett(new Player(0, 0), new Player(0, 1), "Gast"));
		e.getBrett().addKeyListener(new TAdapter(e.getBrett().getPlayer().get(0)));
		e.getBrett().addKeyListener(new TAdapter(e.getBrett().getPlayer().get(1)));
		
	}
	

	public Brett getBrett() {
		return brett;
	}

	public void setBrett(Brett brett) {
		this.brett = brett;
	}

	
}