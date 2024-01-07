package menchclient;

import javax.swing.JFrame;

public class Core {

	JFrame frame;
	Panel p;

	public Core(int turn, int numberOfPlayers) {

		frame = new JFrame();
		frame.setSize(1050, 850);
		
		p = new Panel(new Player[numberOfPlayers], turn);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(p);

		frame.setVisible(true);
	}
}