package musicsorter.musicreader;

import java.util.Scanner;

import musicsorter.Music;
import musicsorter.MusicReader;

public class DefaultMusicReader implements MusicReader {

	@Override
	public Music[] read() {
		Scanner cin = new Scanner(this.getClass()
				.getResource("defaultMusicList.txt").toString());
		int number = cin.nextInt();
		Music[] musics = new Music[number];
		for (int i = 0; i < number; i++) {
			String name = cin.next();
			String artrist = cin.next();
			String album = cin.next();
			int playCount = cin.nextInt();
			musics[i] = new Music(number, name, artrist, album, playCount);
		}
		cin.close();
		return null;
	}
}
