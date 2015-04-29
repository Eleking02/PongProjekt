package ch.berufsbildungscenter.projekt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ch.berufsbildungscenter.projekt.Player;

public class TAdapter extends KeyAdapter {
	private Player spieler;
	
	public TAdapter(Player spieler){
		this.setSpieler(spieler);
	}
	
	public void keyPressed(KeyEvent e) {
		this.getSpieler().keyPressed(e);
		
	}

	public void keyReleased(KeyEvent e) {
		this.getSpieler().keyReleased(e);
	}

	public Player getSpieler() {
		return spieler;
	}

	public void setSpieler(Player spieler) {
		this.spieler = spieler;
	}
}