package netcalc.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import netcalc.domain.Command;

public class TCPNetwork implements Network {

	private Socket socket;

	@Override
	public Integer evaluate(Command command, Integer value1, Integer value2) {
		try {
			createConnection();
			String dlim = " ";
			String eol = "\n";
			OutputStream ostream = socket.getOutputStream();
			ostream.write(command.toString()
					.getBytes(StandardCharsets.US_ASCII));
			ostream.write(dlim.getBytes(StandardCharsets.US_ASCII));
			ostream.write(value1.toString().getBytes(StandardCharsets.US_ASCII));
			ostream.write(dlim.getBytes(StandardCharsets.US_ASCII));
			ostream.write(value2.toString().getBytes(StandardCharsets.US_ASCII));
			ostream.write(eol.getBytes(StandardCharsets.US_ASCII));
			InputStream istream = socket.getInputStream();
			String answer = "";
			int c;
			while ((c = istream.read()) != eol.charAt(0)) {
				answer = answer + (char) c;
			}
			socket.close();
			return Integer.parseInt(answer);
		} catch (IOException | NumberFormatException exception) {
			exception.printStackTrace();
			return null;
		}
	}

	private void createConnection() throws IOException {
		socket = new Socket("127.0.0.1", 1373);
		socket.setSoTimeout(10000);
	}

}
