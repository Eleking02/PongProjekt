package ch.berufsbildungscenter.projekt;

import java.io.Serializable;

import ch.berufsbildungscenter.projekt.Player;
import ch.berufsbildungscenter.rmi.Client;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1641573949050502420L;
	private String ID_user ;
	private String mail ;
	private String password ;
	private String passwordConf ;
	private int selectedPaddle;
	private boolean hasPaddle0;
	private boolean hasPaddle1;
	private boolean hasPaddle2;
	private boolean hasPaddle3;
	private boolean hasPaddle4;
	private boolean hasPaddle5;
	private boolean hasPaddle6;
	private int geld;
	private int rankedPunkte;
	private int gespielteSpiele;
	private int gewonneneSpiele;
	private float winLose;
	private Client client = Client.getInstance();
	private Player player = new Player(0,0);
	private boolean win;
	private boolean lose;

	
public User(){
	this.setID_user(null);
	this.setMail(null);
	this.setPassword(null);
	this.setPasswordConf(null);
	this.setSelectedPaddle(0);
	this.setHasPaddle0(false);
	this.setHasPaddle1(false);
	this.setHasPaddle2(false);
	this.setHasPaddle3(false);
	this.setHasPaddle4(false);
	this.setHasPaddle5(false);
	this.setHasPaddle6(false);
	this.setGeld(0);
	this.setRankedPunkte(0);
	this.setGespielteSpiele(0);
	this.setGewonneneSpiele(0);
	this.setWinLose(0);
}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getID_user() {
		return ID_user;
	}

	public void setID_user(String ID_user) {
		this.ID_user = ID_user;
	}

	public String getPasswordConf() {
		return passwordConf;
	}

	public void setPasswordConf(String passwordConf) {
		this.passwordConf = passwordConf;
	}

	public int getSelectedPaddle() {
		return selectedPaddle;
	}

	public void setSelectedPaddle(int selectedPaddle) {
		this.selectedPaddle = selectedPaddle;
	}

	public boolean isHasPaddle1() {
		return hasPaddle1;
	}

	public void setHasPaddle1(boolean hasPaddle1) {
		this.hasPaddle1 = hasPaddle1;
	}

	public boolean isHasPaddle2() {
		return hasPaddle2;
	}

	public void setHasPaddle2(boolean hasPaddle2) {
		this.hasPaddle2 = hasPaddle2;
	}

	public boolean isHasPaddle3() {
		return hasPaddle3;
	}

	public void setHasPaddle3(boolean hasPaddle3) {
		this.hasPaddle3 = hasPaddle3;
	}

	public boolean isHasPaddle4() {
		return hasPaddle4;
	}

	public void setHasPaddle4(boolean hasPaddle4) {
		this.hasPaddle4 = hasPaddle4;
	}

	public boolean isHasPaddle5() {
		return hasPaddle5;
	}

	public void setHasPaddle5(boolean hasPaddle5) {
		this.hasPaddle5 = hasPaddle5;
	}

	public int getGeld() {
		return geld;
	}

	public void setGeld(int geld) {
		this.geld = geld;
	}

	public int getRankedPunkte() {
		return rankedPunkte;
	}

	public void setRankedPunkte(int rankedPunkte) {
		this.rankedPunkte = rankedPunkte;
	}

	public int getGespielteSpiele() {
		return gespielteSpiele;
	}

	public void setGespielteSpiele(int gespielteSpiele) {
		this.gespielteSpiele = gespielteSpiele;
	}

	public int getGewonneneSpiele() {
		return gewonneneSpiele;
	}

	public void setGewonneneSpiele(int gewonneneSpiele) {
		this.gewonneneSpiele = gewonneneSpiele;
	}

	public float getWinLose() {
		return winLose;
	}

	public void setWinLose(float winLose) {
		this.winLose = winLose;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public boolean isWin() {
		return win;
	}


	public void setWin(boolean win) {
		this.win = win;
	}


	public boolean isLose() {
		return lose;
	}


	public void setLose(boolean lose) {
		this.lose = lose;
	}


	public boolean isHasPaddle0() {
		return hasPaddle0;
	}


	public void setHasPaddle0(boolean hasPaddle0) {
		this.hasPaddle0 = hasPaddle0;
	}


	public boolean isHasPaddle6() {
		return hasPaddle6;
	}


	public void setHasPaddle6(boolean hasPaddle6) {
		this.hasPaddle6 = hasPaddle6;
	}

}
