package musicsorter;

import java.util.Arrays;

import musicsorter.musicreader.XMLMusicReader;

public class MusicManneger implements Runnable {
	private static MusicManneger musicManneger;

	private MusicReader musicReader = new XMLMusicReader();
	private Music[] musics;

	public MusicManneger() {
		musics = musicReader.read();
	}

	synchronized public static MusicManneger getInstance() {
		if (musicManneger == null) {
			musicManneger = new MusicManneger();
		}
		return musicManneger;
	}

	@Override
	public void run() {
		Arrays.sort(musics);
		System.out.println(Arrays.toString(musics));
	}
}
