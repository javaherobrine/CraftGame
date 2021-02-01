package io.github.javaherobrine.net;
import java.io.*;
@Deprecated
public class InputThread extends Thread implements Closeable,AutoCloseable{
	TCPInputStream in;
	boolean keep=true;
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
			if(keep) {
				try {
					sleep(Long.MAX_VALUE);
				} catch (InterruptedException e) {}
			}
			try {
				synchronized(now) {
					now=in.readData();
				}
			} catch (IOException | InterruptedException ee) {
			}
		}
	}
	public void ready() {
		interrupt();
	}
	public InputThread(TCPInputStream in) {
		this.in=in;
	}
	public synchronized byte[] get() {
		synchronized(now){
			return now;
		}
	}
	public synchronized void keepTransmitting() {
		keep=false;
		ready();
	}
	public synchronized void blockTransmitting() {
		keep=true;
	}
}
