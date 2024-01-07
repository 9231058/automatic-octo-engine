package netcalc.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import netcalc.marshaler.Marshaler;

public class CommandLineClientUI implements Runnable {

	BufferedReader bufferedReader;
	Marshaler marshaler;

	public CommandLineClientUI(Marshaler marshaler) {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		this.marshaler = marshaler;
	}

	@Override
	public void run() {
		System.out.println("Welcome to network calculator ......");
		while (true) {
			System.out.print("> ");
			try {
				String string = bufferedReader.readLine();
				try {
					CommandLineCommand command = CommandLineCommand
							.valueOf(string.toUpperCase());
					runCommand(command);
				} catch (IllegalArgumentException exception) {
					System.out.println(marshaler.request(string));
				}
			} catch (IOException e) {
				System.out.println("ERROR");
				e.printStackTrace();
			}
		}
	}

	private void runCommand(CommandLineCommand command) {
		if (command == CommandLineCommand.EXIT
				|| command == CommandLineCommand.QUIT) {
			System.exit(0);
		} else if (command == CommandLineCommand.HELP) {
			CommandLineFunction.showHelp();
		}
	}

}
