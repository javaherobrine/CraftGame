package io.github.javaherobrine.net;
import java.io.*;
public class InputThread extends Thread implements Closeable,AutoCloseable{
	TCPInputStream in;
	private byte[] now=null;
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
				try {
					synchronized(now) {
						now=in.readData();
					}
				} catch (IOException e) {
				}
			} catch (InterruptedException e) {
			}
		}
	}
	public void ready() {
		interrupt();
	}
	public InputThread(TCPInputStream in) {
		this.in=in;
	}
	public byte[] get() {
		synchronized(now){
			return now;
		}
	}
}
