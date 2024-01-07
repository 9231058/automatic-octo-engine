package httpanalyzer.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HttpRequest {

	private Socket httpSocket;
	private String ServerApp;
	private String state;
	private ArrayList<HttpCookie> cookies;

	public HttpRequest(String addr) throws UnknownHostException, IOException {
		httpSocket = new Socket(addr, 80);
		System.err.println("Connect");
		cookies = new ArrayList<>();
		getContent();
	}

	private void getContent() throws IOException {
		OutputStream ostream = httpSocket.getOutputStream();
		String command = "HEAD / HTTP/1.1\n";
		ostream.write(command.getBytes(StandardCharsets.US_ASCII));
		command = "Host:" + httpSocket.getInetAddress().getHostName() + "\n";
		ostream.write(command.getBytes(StandardCharsets.US_ASCII));
		command = "\n";
		ostream.write(command.getBytes(StandardCharsets.US_ASCII));
		ostream.flush();
		System.out.println("Respond from " + httpSocket.getInetAddress() + ":");
		InputStream istream = httpSocket.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(istream, StandardCharsets.US_ASCII));
		String responseString = "";
		do {
			responseString = bufferedReader.readLine();
			analyze(responseString);
		} while (responseString.length() != 0);
	}

	void analyze(String response) {
		String splitedResponse[] = response.split(" ");
		switch (splitedResponse[0]) {
		case "HTTP/1.1":
			state = splitedResponse[2];
			break;
		case "Server:":
			ServerApp = splitedResponse[1];
			break;
		case "Set-Cookie:":
			cookies.addAll(HttpCookie.parse(response));
			break;
		case "Cache-Control:":
			break;
		}
	}

	public String getServerApp() {
		return ServerApp;
	}

	public String getState() {
		return state;
	}

	public HttpCookie getCookie(int index) {
		return cookies.get(index);
	}

	public int getCookiesSize() {
		return cookies.size();
	}
}
