package menchclient.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MenchConfig {

	private static MenchConfig instance;
	private Properties properties;

	public static MenchConfig getInstance() {
		if (instance == null) {
			instance = new MenchConfig();
		}
		return instance;
	}

	private MenchConfig() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream("config/mench.conf.xml"));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public String getIP() {
		return properties.getProperty("Server_IP", "127.0.0.1");
	}
}
