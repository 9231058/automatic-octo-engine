package jscriptlanguage.objectserver.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScriptObjectServer extends Thread {

	private ExecutorService executorService;
	private ServerSocket serverSocket;

	public ScriptObjectServer(int port) {
		executorService = Executors.newCachedThreadPool();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			Socket socket = serverSocket.accept();
			executorService.submit(new ScriptObjectHandler(socket));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
