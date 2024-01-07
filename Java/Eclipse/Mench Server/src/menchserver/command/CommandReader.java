package menchserver.command;

import java.util.ArrayList;

import menchserver.domain.Account;
import menchserver.domain.Game;
import menchserver.domain.Message;
import menchserver.network.MessageHandler;
import menchserver.network.MessageServer;

public class CommandReader implements Runnable {
	private ArrayList<Message> commands;
	private MessageServer messageServer;
	private ArrayList<Account> accounts;
	private ArrayList<Game> games;

	public CommandReader() {
		messageServer = MessageServer.getInstance();
		new Thread(messageServer, "Message Server").start();
		accounts = new ArrayList<Account>();
		games = new ArrayList<Game>();
		commands = new ArrayList<Message>();
	}

	@Override
	public void run() {

		while (true) {
			commandGetter();
			commandAnalyzer();
			try {
				Thread.sleep(100);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}

	public void commandGetter() {
		MessageHandler handler;
		if ((handler = messageServer.getHandler()) != null) {
			commands.add(handler.getMessage());
		}
	}

	private void commandAnalyzer() {

		if (commands.size() > 0) {
			Message m = commands.get(0);

			String ip = m.getIp();
			String mS = m.getMessage();
			String[] mSArr = mS.split(" ");

			if (mSArr[0].equals("login")) {

				String username = mSArr[2];
				String password = mSArr[4];

				if (existUsername(username)) {
					if (isCorrectUserAndPass(username, password)) {
						messageServer.sendMessage(new Message("true n "
								+ getNameOfAccount(username), ip));
					} else {
						messageServer.sendMessage(new Message("false", ip));
					}
				} else {
					messageServer.sendMessage(new Message("false", ip));
				}
			}
			if (mSArr[0].equals("signup")) {

				String name = mSArr[2];
				String username = mSArr[4];
				String password = mSArr[6];

				if (!existUsername(username)) {

					accounts.add(new Account(name, username, password));

					messageServer.sendMessage(new Message("true", ip));
				} else {
					messageServer.sendMessage(new Message("false", ip));
				}
			}
			if (mSArr[0].equals("numberOfConnection")) {

				int gameNumber = Integer.valueOf(mSArr[2]);

				messageServer.sendMessage(new Message(""
						+ games.get(gameNumber).getNumberOfConnections(), ip));
			}
			if (mSArr[0].equals("newGame")) {

				String username = mSArr[2];
				String password = mSArr[4];

				games.add(new Game(accounts.get(witchAccount(username)),
						password));

				messageServer.sendMessage(new Message("" + (games.size() - 1),
						ip));

			}
			if (mSArr[0].equals("joinGame")) {

				String username = mSArr[2];
				String password = mSArr[4];

				if (isCorrectUsernameAndPasswordForGame(username, password)) {

					games.get(witchGame(username)).addAConnection(ip);

					messageServer.sendMessage(new Message("true "
							+ witchGame(username), ip));
				} else {

					messageServer.sendMessage(new Message("false", ip));
				}
			}
			if (mSArr[0].equals("change")) {

				int gameNumber = Integer.valueOf(mSArr[2]);
				int playerTurn = Integer.valueOf(mSArr[4]);

				if (mSArr.length == 9) {
					int marbleNumber = Integer.valueOf(mSArr[6]);
					for (int i = 0; i < games.get(gameNumber).getIPs().size(); i++) {

						if (i != playerTurn - 1) {
							messageServer.sendMessage(new Message("cahnge p "
									+ playerTurn + " m " + marbleNumber, games
									.get(gameNumber).getIPs().get(i)));
						}
					}
				}

				newTurnAndDice(gameNumber);
			}
			if (mSArr[0].equals("active")) {

				int gameNumber = Integer.valueOf(mSArr[2]);
				int playerTurn = Integer.valueOf(mSArr[4]);
				int marbleNumber = Integer.valueOf(mSArr[6]);

				for (int i = 0; i < games.get(gameNumber).getIPs().size(); i++) {

					if (i != playerTurn - 1) {
						messageServer.sendMessage(new Message("active p "
								+ playerTurn + " m " + marbleNumber, games
								.get(gameNumber).getIPs().get(i)));
					}
				}

				newTurnAndDice(gameNumber);
			}

			commands.remove(0);
		}
	}

	private void newTurnAndDice(int gameNumber) {
		if (!(games.get(gameNumber).getDice() == 6)) {
			games.get(gameNumber).changeTurn();
		}
		for (int i = 0; i < games.get(gameNumber).getIPs().size(); i++) {

			messageServer.sendMessage(new Message("play t"
					+ games.get(gameNumber).getTurn() + " d "
					+ games.get(gameNumber).newDice(), games.get(gameNumber)
					.getIPs().get(i)));
		}
	}

	/*
	 * private void tellCreatedGameList() {
	 * 
	 * ArrayList<JLabel> jl = new ArrayList<JLabel>();
	 * 
	 * for (int i = 0; i < games.size(); i++) { if (!games.get(i).isStarted())
	 * jl.add(new JLabel("" + (jl.size() + 1) + ". " +
	 * games.get(i).getFirstPlayerAccount().getUsername())); }
	 * 
	 * if (jl.size() > 0) {
	 * 
	 * JFrame frame = new JFrame(); frame.setLayout(null);
	 * 
	 * frame.setSize(300, jl.size() * 30 + 60);
	 * 
	 * for (int i = 0; i < jl.size(); i++) {
	 * 
	 * jl.get(i).setBounds(30, i * 40 + 30, 240, 30); frame.add(jl.get(i)); }
	 * 
	 * frame.setVisible(true);
	 * 
	 * } else {
	 * 
	 * JOptionPane.showMessageDialog(null, "No Game has Created yet"); } }
	 */

	private boolean existUsername(String username) {

		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getUsername().equals(username))
				return true;
		}
		return false;
	}

	private boolean isCorrectUserAndPass(String username, String password) {

		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getUsername().equals(username))
				if (accounts.get(i).getPassword().equals(password))
					return true;
		}
		return false;
	}

	private String getNameOfAccount(String username) {

		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getUsername().equals(username))
				return accounts.get(i).getName();
		}
		return null;
	}

	public int witchAccount(String username) {

		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getUsername().equals(username)) {
				return i;
			}
		}

		return 0;
	}

	public boolean isExistGame(String username) {

		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getFirstPlayerAccount().getUsername()
					.equals(username))
				return true;
		}
		return false;
	}

	public int witchGame(String username) {

		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getFirstPlayerAccount().getUsername()
					.equals(username))
				return i;
		}
		return 0;
	}

	public boolean isCorrectUsernameAndPasswordForGame(String username,
			String password) {

		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getFirstPlayerAccount().getUsername()
					.equals(username))
				if (games.get(i).getPassword().equals(password))
					return true;
		}
		return false;
	}

}
