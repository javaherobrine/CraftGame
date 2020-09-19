package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class TCPOutputStream extends FilterOutputStream{
	DataOutputStream dos;
	public TCPOutputStream(OutputStream out) {
		super(out);
		dos=new DataOutputStream(out);
	}
	public TCPOutputStream(Socket soc) throws IOException{
		this(soc.getOutputStream());
	}
	public void writePacket(byte[] data) throws IOException {
		dos.writeInt(data.length);
		write(data);
	}
}
