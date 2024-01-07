package netcalc.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import netcalc.marshaler.Marshaler;

public class TCPNetworkHandler implements Runnable {

	private Socket socket;
	private Marshaler marshaler;

	public TCPNetworkHandler(Socket socket) {
		this.socket = socket;
		marshaler = new Marshaler();
	}

	@Override
	public void run() {
		try {
			String eol = "\n";
			InputStream istream = socket.getInputStream();
			String request = "";
			int c;
			while ((c = istream.read()) != eol.charAt(0)) {
				request = request + (char) c;
			}
			Integer value = marshaler.request(request);
			System.err.println(value);
			OutputStream ostream = socket.getOutputStream();
			ostream.write(value.toString().getBytes(StandardCharsets.US_ASCII));
			ostream.write(eol.getBytes(StandardCharsets.US_ASCII));
			socket.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}
}
