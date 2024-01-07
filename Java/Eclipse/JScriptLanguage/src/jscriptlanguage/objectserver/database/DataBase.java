package jscriptlanguage.objectserver.database;

import java.util.HashMap;
import java.util.UUID;

public class DataBase {
	private static DataBase instance = null;

	private HashMap<UUID, Object> objects;

	public static DataBase getInstance() {
		if (instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	private DataBase() {
		objects = new HashMap<>();
	}

	public void addObject(UUID id, Object object) {
		objects.put(id, object);
	}

	public Object getObject(UUID id) {
		return objects.get(id);
	}
}
