package jscriptlanguage.mainserver.objects;

import java.util.HashMap;
import java.util.UUID;

public class ObjectCollector {

	private HashMap<UUID, ScriptObject> objects;

	public ObjectCollector() {
		objects = new HashMap<>();
	}

	public void addToObjects(UUID id, ScriptObject object) {
		objects.put(id, object);
	}

	public void changeAnObject(String name) {

	}
}