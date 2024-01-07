package jscriptlanguage.mainserver.objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class ScriptObject {
	private String IP;
	private int port;
	private UUID id;

	public ScriptObject(String ObjectType) {
		try {
			Socket socket = new Socket(IP, port);
			BufferedReader istream = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter ostream = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			ostream.println("NewObjcet");
			ostream.println(ObjectType);
			ostream.flush();
			String respond = istream.readLine();
			switch (respond) {
			case "OK":
				id = UUID.fromString(istream.readLine());
				break;
			case "ERROR":
				break;
			}
			socket.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public Object runMethod(String methodName, Object... objects) {
		try {
			Socket socket = new Socket(IP, port);
			BufferedReader istream = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter ostream = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			ostream.println("RunMethod");
			ostream.println(id);
			ostream.println(methodName);
			ostream.println(objects.length);
			ostream.flush();
			ObjectOutputStream oostream = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream oistream = new ObjectInputStream(
					socket.getInputStream());
			oostream.flush();
			for (int i = 0; i < objects.length; i++) {
				oostream.writeObject(objects[i]);
			}
			oostream.flush();
			String respond = istream.readLine();
			switch (respond) {
			case "OK":
				oistream.readObject();
				break;
			case "ERROR":
				break;
			}
			socket.close();
		} catch (IOException | ClassNotFoundException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public UUID getID() {
		return id;
	}
}
