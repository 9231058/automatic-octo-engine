import netcalc.marshaler.TokenMarshaler;
import netcalc.ui.CommandLineClientUI;

public class Main {

	public static void main(String[] args) {
		new CommandLineClientUI(new TokenMarshaler()).run();
	}

}
