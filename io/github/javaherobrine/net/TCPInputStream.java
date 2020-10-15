package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class TCPInputStream extends FilterInputStream{
	public DataProcessor dataproc;
	public TCPInputStream(InputStream in) {
		this(in,PlainDataProcessor.DEFAULT_PROCESSOR);
	}
	public TCPInputStream(InputStream in,DataProcessor proc) {
		super(in);
		dataproc=proc;
	}
	public TCPInputStream(Socket soc) throws IOException{
		this(soc.getInputStream());
	}
	public TCPInputStream(Socket soc,DataProcessor proc) throws IOException {
		this(soc.getInputStream(),proc);
	}
	public byte[] readData() throws IOException {
		return dataproc.read(this);
	}
}