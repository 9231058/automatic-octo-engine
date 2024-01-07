package menchserver.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NetworkReader {

	String ip;
	BufferedReader br;

	public NetworkReader(String ip) {

		this.ip = ip;
		try {
			br = new BufferedReader(new InputStreamReader(
					new Socket(ip, 1374).getInputStream()));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public String receiveMessage() {

		try {
			return br.readLine();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return null;
	}
}