package io.github.javaherobrine.net;
import java.io.*;
@Deprecated
public class TCPInputStream extends FilterInputStream{
	public DataProcessor dataproc;
	public TCPInputStream(InputStream in) {
		this(in,PlainDataProcessor.DEFAULT_PROCESSOR);
	}
	public TCPInputStream(InputStream in,DataProcessor proc) {
		super(in);
		dataproc=proc;
	}
	public byte[] readData() throws IOException, InterruptedException {
		return dataproc.read(this);
	}
}