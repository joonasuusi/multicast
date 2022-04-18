package multi;

import java.nio.charset.StandardCharsets;
import java.io.*;
import java.net.*;

public class Multicast {
	
	public static void main(String[] args) {
		String msg = "hello";
		int port = 42000;
		 InetAddress mcastaddr = null;
		try {
			mcastaddr = InetAddress.getByName("239.0.0.1");
		} catch (UnknownHostException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		 InetSocketAddress group = new InetSocketAddress(mcastaddr, port);
		 NetworkInterface netIf = null;
		try {
			netIf = NetworkInterface.getByName("bge0");
		} catch (SocketException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 MulticastSocket s = null;
		try {
			s = new MulticastSocket(42000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		 try {
			s.joinGroup(group, netIf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
		 DatagramPacket hi = new DatagramPacket(msgBytes, msgBytes.length,
		                                        group);
		 try {
			s.send(hi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 // get their responses!
		 byte[] buf = new byte[1000];
		 DatagramPacket recv = new DatagramPacket(buf, buf.length);
		 try {
			s.receive(recv);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //...
		 // OK, I'm done talking - leave the group...
		 try {
			s.leaveGroup(group, netIf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
