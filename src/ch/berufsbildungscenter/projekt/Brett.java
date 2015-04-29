package ch.berufsbildungscenter.projekt;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.berufsbildungscenter.rmi.Client;

public class Brett extends JPanel {

	private static final long serialVersionUID = -2794739464917198251L;
	// Variablen
	private List<Player> player = new ArrayList<Player>();
	private boolean ingame = true;
	private Muenze coin;
	private Muenze standardCoin;
	private Muenze standardCoinP2;
	private boolean coinDestroyed;
	private Ball ball;
	private int abpaller = 0;
	private int anzCoin = 1;
	private int p1Coin;
	private int p2Coin;
	private int punkteP1;
	private int punkteP2;
	private boolean player1 = false;
	private boolean player2 = false;
	private JPanel consolepanel = new JPanel();
	private JFrame consoleframe;
	private JLabel consolelabel = new JLabel();
	private boolean beendet = false;
	private String ersteller;
	public static boolean player1Score;
	public static boolean player2Score;
	public boolean p1Scores;
	public boolean p2Scores;

	private static boolean pausenKnopf = false;
	private String countdown = "";
	private static int pauseP1 = 0;
	private static int pauseP2 = 0;
	private String winner;
	private long timer;
	private long time = 2000;

	public Brett(Player p1, Player p2, String ersteller) {
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		this.getPlayer().add(p1);
		this.getPlayer().add(p2);
		this.setCoinDestroyed(false);
		this.setErsteller(ersteller);
		this.gameInit();
		timer = System.currentTimeMillis();
		Prozess p = new Prozess();
		p.start();

	}

	public void coin() {
		int zahl = Muenze.randomCoin();

		// if (this.getAbpaller() >= 2) {
		if (zahl <= 35) {
			this.setCoin(new Muenze(1));

		}
		if (zahl > 35 && zahl <= 65) {

			this.setCoin(new Muenze(3));

		}
		if (zahl > 65 && zahl <= 90) {
			this.setCoin(new Muenze(5));
		}
		if (zahl > 90) {
			this.setCoin(new Muenze(10));

			// }
		}

	}

	public void gameInit() {
		this.getPlayer().get(0).resetState(15, 205);
		if (this.getPlayer().size() == 2) {
			this.getPlayer().get(1).resetState(675, 205);
		}
		coin();

		this.setBall(new Ball(2, 0));
		this.setStandardCoin(new Muenze(0));
		this.setStandardCoinP2(new Muenze(0));

	}

	public void paint(Graphics g) {

		super.paint(g);
		if (this.isIngame()) {

			for (Player p : this.getPlayer()) {
				g.drawImage(p.getImage(), p.getX(), p.getY(), p.getWidth(),
						p.getHeight(), this);
			}

			g.drawImage(this.getBall().getImage(), this.getBall().getX(), this
					.getBall().getY(), this.getBall().getWidth(), this
					.getBall().getHeight(), this);

			

			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.drawLine((Gemeinsam.WIDTH / 2), Gemeinsam.HEIGTH,
					(Gemeinsam.WIDTH / 2), 0);
			Font font = new Font("Verdana", Font.BOLD, 18);


			g.setColor(Color.BLACK);
			g.setFont(font);

			g.drawOval(Gemeinsam.WIDTH / 2 - 125, Gemeinsam.HEIGTH / 2 - 140,
					250, 250);
			g.fillOval(Gemeinsam.WIDTH / 2 - 10, Gemeinsam.HEIGTH / 2 - 25, 20,
					20);

		}
	}

	public void newBall(long time) {
		int p1 = 0;
		int p2 = 0;
		if (timer + time <= System.currentTimeMillis()) {
			if (ball.getRect().getMaxX() > Gemeinsam.WIDTH) {
				p1++;
				this.setAbpaller(this.getAbpaller() + 1);

				this.setPunkteP1(this.getPunkteP1() + p1);
				Brett.setPlayer1Score(true);
				Brett.setPlayer2Score(false);
				this.setBall(new Ball(2, 0));
				this.getBall().resetState();
				Brett.setPlayer1Score(false);
				this.setAbpaller(this.getAbpaller() + 1);
			}

			if (ball.getRect().getMinX() < 0) {
				p2++;
				Brett.setPlayer2Score(true);
				this.setPunkteP2(this.getPunkteP2() + p2);
				Brett.setPlayer1Score(false);

				this.setBall(new Ball(2, 0));
				this.getBall().resetState();
				Brett.setPlayer2Score(false);
				this.setAbpaller(this.getAbpaller() + 1);
			}
			timer = System.currentTimeMillis();

		}
	}

	public synchronized void checkCollision() {

		if (ball.getRect().getMaxX() > Gemeinsam.WIDTH) {
			this.setP1Scores(true);
			newBall(2000);
			this.setP1Scores(false);

		}

		if (ball.getRect().getMinX() < 0) {
			this.setP2Scores(true);
			newBall(2000);
			this.setP2Scores(false);
			
		}
		if (this.getPunkteP1() == 7) {
			stopGame();
		}
		if (this.getPunkteP2() == 7) {
			stopGame();
		}

		Rectangle ballR = new Rectangle(ball.getX(), ball.getY(),
				ball.getWidth(), ball.getHeight());
		Rectangle coinR = new Rectangle(coin.getX(), coin.getY(),
				coin.getWidth(), coin.getHeight());

		Rectangle playerCol1 = new Rectangle(this.getPlayer().get(0).getX(),
				this.getPlayer().get(0).getY(), this.getPlayer().get(0)
						.getWidth(), this.getPlayer().get(0).getHeigth());
		Rectangle playerCol2 = new Rectangle(this.getPlayer().get(1).getX(),
				this.getPlayer().get(1).getY(), this.getPlayer().get(1)
						.getWidth(), this.getPlayer().get(1).getHeigth());

		if (ballR.intersects(playerCol1)) {
			this.setPlayer1(true);
			this.setPlayer2(false);
		}
		if (ballR.intersects(playerCol2)) {
			this.setPlayer2(true);
			this.setPlayer1(false);
		}
		for (int i = 0; i < 1; i++) {
			if (ballR.intersects(coinR)) {
				this.getCoin().setDestroyed(true);
				if (this.getCoin().isDestroyed()) {

					if (this.isPlayer1() == true && this.isPlayer2() == false) {

						this.setP1Coin(this.getP1Coin()
								+ this.getCoin().getWert());
					}
					if (this.isPlayer2() == true && this.isPlayer1() == false) {

						this.setP2Coin(this.getP2Coin()
								+ this.getCoin().getWert());
					}
					coin();
					this.getCoin().setDestroyed(false);
				}

			}
		}

		for (Player p : this.getPlayer()) {

			if ((ball.getRect()).intersects(p.getRect())) {
				int paddleLPos = (int) p.getRect().getMinY();
				int ballLPos = (int) ball.getRect().getMinY()
						+ (ball.getHeight() / 2);

				int first = paddleLPos + 5;
				int second = paddleLPos + 10;
				int third = paddleLPos + 15;
				int fourth = paddleLPos + 20;
				int fifth = paddleLPos + 25;
				int sixth = paddleLPos + 30;
				int seven = paddleLPos + 35;
				int eight = paddleLPos + 40;
				int nine = paddleLPos + 45;
				int ten = paddleLPos + 50;
				int eleven = paddleLPos + 55;
				int twelve = paddleLPos + 60;
				int thirteenth = paddleLPos + 65;
				int fourteenth = paddleLPos + 70;
				String Xcor = "";

				if (p.getSpielerNr() == 1) {

					Xcor = "x-";
				} else {
					Xcor = "x+";
				}
				if (ballLPos < first) {
					String[] move = { Xcor, "y-", "y-", "y-", Xcor, "y-", "y-",
							"y-" };
					ball.setBewegen(move);

				}
				if (ballLPos >= first && ballLPos < second) {
					String[] move = { Xcor, "y-", "y-", Xcor, "y-", "y-", Xcor,
							"y-" };
					ball.setBewegen(move);
				}
				if (ballLPos >= second && ballLPos < third) {
					String[] move = { Xcor, "y-", Xcor, "y-", Xcor, "y-", Xcor,
							"y-" };
					ball.setBewegen(move);

				}
				if (ballLPos >= third && ballLPos < fourth) {
					String[] move = { Xcor, "y-", Xcor, "y-", Xcor, "y-", Xcor,
							"y-" };
					ball.setBewegen(move);

				}
				if (ballLPos >= fourth && ballLPos < fifth) {
					String[] move = { Xcor, "y-", Xcor, Xcor, "y-", Xcor, "y-",
							Xcor };
					ball.setBewegen(move);

				}
				if (ballLPos >= fifth && ballLPos < sixth) {
					String[] move = { Xcor, Xcor, Xcor, "y-", Xcor, Xcor, Xcor,
							"y-" };
					ball.setBewegen(move);

				}
				if (ballLPos >= sixth && ballLPos < seven) {
					String[] move = { Xcor, Xcor, Xcor, Xcor, Xcor, Xcor, Xcor,
							"y-" };
					ball.setBewegen(move);

				}
				if (ballLPos >= seven && ballLPos < eight) {
					String[] move = { Xcor, Xcor, Xcor, Xcor, Xcor, Xcor, Xcor,
							Xcor };
					ball.setBewegen(move);

				}
				if (ballLPos >= eight && ballLPos < nine) {
					String[] move = { Xcor, Xcor, Xcor, Xcor, Xcor, Xcor, Xcor,
							"y+" };
					ball.setBewegen(move);

				}
				if (ballLPos >= nine && ballLPos < ten) {
					String[] move = { Xcor, Xcor, Xcor, "y+", Xcor, Xcor, Xcor,
							"y+" };
					ball.setBewegen(move);

				}
				if (ballLPos >= ten && ballLPos < eleven) {
					String[] move = { Xcor, Xcor, "y+", Xcor, Xcor, "y+", Xcor,
							"y+" };
					ball.setBewegen(move);

				}
				if (ballLPos >= eleven && ballLPos < twelve) {
					String[] move = { Xcor, "y+", Xcor, "y+", Xcor, "y+", Xcor,
							"y+" };
					ball.setBewegen(move);

				}
				if (ballLPos >= twelve && ballLPos < thirteenth) {
					String[] move = { Xcor, "y+", Xcor, "y+", Xcor, "y+", Xcor,
							"y+" };
					ball.setBewegen(move);

				}
				if (ballLPos >= thirteenth && ballLPos < fourteenth) {
					String[] move = { Xcor, "y+", "y+", Xcor, "y+", "y+", Xcor,
							"y+" };
					ball.setBewegen(move);

				}
				if (ballLPos > fourteenth) {
					String[] move = { Xcor, "y+", "y+", "y+", Xcor, "y+", "y+",
							"y+" };
					ball.setBewegen(move);

				}

			}

		}
	}

	public synchronized void CounterT() {

		int i = 5;
		this.setConsoleframe(new JFrame());
		this.getConsoleframe().add(this.getConsolelabel());
		this.getConsolepanel().add(this.getConsolelabel());
		this.getConsoleframe().add(this.getConsolepanel(), BorderLayout.CENTER);
		this.getConsoleframe().setLocationRelativeTo(null);
		this.getConsoleframe().setSize(400, 400);
		this.getConsoleframe().setVisible(true);

		while (i >= 1) {

			try {
				if (Player.isPressPauseP1() == true) {
					this.getConsolelabel().setText(
							"Das Spiel geht weiter in: " + i + "\n"
									+ "Sie haben schon " + Brett.getPauseP1()
									+ " von 3 mal pausiert");
				}
				if (Player.isPressPauseP2() == true) {
					this.getConsolelabel().setText(
							"Das Spiel geht weiter in: " + i + "\n\n\n"
									+ "Sie haben schon " + Brett.getPauseP2()
									+ " von 3 mal pausiert");
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			repaint();

			i--;
		}
		this.setCountdown("");
		this.getConsoleframe().setVisible(false);
		setPausenKnopf(false);

	}

	public synchronized void pause() {

		if (Brett.isPausenKnopf() == false) {
			ball.move();
			ball.move();
			ball.move();
			ball.move();

		}
		if (Brett.isPausenKnopf() == true) {
			ball.stop();
			CounterT();

		}

		if (Brett.isPausenKnopf() == false) {
			for (Player p : getPlayer()) {
				p.move();

				if (Brett.isPausenKnopf() == true) {
					p.stop();
				}
			}
		}
	}

	public class Prozess extends Thread {

		public void run() {
			while (!(isBeendet())) {
				pause();

				repaint();

				checkCollision();

				newBall(2000);

			}
		}
	}

	public void stopGame() {

		if (Client.getInstance().getUser() != null) {

			if (Client.getInstance().getUser().getID_user()
					.equals(this.getErsteller())) {

				Client.getInstance()
						.getUser()
						.setGeld(
								Client.getInstance().getUser().getGeld()
										+ this.getP1Coin());
			} else {
				Client.getInstance()
						.getUser()
						.setGeld(
								Client.getInstance().getUser().getGeld()
										+ this.getP2Coin());
			}

			try {
				Client.getInstance().setUser(
						Client.getInstance().connect()
								.updateUser(Client.getInstance().getUser()));
			} catch (RemoteException e) {
				e.printStackTrace();
			}

			this.setIngame(false);
			this.setBeendet(true);

		}
	}

	public boolean isIngame() {
		return ingame;
	}

	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public List<Player> getPlayer() {
		return player;
	}

	public void setPlayer(List<Player> player) {
		this.player = player;
	}

	public Muenze getCoin() {
		return coin;
	}

	public void setCoin(Muenze coin) {
		this.coin = coin;
	}

	public boolean isCoinDestroyed() {
		return coinDestroyed;
	}

	public void setCoinDestroyed(boolean coinDestroyed) {
		this.coinDestroyed = coinDestroyed;
	}

	public int getAnzCoin() {
		return anzCoin;
	}

	public void setAnzCoin(int anzCoin) {
		this.anzCoin = anzCoin;
	}

	public int getAbpaller() {
		return abpaller;
	}

	public void setAbpaller(int abpaller) {
		this.abpaller = abpaller;
	}

	public int getPunkteP1() {
		return punkteP1;
	}

	public void setPunkteP1(int punkteP1) {
		this.punkteP1 = punkteP1;
	}

	public int getPunkteP2() {
		return punkteP2;
	}

	public void setPunkteP2(int punkteP2) {
		this.punkteP2 = punkteP2;
	}

	public String getCountdown() {
		return countdown;
	}

	public void setCountdown(String countdown) {
		this.countdown = countdown;
	}

	public boolean isPlayer1() {
		return player1;
	}

	public void setPlayer1(boolean player1) {
		this.player1 = player1;
	}

	public boolean isPlayer2() {
		return player2;
	}

	public void setPlayer2(boolean player2) {
		this.player2 = player2;
	}

	public JLabel getConsolelabel() {
		return consolelabel;
	}

	public void setConsolelabel(JLabel consolelabel) {
		this.consolelabel = consolelabel;
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

	public int getP1Coin() {
		return p1Coin;
	}

	public void setP1Coin(int p1Coin) {
		this.p1Coin = p1Coin;
	}

	public int getP2Coin() {
		return p2Coin;
	}

	public void setP2Coin(int p2Coin) {
		this.p2Coin = p2Coin;
	}

	public Muenze getStandardCoin() {
		return standardCoin;
	}

	public void setStandardCoin(Muenze standardCoin) {
		this.standardCoin = standardCoin;
	}

	public Muenze getStandardCoinP2() {
		return standardCoinP2;
	}

	public void setStandardCoinP2(Muenze standardCoinP2) {
		this.standardCoinP2 = standardCoinP2;
	}

	public boolean isBeendet() {
		return beendet;
	}

	public void setBeendet(boolean beendet) {
		this.beendet = beendet;
	}

	public String getErsteller() {
		return ersteller;
	}

	public void setErsteller(String ersteller) {
		this.ersteller = ersteller;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public static boolean isPausenKnopf() {
		return pausenKnopf;
	}

	public static void setPausenKnopf(boolean pausenKnopf) {
		Brett.pausenKnopf = pausenKnopf;
	}

	public static int getPauseP1() {
		return pauseP1;
	}

	public static void setPauseP1(int pauseP1) {
		Brett.pauseP1 = pauseP1;
	}

	public static int getPauseP2() {
		return pauseP2;
	}

	public static void setPauseP2(int pauseP2) {
		Brett.pauseP2 = pauseP2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public static boolean isPlayer1Score() {
		return player1Score;
	}

	public static void setPlayer1Score(boolean player1Score) {
		Brett.player1Score = player1Score;
	}

	public static boolean isPlayer2Score() {
		return player2Score;
	}

	public static void setPlayer2Score(boolean player2Score) {
		Brett.player2Score = player2Score;
	}

	public boolean isP1Scores() {
		return p1Scores;
	}

	public void setP1Scores(boolean p1Scores) {
		this.p1Scores = p1Scores;
	}

	public boolean isP2Scores() {
		return p2Scores;
	}

	public void setP2Scores(boolean p2Scores) {
		this.p2Scores = p2Scores;
	}

}
