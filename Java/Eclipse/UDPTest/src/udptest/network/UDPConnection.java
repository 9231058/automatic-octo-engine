package udptest.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection {

	DatagramSocket udp;
	InetAddress ip;

	public UDPConnection(byte[] ipAddress) throws SocketException,
			UnknownHostException {
		udp = new DatagramSocket();
		ip = InetAddress.getByAddress(ipAddress);
	}

	public void DNSRequest() {
		byte[] dnsRequestHeader = new byte[] { 0x75, 0x17, 0x01, 0x00, 0x01,
				0x00, 0x00, 0x00 };
		byte[] dnsRequestQuery = new byte[] { 0x00, 0x0f };
	}

}
