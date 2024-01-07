package netcalc.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPNetworkServer implements Runnable {

	private ServerSocket serverSocket;
	private ExecutorService executorService;
	private volatile boolean isAlive;

	public TCPNetworkServer() {
		executorService = Executors.newCachedThreadPool();
		try {
			serverSocket = new ServerSocket(1373);
			isAlive = true;
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (isAlive) {
			try {
				Socket socket = serverSocket.accept();
				executorService.submit(new TCPNetworkHandler(socket));
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}

	public void shutdown() {
		isAlive = false;
		try {
			serverSocket.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
