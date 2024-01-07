package menchclient;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import menchclient.domain.Message;
import menchclient.network.MessageHandler;

public class Panel extends JPanel {

	private static final long serialVersionUID = 4114892184745203555L;

	int myTurn;
	int game;
	private int playTurn; // witch player have to play GET FROM SERVER
	private int dice; // dice GET FROM SERVER

	Place[] place;
	Player[] player;

	BufferedImage blue_marble;
	BufferedImage green_marble;
	BufferedImage red_marble;
	BufferedImage yellow_marble;
	BufferedImage free_place;
	BufferedImage[] dice_pic;

	public Panel(Player[] player, int myTurn) {

		this.player = player;
		this.myTurn = myTurn;
		place = new Place[72];
		dice_pic = new BufferedImage[6];
		Thread t;
		try {
			t = new Thread(new DeCoder(new Socket(player[myTurn - 1].getIP(),
					1374)));
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setPlace();
		ImagePanel();
	}

	private void setPlace() {

		place[0] = new Place(50, 470);
		place[1] = new Place(120, 470);
		place[2] = new Place(190, 470);
		place[3] = new Place(260, 470);
		place[4] = new Place(330, 470);
		place[5] = new Place(330, 540);
		place[6] = new Place(330, 610);
		place[7] = new Place(330, 680);
		place[8] = new Place(330, 750);
		place[9] = new Place(400, 750);
		place[10] = new Place(470, 750);
		place[11] = new Place(470, 680);
		place[12] = new Place(470, 610);
		place[13] = new Place(470, 540);
		place[14] = new Place(470, 470);
		place[15] = new Place(540, 470);
		place[16] = new Place(610, 470);
		place[17] = new Place(680, 470);
		place[18] = new Place(750, 470);
		place[19] = new Place(750, 400);
		place[20] = new Place(750, 330);
		place[21] = new Place(680, 330);
		place[22] = new Place(610, 330);
		place[23] = new Place(540, 330);
		place[24] = new Place(470, 330);
		place[25] = new Place(470, 260);
		place[26] = new Place(470, 190);
		place[27] = new Place(470, 120);
		place[28] = new Place(470, 50);
		place[29] = new Place(400, 50);
		place[30] = new Place(330, 50);
		place[31] = new Place(330, 120);
		place[32] = new Place(330, 190);
		place[33] = new Place(330, 260);
		place[34] = new Place(330, 330);
		place[35] = new Place(260, 330);
		place[36] = new Place(190, 330);
		place[37] = new Place(120, 330);
		place[38] = new Place(50, 330);
		place[39] = new Place(50, 400);
		place[40] = new Place(50, 680);
		place[41] = new Place(50, 750);
		place[42] = new Place(120, 680);
		place[43] = new Place(120, 750);
		place[44] = new Place(680, 680);
		place[45] = new Place(680, 750);
		place[46] = new Place(750, 680);
		place[47] = new Place(750, 750);
		place[48] = new Place(680, 50);
		place[49] = new Place(680, 120);
		place[50] = new Place(750, 50);
		place[51] = new Place(750, 120);
		place[52] = new Place(50, 50);
		place[53] = new Place(50, 120);
		place[54] = new Place(120, 50);
		place[55] = new Place(120, 120);

		place[56] = new Place(825, 325);
		place[57] = new Place(875, 325);
		place[58] = new Place(925, 325);
		place[59] = new Place(975, 325);

		place[60] = new Place(825, 425);
		place[61] = new Place(875, 425);
		place[62] = new Place(925, 425);
		place[63] = new Place(975, 425);

		place[64] = new Place(825, 525);
		place[65] = new Place(875, 525);
		place[66] = new Place(925, 525);
		place[67] = new Place(975, 525);

		place[68] = new Place(825, 625);
		place[69] = new Place(875, 625);
		place[70] = new Place(925, 625);
		place[71] = new Place(975, 625);
	}

	private boolean isFree(int n) {

		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < 4; j++) {
				if (player[i].getMarbles()[j].getPlace() == n)
					return false;
			}
		}

		return true;
	}

	private boolean isPlayerTurnMarbleInN(int turn, int n) {

		for (int i = 0; i < 4; i++) {
			if (player[turn - 1].getMarbles()[i].getPlace() == n)
				return true;
		}

		return false;
	}

	private int witchMarbleOfPlayerTurnInN(int turn, int n) {

		for (int i = 0; i < 4; i++) {
			if (player[turn - 1].getMarbles()[i].getPlace() == n)
				return i;
		}
		return -1;
	}

	private int tellNumber(int x, int y) {

		for (int i = 0; i < place.length; i++) {

			if (x >= place[i].getX() && x <= place[i].getX() + 50
					&& y >= place[i].getY() && y <= place[i].getY() + 50)
				return i;
		}

		return -1;
	}

	private void ImagePanel() {
		try {
			blue_marble = ImageIO.read(new File("Blue_Marble.jpg"));
			red_marble = ImageIO.read(new File("Red_Marble.jpg"));
			yellow_marble = ImageIO.read(new File("Yellow_Marble.jpg"));
			green_marble = ImageIO.read(new File("Green_Marble.jpg"));
			dice_pic[0] = ImageIO.read(new File("dice_1.jpg"));
			dice_pic[1] = ImageIO.read(new File("dice_2.jpg"));
			dice_pic[2] = ImageIO.read(new File("dice_3.jpg"));
			dice_pic[3] = ImageIO.read(new File("dice_4.jpg"));
			dice_pic[4] = ImageIO.read(new File("dice_5.jpg"));
			dice_pic[5] = ImageIO.read(new File("dice_6.jpg"));
			free_place = ImageIO.read(new File("FreePlace.jpg"));
		} catch (IOException ex) {
		}
	}

	private void checkDeactivation(int n) {

		for (int i = 0; i < player.length; i++) {

			if (isPlayerTurnMarbleInN(i + 1, n)) {

				player[i].deActive(witchMarbleOfPlayerTurnInN(i + 1, n));

				break;
			}
		}
	}

	private boolean haveMove() {

		for (int i = 0; i < 4; i++) {

			if (player[myTurn - 1].getMarbles()[i].isGoal())
				continue;

			if (player[myTurn - 1].getMarbles()[i].isActivated()) {

				if ((player[myTurn - 1].getMarbles()[i].getPlace() + dice) >= 40)
					return true;

				if (!isPlayerTurnMarbleInN(myTurn,
						player[myTurn - 1].getMarbles()[i].getPlace() + dice))
					return true;

			} else {
				if (dice == 6) {
					if (!isPlayerTurnMarbleInN(myTurn,
							player[myTurn - 1].getMarbles()[i].getFirstPlace()))
						return true;
				}
			}
		}

		return false;
	}

	public void paintComponent(Graphics g) {

		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (playTurn == myTurn) {

					if (haveMove()) {

						int n = tellNumber(e.getX(), e.getY());

						if (n != -1) {

							if (isPlayerTurnMarbleInN(myTurn, n)) {

								if (player[myTurn - 1].getMarbles()[witchMarbleOfPlayerTurnInN(
										myTurn, n)].isActivated()) {

									if (player[myTurn - 1].getMarbles()[witchMarbleOfPlayerTurnInN(
											myTurn, n)].getCounter() + 6 >= 40) {

										player[myTurn - 1].changePosition(
												witchMarbleOfPlayerTurnInN(
														myTurn, n), dice);
									} else {

										if (isFree(n + dice)) {

											player[myTurn - 1].changePosition(
													witchMarbleOfPlayerTurnInN(
															myTurn, n), dice);
										} else {

											if (!isPlayerTurnMarbleInN(myTurn,
													n + dice)) {

												checkDeactivation(n + dice);
											}
										}
									}

								} else {

									if (dice == 6) {

										if (!isPlayerTurnMarbleInN(
												myTurn,
												player[myTurn - 1].getMarbles()[0]
														.getFirstPlace())) {

											player[myTurn - 1]
													.active(witchMarbleOfPlayerTurnInN(
															myTurn, n));

											checkDeactivation(player[myTurn - 1]
													.getMarbles()[0]
													.getFirstPlace());
										}
									}
								}
							}
						}
					} else {

						String message = "";

						message = "change g " + game + " p " + myTurn
								+ " noMove";

						Thread send = new Thread(new sendToServer(
								player[myTurn - 1].getIP(), message));
						send.start();
					}
					repaint();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		for (int i = 0; i < place.length; i++) {
			if (isFree(i)) {
				g.drawImage(free_place, place[i].getX(), place[i].getY(), null);
				continue;
			}
			for (int j = 0; j < player.length; j++) {
				if (isPlayerTurnMarbleInN(j + 1, i)) {
					g.drawImage(red_marble, place[i].getX(), place[i].getY(),
							null);
					continue;
				}
			}
		}

		g.drawImage(dice_pic[dice - 1], 875, 50, null);
	}

	class DeCoder implements Runnable {

		Socket socket;

		public DeCoder(Socket socket) {

			this.socket = socket;
		}

		@Override
		public void run() {
			while (true) {
				commandReader();
			}
		}

		private void commandReader() {

			MessageHandler mh = new MessageHandler(socket);

			Message m = mh.getMessage();

			String s = m.getMessage();

			String[] sArr = s.split(" ");

			if (sArr[0].equals("change")) { // <<change p "player number" m
											// "marble number" c
											// "counter change">>

				int playerTurn = Integer.valueOf(sArr[2]);
				int marbleNumber = Integer.valueOf(sArr[4]);
				int counterChange = Integer.valueOf(sArr[6]);
				int number = player[playerTurn - 1].getMarbles()[marbleNumber - 1]
						.getPlace() + counterChange;

				if (!(playerTurn == myTurn)) {
					if (!isFree(number)) {

						for (int i = 0; i < player.length; i++) {
							if (isPlayerTurnMarbleInN(i, number)) {
								player[i].getMarbles()[witchMarbleOfPlayerTurnInN(
										i, number)].deactivate();
							}
						}
					}

					player[playerTurn - 1].getMarbles()[marbleNumber - 1]
							.addToCounter(counterChange);

					repaint();
				}
			}

			if (sArr[0].equals("play")) { // <<play p "player turn" dice
											// "dice">>

				playTurn = Integer.valueOf(sArr[2]);
				dice = Integer.valueOf(sArr[4]);
				repaint();
			}

			if (sArr[0].equals("active")) { // <<active p "player turn" m
											// "marble number">>

				int playerTurn = Integer.valueOf(sArr[2]);
				int marbleNumber = Integer.valueOf(sArr[4]);
				int number = player[playerTurn - 1].getMarbles()[marbleNumber - 1]
						.getFirstPlace();

				if (!isFree(number)) {

					for (int i = 0; i < player.length; i++) {
						if (isPlayerTurnMarbleInN(i, number)) {
							player[i].getMarbles()[witchMarbleOfPlayerTurnInN(
									i, number)].deactivate();
						}
					}
				}

				player[playerTurn - 1].getMarbles()[marbleNumber - 1]
						.activate();
			}
		}
	}

	class sendToServer implements Runnable {

		String ip;
		String message;

		public sendToServer(String ip, String message) {
			this.ip = ip;
			this.message = message;
		}

		@Override
		public void run() {
			sendMessage();
		}

		private void sendMessage() {
			try {
				Socket connection = new Socket(ip, 1374);
				PrintWriter pw = new PrintWriter(connection.getOutputStream());
				pw.println(message);
				pw.flush();
				connection.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}