package simillarping;

import java.io.IOException;
import java.net.InetAddress;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] pingAddressString = null;
		try {
			pingAddressString = args[0].split("\\.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("you do not enter the host ip address ...");
			System.exit(1);
			e.printStackTrace();
		}
		try {
			InetAddress pingAddress = InetAddress.getByAddress(new byte[] {
					(byte) Integer.parseInt(pingAddressString[0]),
					(byte) Integer.parseInt(pingAddressString[1]),
					(byte) Integer.parseInt(pingAddressString[2]),
					(byte) Integer.parseInt(pingAddressString[3]) });
			System.out.println("Connecting to host : " + args[0]);
			System.out.println(pingAddress.isReachable(5000) ? "is reachable"
					: "is not reachable in 5s");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
