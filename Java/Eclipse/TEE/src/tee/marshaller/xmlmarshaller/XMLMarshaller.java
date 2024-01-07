package tee.marshaller.xmlmarshaller;

import tee.base.BaseConfig;
import tee.domain.exceptions.MarshallerException;
import tee.marshaller.Marshaller;

public class XMLMarshaller implements Marshaller {

	private String path;
	private Reader reader;

	public XMLMarshaller() {
		path = BaseConfig.getInstance().getProperty("TasksXMLPath", "");
		reader = new Reader(path);
	}

	@Override
	public void validate() throws MarshallerException {
		if (path.equals("")) {
			throw new MarshallerException("Invalid TasksXMLPath");
		}
		new Validator(reader.read()).validate();
	}

	@Override
	public void marshal() {
	}

}
