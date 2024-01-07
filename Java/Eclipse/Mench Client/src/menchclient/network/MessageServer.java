package menchclient.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import menchclient.conf.MenchConfig;

public class MessageServer implements Runnable {

	private static MessageServer instance;
	private ExecutorService executer;
	private ArrayList<MessageHandler> handlers;
	private volatile boolean run = true;

	public static MessageServer getInstance() {
		if (instance == null) {
			instance = new MessageServer();
		}
		return instance;
	}

	private MessageServer() {
		executer = Executors.newCachedThreadPool();
		handlers = new ArrayList<MessageHandler>();
	}

	@Override
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(13742);
			while (run) {
				Socket socket = serverSocket.accept();
				MessageHandler handler = new MessageHandler(socket);
				executer.submit(handler);
				handlers.add(handler);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void shutdown() {
		run = false;
	}

	public MessageHandler getHandler() {
		try {
			if (handlers.size() > 0)
				return handlers.remove(0);
		} catch (ArrayIndexOutOfBoundsException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public void sendMessage(String message) {
		try {
			Socket socket = new Socket(MenchConfig.getInstance().getIP(), 13741);
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println(message);
			writer.flush();
			socket.close();
			socket.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}