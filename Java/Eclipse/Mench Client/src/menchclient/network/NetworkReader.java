package menchclient.network;

public class NetworkReader {

	public String receiveMessage() {
		MessageHandler handler;
		while (true) {
			if ((handler = MessageServer.getInstance().getHandler()) != null) {
				return handler.getMessage().getMessage();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}
}
