package io.github.javaherobrine.net;
import java.util.concurrent.*;
import java.io.*;
public class EventHandler extends Thread implements Closeable{
	private BlockingQueue<EventContent> q=new LinkedBlockingQueue<>();
	private boolean disconnected;
	@Override
	public void run() {
		while(!disconnected) {
			try {
				q.take().recvExec(true);
			} catch (InterruptedException e) {
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void push(EventContent c) throws InterruptedException{
		if(c==null) {
			return;
		}
		if(disconnected) {
			throw new IllegalStateException("");
		}
		q.put(c);
	}
	@Override
	public void close() {
		disconnected=true;
		interrupt();
	}
}