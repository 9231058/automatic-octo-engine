package netcalc.net;

import netcalc.domain.Command;

public interface Network {
	Integer evaluate(Command command, Integer value1, Integer value2);
}
