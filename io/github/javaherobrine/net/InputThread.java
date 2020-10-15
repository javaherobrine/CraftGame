package io.github.javaherobrine.net;
import java.io.*;
public class InputThread extends Thread implements Closeable,AutoCloseable{
	TCPInputStream in;
	boolean live=true;
	@Override
	public void close() throws IOException {
		in.close();
		live=false;
	}
	@Override
	public void run() {
		while(live) {
			try {
				sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
			}
		}
	}
	public void ready() {
		interrupt();
	}
	public InputThread(TCPInputStream in) {
		this.in=in;
		start();
	}
}
