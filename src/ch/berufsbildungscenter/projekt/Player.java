package ch.berufsbildungscenter.projekt;

import java.awt.event.KeyEvent;

import ch.berufsbildungscenter.projekt.User;

import ch.berufsbildungscenter.projekt.User;

public class Player extends Form implements Gemeinsam {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8656137103384179789L;
	// erstellen der Variable dx
	private int dy;
	private int spielerNr;
	private static boolean pressPauseP1 = false;
	private static boolean pressPauseP2 = false;
	private User besitzer;
	private User beitreter;
	int zähler1 = 0;
	int zähler2 = 0;

	// Konstruktor für Player
	public Player(int paddleID, int spielerNr) {
		// Konstruktor der Super Klass
		super(paddleID, "/player/" + paddleID + ".png");
		this.setSpielerNr(spielerNr);
		resetState();
	}

	// Methode move
	public void move() {
		this.setY(this.getY() + this.getDy());
		if (this.getY() <= Gemeinsam.TOP) {
			this.setY(Gemeinsam.TOP + 1);
		}
		if (this.getY() >= Gemeinsam.BOTTOM) {
			this.setY(Gemeinsam.BOTTOM - 1);
		}
	}

	public void stop() {

	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		

		if (key == KeyEvent.VK_P && Brett.getPauseP1() <= 3) {
			Brett.setPausenKnopf(true);
			zähler1++;
			Brett.setPauseP1(zähler1+1);
			Player.setPressPauseP1(true);

		}
		if (key == KeyEvent.VK_P && Brett.getPauseP2() <= 3) {
			Brett.setPausenKnopf(true);
			

			Brett.setPauseP2(zähler2+1);
			Player.setPressPauseP2(true);
			zähler2++;
		}

		if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_UP)) {
			this.setDy(-5);

		}

		if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_DOWN)) {
			this.setDy(5);
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT
				|| key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP)) {
			this.setDy(0);
		}

	}

	// Methode resetState
	public void resetState() {
		this.setX(200);
		this.setY(360);
	}

	public void resetState(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	// Getter und Setter Methode der Variable dx
	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getSpielerNr() {
		return spielerNr;
	}

	public void setSpielerNr(int spielerNr) {
		this.spielerNr = spielerNr;
	}

	public static boolean isPressPauseP1() {
		return pressPauseP1;
	}

	public static void setPressPauseP1(boolean pressPauseP1) {
		Player.pressPauseP1 = pressPauseP1;
	}

	public static boolean isPressPauseP2() {
		return pressPauseP2;
	}

	public static void setPressPauseP2(boolean pressPauseP2) {
		Player.pressPauseP2 = pressPauseP2;
	}

	public User getBeitreter() {
		return beitreter;
	}

	public void setBeitreter(User beitreter) {
		this.beitreter = beitreter;
	}

	public User getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(User besitzer) {
		this.besitzer = besitzer;
	}

}