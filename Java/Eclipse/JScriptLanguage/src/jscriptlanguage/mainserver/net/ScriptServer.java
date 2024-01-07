package jscriptlanguage.mainserver.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScriptServer extends Thread {

	private ServerSocket serverSocket;
	private ExecutorService executor;

	public ScriptServer() {
		executor = Executors.newCachedThreadPool();
		try {
			serverSocket = new ServerSocket(1373);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			Socket socket = serverSocket.accept();
			executor.submit(new ScriptHandler(socket));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
