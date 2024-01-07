package menchclient.domain;

public class Message {

	private String message;
	private  String ip;
	
	public Message(String message, String ip) {
		
		this.message = message;
		this.ip = ip;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getIp() {
		return ip;
	}
}
