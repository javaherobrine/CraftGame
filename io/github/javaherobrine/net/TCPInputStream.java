package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class TCPInputStream extends FilterInputStream{
	DataInputStream ois;
	public TCPInputStream(InputStream in) throws IOException{
		super(in);
	}
	public TCPInputStream(Socket soc) throws IOException{
		this(soc.getInputStream());
	}
	public byte[] readPacket() throws IOException {
		return in.readNBytes(ois.readInt());
	}
}
