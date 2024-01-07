package musicsorter.musicwriter;

import java.io.FileNotFoundException;
import java.util.Formatter;

import musicsorter.Music;
import musicsorter.MusicWriter;

public class DefaultMusicWriter implements MusicWriter {

	@Override
	public void write(Music[] musics) {
		Formatter cout = null;
		try {
			cout = new Formatter("defualtSortedMusics.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		cout.format("%d", musics.length);
		for (int i = 0; i < musics.length; i++) {
			cout.format("%d.%s", i, musics.toString());
		}
		cout.close();
	}
}
