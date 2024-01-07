package home.parham.process.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuildProcess {
	public void buid() {
		ProcessBuilder builder = new ProcessBuilder("ls", "-la");
		try {
			Process process = builder.start();
			BufferedReader iStream = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			while (true) {
				String read = iStream.readLine();
				if (read == null)
					break;
				System.out.println(read);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
