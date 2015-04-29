package ch.berufsbildungscenter.projekt;

import java.util.Random;

import javax.swing.ImageIcon;

public class Muenze extends Form {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2681670899264368776L;
	private int wert;
	private boolean destroyed;

	public Muenze(int wert) {
		super(0, "/images/" + wert + ".png");
		this.setWert(wert);
		this.randomPos();
		destroyed = false;

	}
	

	public void randomPos() {
		Random rand = new Random();
		int xR = rand.nextInt((600) + 1);
		int xR2 = rand.nextInt((50)+1);
		int yR = rand.nextInt((300) + 1);
		int yR2 = rand.nextInt((50)+1);
		this.setX(xR+xR2);
		this.setY(yR+yR2);
	}
	public static int randomCoin(){
		Random randC = new Random();
		int zahl = randC.nextInt(100);
		return zahl;
		
	}

	public int getWert() {
		return wert;
	}

	public void setWert(int wert) {
		this.wert = wert;
		this.setImage( new ImageIcon(this.getClass().getResource("/images/" + wert + ".png")));
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

}
