package tee.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class BaseConfig {
	private static BaseConfig instance;
	private Properties properties;

	public static BaseConfig getInstance() {
		if (instance == null) {
			instance = new BaseConfig();
		}
		return instance;
	}

	private BaseConfig() {
		properties = new Properties();
	}

	public void loadFromXML(InputStream inputStream)
			throws InvalidPropertiesFormatException, IOException {
		properties.loadFromXML(inputStream);
	}

	public String getProperty(String key, String defaulValue) {
		return properties.getProperty(key, defaulValue);
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
