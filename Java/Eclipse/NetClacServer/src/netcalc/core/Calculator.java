package netcalc.core;

import netcalc.domain.Command;

public class Calculator {
	public static Integer evaluate(Command command, Integer value1,
			Integer value2) {
		if (command == Command.PLUS) {
			return value1.intValue() + value2.intValue();
		}
		if (command == Command.MINUS) {
			return value2.intValue() - value1.intValue();
		}
		if (command == Command.DIV) {
			return value2.intValue() / value1.intValue();
		}
		if (command == Command.MULT) {
			return value1.intValue() * value2.intValue();
		}
		if (command == Command.EXP) {
			return (int) Math.pow(value2.intValue(), value1.intValue());
		}
		return null;
	}

}
