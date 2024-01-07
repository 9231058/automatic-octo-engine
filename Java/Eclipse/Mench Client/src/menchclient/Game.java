package menchclient;

import java.util.ArrayList;

import menchclient.domain.Account;

public class Game {

	private Account firstPlayer;
	private String password;
	private int numberOfConnections;
	private ArrayList<String> ip;
	boolean started;
	int dice;
	Dice d;
	int turn;

	public Game(Account firstPlayer, String password) {

		this.firstPlayer = firstPlayer;
		this.password = password;
		numberOfConnections = 1;
		ip = new ArrayList<String>();
		ip.add(firstPlayer.getIP());
		started = false;
		d = new Dice();
		dice = d.getDice();
		turn = 1;
	}

	public void addAConnection(String ip) {

		if (numberOfConnections < 4) {
			this.ip.add(ip);
			numberOfConnections++;
		}
	}
	
	public int getNumberOfConnections() {
		
		return numberOfConnections;
	}
	
	public Account getFirstPlayerAccount() {
		
		return firstPlayer;
	}
	
	public ArrayList<String> getIPs() {
		return ip;
	}
	
	public boolean isStarted() {
		return started;
	}
	
	public void start() {
		started = true;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getDice() {
		return dice;
	}
	
	public int newDice() {
		dice = d.newDice();
		return dice;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void changeTurn() {
		turn = (turn % ip.size()) + 1;
	}
}
