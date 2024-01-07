package jscriptlanguage.mainserver.database;

import java.util.HashMap;
import java.util.UUID;

import jscriptlanguage.mainserver.objects.ObjectCollector;
import jscriptlanguage.mainserver.objects.ScriptObject;

public class DataBase {
	private static DataBase instance;

	private HashMap<String, Integer> values;
	private HashMap<String, String> strings;
	private HashMap<String, UUID> objects;
	private ObjectCollector collector;

	public static DataBase getInstance() {
		if (instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	private DataBase() {
		objects = new HashMap<String, UUID>();
		strings = new HashMap<String, String>();
		values = new HashMap<String, Integer>();
	}

	public void addValue(String name, Integer value) {
		values.put(name, value);
	}

	public void addString(String name, String string) {
		strings.put(name, string);
	}

	public void addObject(String name, String type) {
		ScriptObject object = new ScriptObject(type);
		collector.addToObjects(object.getID(), object);
		objects.put(name, object.getID());
	}
}
