package jscriptlanguage.mainserver.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import jscriptlanguage.mainserver.noteanalyzer.NoteAnalyzer;

public class ScriptHandler implements Runnable {

	private Socket socket;

	public ScriptHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		ArrayList<String> script = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String lastLine = null;
			do {
				lastLine = bufferedReader.readLine();
				script.add(lastLine);
			} while (lastLine.length() != 0);
			socket.close();
			new NoteAnalyzer(script);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
