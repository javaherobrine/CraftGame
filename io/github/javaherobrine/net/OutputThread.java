package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import io.github.javaherobrine.ioStream.*;
public class OutputThread extends Thread implements Closeable,AutoCloseable{
	Socket soc;
	@Override
	public void close() throws IOException {
	}
	public OutputThread(Socket soc) {
		this.soc=soc;
	}
}
