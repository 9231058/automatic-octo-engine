import java.net.SocketException;
import java.net.UnknownHostException;

import udptest.network.UDPConnection;

public class Main {

	/**
	 * @param args
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws SocketException,
			UnknownHostException {
		UDPConnection connection = new UDPConnection(
				new byte[] { 127, 0, 0, 1 });
		while (true) {

		}
	}

}
