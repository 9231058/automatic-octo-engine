package tee.marshaller.xmlmarshaller;

import java.util.HashMap;

public class Item {
	private String name;
	private String data = "";
	private HashMap<String, String> attrs;

	public Item(String name) {
		this.name = name;
		attrs = new HashMap<String, String>();
	}

	public void addAttr(String key, String value) {
		attrs.put(key, value);
	}

	public boolean contains(String key) {
		return attrs.containsKey(key);
	}

	public String getAttr(String key) {
		return attrs.get(key);
	}

	public String getName() {
		return name;
	}

	public int getAttrSize() {
		return attrs.size();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data.trim();
	}
}
