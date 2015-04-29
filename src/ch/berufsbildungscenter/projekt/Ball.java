
package ch.berufsbildungscenter.projekt;

import java.util.Random;

public class Ball extends Form implements Gemeinsam {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1292357828971032774L;
	private String[] bewegen = new String[8];
	private int speed;
	Random rand = new Random();
	private boolean player1Score ;
	private boolean player2Score ;
	

	// Getter und Setter Methode zu speed
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// Konstruktor Ball
	public Ball(int speed, int id) {
		
		super(0, "/images/ball.png");
		String Xcor = "";
		int winkel = rand.nextInt((30) + 1);
		if (Brett.isPlayer1Score() == false && Brett.isPlayer2Score() == false) {
			Xcor = "x+";
		}
		if (Brett.isPlayer1Score() == true) {
			Xcor = "x-";
		}
		if (Brett.isPlayer2Score() == true) {
			Xcor = "x+";
		}
		String[] start = new String[8];
		if (winkel <= 10) {
			start[0] = Xcor;
			start[1] = "y-";
			start[2] = Xcor;
			start[3] = "y-";
			start[4] = Xcor;
			start[5] = "y-";
			start[6] = Xcor;
			start[7] = "y-";

		}
		if (winkel > 10 && winkel <= 20) {
			start[0] = Xcor;
			start[1] = "y+";
			start[2] = Xcor;
			start[3] = "y+";
			start[4] = Xcor;
			start[5] = "y+";
			start[6] = Xcor;
			start[7] = "y+";

		}
		if (winkel > 20) {
			start[0] = Xcor;
			start[1] = Xcor;
			start[2] = Xcor;
			start[3] = Xcor;
			start[4] = Xcor;
			start[5] = Xcor;
			start[6] = Xcor;
			start[7] = Xcor;

		}
		this.setBewegen(start);
		this.resetState();
		this.setSpeed(speed);

	}

	// Methode move
	public void move() {

		String holder = this.getBewegen()[0];
		int i = 0;

		if (this.getBewegen()[0].equals("x+")) {
			this.setX(this.getX() + this.getSpeed());
			i = 0;
			for (String wohin : this.getBewegen()) {
				if (i != 7) {
					this.getBewegen()[i] = this.getBewegen()[i + 1];
				} else {
					this.getBewegen()[i] = holder;
				}
				i++;
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		else if (this.getBewegen()[0].equals("x-")) {
			this.setX(this.getX() - this.getSpeed());
			i = 0;
			for (String wohin : this.getBewegen()) {
				if (i != 7) {
					this.getBewegen()[i] = this.getBewegen()[i + 1];
				} else {
					this.getBewegen()[i] = holder;
				}
				i++;
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		else if (this.getBewegen()[0].equals("y+")) {
			this.setY(this.getY() + this.getSpeed());
			i = 0;
			for (String wohin : this.getBewegen()) {
				if (i != 7) {
					this.getBewegen()[i] = this.getBewegen()[i + 1];
				} else {
					this.getBewegen()[i] = holder;
				}
				i++;
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		else if (this.getBewegen()[0].equals("y-")) {
			this.setY(this.getY() - this.getSpeed());
			i = 0;
			for (String wohin : this.getBewegen()) {
				if (i != 7) {
					this.getBewegen()[i] = this.getBewegen()[i + 1];
				} else {
					this.getBewegen()[i] = holder;
				}
				i++;
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		if (this.getY() < 0) {
			int j = 0;
			for (String replace : this.getBewegen()) {

				if (replace.equals("y-")) {
					this.getBewegen()[j] = "y+";
				}

				j++;
			}
			j = 0;

		}
		if (this.getY() > 415) {
			int j = 0;
			for (String replace : this.getBewegen()) {

				if (replace.equals("y+")) {
					this.getBewegen()[j] = "y-";
				}

				j++;
			}
			j = 0;

		}

	}

	public void stop() {

	}
	public synchronized void checkBall(){
		try {
		Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Methode resetState
	public void resetState() {
		this.setX(350);
		this.setY(225);

	}

	public String[] getBewegen() {
		return bewegen;
	}

	public void setBewegen(String[] bewegen) {
		this.bewegen = bewegen;
	}

	public boolean isPlayer2Score() {
		return player2Score;
	}

	public void setPlayer2Score(boolean player2Score) {
		this.player2Score = player2Score;
	}

	public boolean isPlayer1Score() {
		return player1Score;
	}

	public void setPlayer1Score(boolean player1Score) {
		this.player1Score = player1Score;
	}

}
