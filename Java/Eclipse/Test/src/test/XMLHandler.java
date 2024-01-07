package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLHandler {

	public XMLHandler() {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader xmlEventReader = null;
		try {
			xmlEventReader = xmlInputFactory
					.createXMLEventReader(new FileInputStream("Test.xml"));
		} catch (FileNotFoundException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (xmlEventReader.hasNext()) {
			XMLEvent event = null;
			try {
				event = xmlEventReader.nextEvent();
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart().equals("Table")) {
					@SuppressWarnings("unchecked")
					Iterator<Attribute> iterator = startElement.getAttributes();
					while (iterator.hasNext()) {
						Attribute attribute = iterator.next();
						System.out.println(attribute.getName().getLocalPart()
								.toString());
						if (attribute.getName().toString().equals("Row")) {
							System.out.println(attribute.getValue());
						}
						if (attribute.getName().toString().equals("Column")) {
							System.out.println(attribute.getValue());
						}
					}
				} else if (startElement.getName().getLocalPart().equals("Row")) {
					try {
						event = xmlEventReader.nextEvent();
					} catch (XMLStreamException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (event.isCharacters()) {
						System.out.println((event.asCharacters()).toString()
								.trim());
					}
				}
			}
		}
	}
}
