import menchserver.command.CommandReader;

public class Main {
	public static void main(String[] args) {
		new Thread(new CommandReader(), "Command Reader").start();
	}
}
