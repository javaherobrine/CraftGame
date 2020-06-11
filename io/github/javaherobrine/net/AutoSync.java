package io.github.javaherobrine.net;
import java.io.*;
public class AutoSync<T> extends Thread{
	PipedInputStream piped=new PipedInputStream();
	private volatile boolean online=true;
	public void run() {
		try {
			ObjectInputStream ois=new ObjectInputStream(piped);
			while(true) {
				if(online) {
					ois.readObject();
				}else {
					try {
						Thread.sleep(Long.MAX_VALUE);
					} catch (InterruptedException e) {}
				}
			}
		} catch (IOException | ClassNotFoundException e) {}
		}
	public AutoSync() {}
	public void online() {
		online=true;
		interrupt();
	}
	public void offline() {
		online=false;
	}
}
