package menchclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import menchclient.conf.MenchConfig;

public class Player {

	private int turn;
	private int game;
	private String ip;
	private Marble[] marbles;

	public Player(int turn) {
		ip = MenchConfig.getInstance().getIP();
		this.turn = turn;

		marbles = new Marble[4];

		for (int i = 0; i < 4; i++)
			marbles[i] = new Marble(turn, i);
	}

	public boolean haveActivatedMarble() {

		for (int i = 0; i < 4; i++)
			if (marbles[i].isActivated())
				return true;

		return false;
	}

	public void changePosition(int marbleNum, int number) {

		marbles[marbleNum].addToCounter(number);

		tellServerTheChange(marbleNum, number);
	}

	public void tellServerTheChange(int marbleNum, int number) {

		sendToServer("change g " + game + " p " + turn + " m " + marbleNum
				+ " c " + number);
	}

	public void active(int marbleNum) {

		marbles[marbleNum].activate();

		tellServerTheActive(marbleNum);
	}

	private void tellServerTheActive(int marbleNum) {

		sendToServer("active g " + game + " p " + turn + " m " + marbleNum);
	}

	public void deActive(int marbleNum) {

		marbles[marbleNum].deactivate();
	}

	public Marble[] getMarbles() {

		return marbles;
	}

	public String getIP() {
		return ip;
	}

	private void sendToServer(String message) {

		try {
			Socket connection = new Socket(ip, 13741);

			PrintWriter pw = new PrintWriter(connection.getOutputStream());

			pw.println(message);
			pw.flush();

			connection.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}