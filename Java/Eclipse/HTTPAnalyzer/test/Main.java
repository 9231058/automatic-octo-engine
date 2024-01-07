import httpanalyzer.gui.HttpFrame;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		HttpFrame httpFrame = new HttpFrame();
		httpFrame.setVisible(true);
	}

}
