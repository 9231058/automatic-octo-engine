package menchserver.domain;

public class Account { // DONE

	private String name;
	private String username;
	private String password;
	private String ip;
	
	public Account(String name, String username, String password) {
		
		this.name = name;
		this.username = username;
		this.password = password;
		ip = "";
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setIP(String ip) {
		this.ip = ip;
	}
	
	public String getIP() {
		return ip;
	}
}
