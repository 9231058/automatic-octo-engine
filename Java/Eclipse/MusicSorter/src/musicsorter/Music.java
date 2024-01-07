package musicsorter;

/**
 * @author parham
 * 
 */
public class Music implements Comparable<Music> {

	private int index;
	private String name;
	private String artrist;
	private String album;
	private int playCount;

	public String getAlbum() {
		return album;
	}

	public String getArtrist() {
		return artrist;
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public int getPlayCount() {
		return playCount;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setArtrist(String artrist) {
		this.artrist = artrist;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}

	public Music(int index, String name, String artrist, String album,
			int playCount) {
		super();
		this.index = index;
		this.name = name;
		this.artrist = artrist;
		this.album = album;
		this.playCount = playCount;
	}

	@Override
	public int compareTo(Music o) {
		if (this.getArtrist().equalsIgnoreCase(o.getArtrist())) {
			if (this.getAlbum().equalsIgnoreCase(o.getAlbum())) {
				return this.index - o.index;
			}
			return this.getAlbum().compareToIgnoreCase(o.getAlbum());
		}
		return this.getArtrist().compareToIgnoreCase(o.getArtrist());
	}

	@Override
	public String toString() {
		return String.format("%d %s %s %s %d", index, name, artrist, album,
				playCount);
	}

}