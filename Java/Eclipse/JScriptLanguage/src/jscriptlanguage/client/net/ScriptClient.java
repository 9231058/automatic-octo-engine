package jscriptlanguage.client.net;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ScriptClient {

	public ScriptClient(String script, String IP) {
		try {
			Socket socket = new Socket(IP, 1373);
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			printWriter.println(script);
			printWriter.println();
			printWriter.flush();
			socket.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
