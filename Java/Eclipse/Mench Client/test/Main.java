import menchclient.gui.MainFrame;
import menchclient.network.MessageServer;

public class Main {
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
		new Thread(MessageServer.getInstance(), "Messager Server").start();
	}
}
