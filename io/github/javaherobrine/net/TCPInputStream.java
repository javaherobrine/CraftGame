package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class TCPInputStream extends FilterInputStream{
	ObjectInputStream ois;
	public TCPInputStream(InputStream in) throws IOException{
		super(in);
		init();
	}
	public TCPInputStream(Socket soc) throws IOException{
		this(soc.getInputStream());
	}
	public byte[] readPacket() throws IOException {
		return in.readNBytes(ois.readInt());
	}
	public void init() throws IOException{
		ois=new ObjectInputStream(in);
	}
}
