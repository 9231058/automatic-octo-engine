package tee.marshaller.xmlmarshaller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import tee.domain.exceptions.MarshallerException;

public class Reader {

	private String path;

	public Reader(String path) {
		this.path = path;
	}

	@SuppressWarnings("unchecked")
	public List<Item> read() throws MarshallerException {
		List<Item> items = new ArrayList<Item>();
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream(path);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					Item item = new Item(startElement.getName().getLocalPart());
					Iterator<Attribute> attributes = startElement
							.getAttributes();
					while (attributes.hasNext()) {
						Attribute attribute = attributes.next();
						item.addAttr(attribute.getName().getLocalPart(),
								attribute.getValue());
					}
					if (eventReader.peek().isCharacters()) {
						item.setData(eventReader.peek().asCharacters()
								.getData());
					}
					items.add(item);
				}
			}

		} catch (FileNotFoundException exception) {
			throw new MarshallerException("FileNotFound", exception);
		} catch (XMLStreamException exception) {
			throw new MarshallerException("IOError", exception);
		}
		return items;
	}

}
