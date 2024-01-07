package netcalc.marshaler;

import netcalc.core.Calculator;
import netcalc.domain.Command;

public class Marshaler {
	public Integer request(String string) {
		String[] splitStrings = string.split(" ");
		Command command = Command.valueOf(splitStrings[0]);
		Integer value1 = Integer.parseInt(splitStrings[1]);
		Integer value2 = Integer.parseInt(splitStrings[2]);
		return Calculator.evaluate(command, value1, value2);
	}
}
