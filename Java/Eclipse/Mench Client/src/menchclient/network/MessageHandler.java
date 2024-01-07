package menchclient.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import menchclient.domain.Message;

public class MessageHandler implements Runnable {

	private Socket socket;
	private Message message;

	public MessageHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			message = new Message(reader.readLine(), socket.getInetAddress()
					.getHostAddress());
			System.err.println(message.getIp());
			System.err.println("I get Message " + message.getMessage());
			socket.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public Message getMessage() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
		return message;
	}
}
