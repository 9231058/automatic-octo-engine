package menchclient.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import menchclient.domain.Account;
import menchclient.network.MessageServer;
import menchclient.network.NetworkReader;

public class AccountManagerFrame extends JFrame {

	private static final long serialVersionUID = 8054157296698419452L;

	private JFrame newGame;
	private JFrame join;
	private JFrame waitForConnection;
	private JFrame waitForStart;
	private JPasswordField newGamePass;
	private JPasswordField pass;
	private JTextField usertext;
	private Thread startThread;
	private JLabel numberOfConnections;

	private boolean started;

	private Account c;

	private int gameNumber;

	private NetworkReader reader;

	public AccountManagerFrame(Account c) {

		started = false;

		this.c = c;

		reader = new NetworkReader();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(300, 270));
		setLayout(null);

		JLabel sayHi = new JLabel("Hi " + c.getName());
		sayHi.setBounds(10, 10, 280, 30);
		add(sayHi);

		JButton creatNewGame = new JButton("Creat new game");
		creatNewGame.setBounds((getSize().width - 140) / 2, 60, 140, 40);
		creatNewGame.addActionListener(new CreateNewGameButton());
		add(creatNewGame);

		JLabel creatExpl = new JLabel(
				"*creat new game and your friends can join");
		creatExpl.setBounds((getSize().width - 280) / 2, 100, 280, 30);
		add(creatExpl);

		JButton join = new JButton("join a game");
		join.setBounds((getSize().width - 140) / 2, 140, 140, 40);
		join.addActionListener(new joinButton());
		add(join);

		JLabel joinExpl = new JLabel("*join a created game");
		joinExpl.setBounds((getSize().width - 140) / 2, 180, 140, 30);
		add(joinExpl);

		setVisible(true);
	}

	private int getNumber() {

		MessageServer.getInstance().sendMessage(
				"numberOfConnection g " + gameNumber);

		return Integer.valueOf(reader.receiveMessage());
	}

	class CreateNewGameButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			newGame = new JFrame();
			newGame.setBounds(getLocationOnScreen().x,
					getLocationOnScreen().y + 60, 300, 150);
			newGame.setLayout(null);

			JLabel password = new JLabel("password:");
			password.setBounds(10, 30, 70, 30);
			newGame.add(password);

			newGamePass = new JPasswordField();
			newGamePass.setBounds(80, 30, 210, 30);
			newGame.add(newGamePass);

			JButton create = new JButton("Create");
			create.setBounds((newGame.getSize().width - 80) / 2, 70, 80, 40);
			create.addActionListener(new createButton());
			newGame.add(create);

			newGame.setVisible(true);
		}

	}

	class joinButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			join = new JFrame();
			join.setBounds(getLocationOnScreen().x,
					getLocationOnScreen().y + 60, 300, 200);
			join.setLayout(null);

			JLabel username = new JLabel("username:");
			username.setBounds(10, 30, 70, 30);
			join.add(username);

			usertext = new JTextField();
			usertext.setBounds(80, 30, 210, 30);
			join.add(usertext);

			JLabel password = new JLabel("password:");
			password.setBounds(10, 70, 70, 30);
			join.add(password);

			pass = new JPasswordField();
			pass.setBounds(80, 70, 210, 30);
			join.add(pass);

			JButton joinb = new JButton("Join");
			joinb.setBounds((join.getSize().width - 80) / 2, 110, 80, 40);
			joinb.addActionListener(new joinbButton());
			join.add(joinb);

			join.setVisible(true);
		}

	}

	class createButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			newGame.setVisible(false);

			setString();

			waitForConnection = new JFrame();
			waitForConnection.setBounds(getLocationOnScreen().x,
					getLocationOnScreen().y + 80, 300, 140);
			waitForConnection.setLayout(null);

			numberOfConnections = new JLabel("" + getNumber()
					+ "/4 are connected");
			numberOfConnections.setBounds(90, 10, 120, 30);
			waitForConnection.add(numberOfConnections);

			JButton start = new JButton("Start");
			start.setBounds(110, 50, 80, 40);
			start.addActionListener(new startButton());
			waitForConnection.add(start);

			waitForConnection.setVisible(true);

			startThread = new Thread(new TForStart());
			startThread.start();
		}

		private String setString() {

			MessageServer.getInstance().sendMessage(
					"newGame u " + c.getUsername() + " p "
							+ new String(newGamePass.getPassword()));

			gameNumber = Integer.valueOf(reader.receiveMessage());

			return "newGame " + new String(newGamePass.getPassword());
		}
	}

	class joinbButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			join.setVisible(false);

			waitForStart = new JFrame();
			waitForStart.setBounds(getLocationOnScreen().x,
					getLocationOnScreen().y + 80, 300, 140);
			waitForStart.setLayout(null);

			numberOfConnections = new JLabel("" + (getNumber() + 1)
					+ "/4 are connected");
			numberOfConnections.setBounds(90, 10, 120, 30);
			waitForStart.add(numberOfConnections);

			JLabel wait = new JLabel("wait for " + usertext.getText()
					+ " to start the game");
			wait.setBounds(90, 50, 120, 30);
			waitForStart.add(wait);

			waitForStart.setVisible(true);

		}

		public void setString() {

			MessageServer.getInstance().sendMessage(
					"joinGame u " + usertext.getText() + " p "
							+ new String(pass.getPassword()));

			String s = reader.receiveMessage();
			String[] sArr = s.split(" ");

			if (sArr[0].equals("true")) {

				gameNumber = Integer.valueOf(sArr[1]);

				join.setVisible(false);

				waitForStart = new JFrame();
				waitForStart.setBounds(getLocationOnScreen().x,
						getLocationOnScreen().y + 80, 300, 140);
				waitForStart.setLayout(null);

				numberOfConnections = new JLabel("" + (getNumber() + 1)
						+ "/4 are connected");
				numberOfConnections.setBounds(90, 10, 120, 30);
				waitForStart.add(numberOfConnections);

				JLabel wait = new JLabel("wait for " + usertext.getText()
						+ " to start the game");
				wait.setBounds(90, 50, 120, 30);
				waitForStart.add(wait);

				waitForStart.setVisible(true);
			} else {

				JOptionPane.showMessageDialog(null, "INCORRECT");
			}

		}

	}

	class startButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			setVisible(false);
			waitForConnection.setVisible(false);

			started = true;

			setVisible(false);
			waitForConnection.setVisible(false);
			dispose();
			
			//TODO
		}

	}

	class TForStart implements Runnable {

		@Override
		public void run() {

			while (!started) {
				numberOfConnections.setText("" + getNumber()
						+ "/4 are connected");
				waitForConnection.repaint(1000);
			}
		}
	}

	class TForWait implements Runnable {

		@Override
		public void run() {

			while (!started) {
				numberOfConnections.setText("" + getNumber()
						+ "/4 are connected");
				if (reader.receiveMessage().equals("started")) {

					started = true;

					// TODO
				}
				waitForConnection.repaint(1000);
			}
			setVisible(false);
			waitForStart.setVisible(false);
			dispose();
		}
	}
}
