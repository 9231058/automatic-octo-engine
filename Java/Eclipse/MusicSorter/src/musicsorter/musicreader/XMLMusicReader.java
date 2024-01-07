package musicsorter.musicreader;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import musicsorter.Music;
import musicsorter.MusicReader;

public class XMLMusicReader implements MusicReader {

	@Override
	public Music[] read() {
		int index = 0;
		ArrayList<Music> musics = new ArrayList<>();
		String name = "";
		String artrist = "";
		String album = "";
		int playCount = 0;
		try {
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			XMLEventReader xmlEventReader = xmlInputFactory
					.createXMLEventReader(this.getClass().getResourceAsStream(
							"XMLMusicList.xml"));
			while (xmlEventReader.hasNext()) {
				XMLEvent xmlEvent = xmlEventReader.nextEvent();
				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();
					if (startElement.getName().getLocalPart()
							.equalsIgnoreCase("Name")) {
						name = xmlEventReader.nextEvent().asCharacters()
								.toString().trim();
					}
					if (startElement.getName().getLocalPart()
							.equalsIgnoreCase("Artrist")) {
						artrist = xmlEventReader.nextEvent().asCharacters()
								.toString().trim();
					}
					if (startElement.getName().getLocalPart()
							.equalsIgnoreCase("Album")) {
						album = xmlEventReader.nextEvent().asCharacters()
								.toString().trim();
					}
					if (startElement.getName().getLocalPart()
							.equalsIgnoreCase("PlayCount")) {
						playCount = Integer.parseInt(xmlEventReader.nextEvent()
								.asCharacters().toString().trim());
					}
				}
				if (xmlEvent.isEndElement()) {
					EndElement endElement = xmlEvent.asEndElement();
					if (endElement.getName().getLocalPart()
							.equalsIgnoreCase("Track")) {
						musics.add(new Music(index, name, artrist, album,
								playCount));
						index++;
						name = "";
						artrist = "";
						album = "";
						playCount = 0;
					}
				}
			}
		} catch (XMLStreamException exception) {
			System.err.println("Something goes wrong ....");
		}
		Music[] arrayMusics = new Music[musics.size()];
		for (int i = 0; i < arrayMusics.length; i++) {
			arrayMusics[i] = musics.get(i);
		}
		return arrayMusics;
	}
}
