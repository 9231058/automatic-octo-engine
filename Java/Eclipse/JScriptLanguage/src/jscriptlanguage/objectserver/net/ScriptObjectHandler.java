package jscriptlanguage.objectserver.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.UUID;

import jscriptlanguage.objectserver.database.DataBase;

public class ScriptObjectHandler implements Runnable {

	private Socket socket;

	public ScriptObjectHandler(Socket socket) {
		this.socket = socket;
	}

	public UUID newObject(String objectType) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		Object object = Class.forName("classes." + objectType).newInstance();
		UUID id = UUID.randomUUID();
		DataBase.getInstance().addObject(id, object);
		return id;
	}

	public Object runMethod(UUID id, String methodName, Object[] objects)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Object mainObject = DataBase.getInstance().getObject(id);
		Class<?>[] classes = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) {
			classes[i] = objects[i].getClass();
		}
		Object returnObject = mainObject.getClass()
				.getMethod(methodName, classes).invoke(mainObject, objects);
		return returnObject;
	}

	@Override
	public void run() {
		try {
			BufferedReader istream = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter ostream = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			String request = istream.readLine();
			switch (request) {
			case "NewObject":
				try {
					UUID id = newObject(istream.readLine());
					ostream.println("OK");
					ostream.println(id);
					ostream.flush();
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException exception) {
					ostream.println("ERROR");
					ostream.flush();
				}
				break;
			case "RunMethod":
				try {
					UUID id = UUID.fromString(istream.readLine());
					String methodName = istream.readLine();
					int size = Integer.parseInt(istream.readLine());
					Object[] objects = new Object[size];
					ObjectOutputStream oostream = new ObjectOutputStream(
							socket.getOutputStream());
					ObjectInputStream oistream = new ObjectInputStream(
							socket.getInputStream());
					oostream.flush();
					for (int i = 0; i < size; i++) {
						objects[i] = oistream.readObject();
					}
					Object object = runMethod(id, methodName, objects);
					ostream.println("OK");
					ostream.flush();
					oostream.writeObject(object);
					oostream.flush();
				} catch (IOException | ClassNotFoundException
						| IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException
						| SecurityException exception) {
					exception.printStackTrace();
					ostream.flush();
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
