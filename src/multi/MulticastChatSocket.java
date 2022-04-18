package multi;

import java.net.*;
import java.io.*;

public class MulticastChatSocket extends MulticastSocket {

	public MulticastChatSocket() throws IOException {
		super();
	}
	
	public MulticastChatSocket(int portti) throws IOException {
		super(portti);
	}
	
	public void receive(DatagramPacket p) throws IOException {
		super.receive(p);
		byte[] temp = p.getData();
		int pituus = temp[12];
		String s = new String(temp, 13, pituus);
		//return s;
	}
	
}
