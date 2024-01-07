package tee.base;

import java.io.FileInputStream;

import tee.marshaller.Marshaller;

public class TEEBase extends Thread {

	private Marshaller marshaller;
	private String path = "tee.conf.xml";

	public TEEBase() {
		super("TEE Base Thread");
	}

	@Override
	public void run() {
		try {
			BaseConfig.getInstance().loadFromXML(new FileInputStream(path));
			String marshallerClass = BaseConfig.getInstance().getProperty(
					"Marshaller", "tee.marshaller.xmlmarshaller.XMLMarshaller");
			marshaller = (Marshaller) Class.forName(marshallerClass)
					.newInstance();
			marshaller.validate();
			marshaller.marshal();
		} catch (Exception exception) {
			exception.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}
