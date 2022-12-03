package io.github.javaherobrine.net.event;
import java.util.concurrent.*;
public class EventHandler extends Thread{
	BlockingQueue<EventContent> bus=new LinkedBlockingQueue<>();
	public void run() {
		while(true) {
			try {
				bus.take().recvExec();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void push(EventContent c) {
		try {
			bus.put(c);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
