package menchserver.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkWriter {

	String ip;
	PrintWriter pw;

	public NetworkWriter(String ip) {
		this.ip = ip;
		try {
			pw = new PrintWriter(new Socket(ip, 1374).getOutputStream());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		pw.println(message);
		pw.flush();
	}
}