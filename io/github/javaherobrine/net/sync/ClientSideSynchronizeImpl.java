package io.github.javaherobrine.net.sync;
import java.io.*;
import io.github.javaherobrine.net.*;
import io.github.javaherobrine.net.event.*;
public class ClientSideSynchronizeImpl extends SynchronizeImpl implements Runnable{
	Client c;
	@Override
	public void offline() throws IOException {
		c.sendEvent(OfflineEvent.OFFLINE_EVENT);
	}
	@Override
	public void online() throws IOException {
		c.sendEvent(OnlineEvent.ONLINE_EVENT);
	}
	public ClientSideSynchronizeImpl(Client c){
		if(c.msg.status==TransmissionStatus.CONTINUE) {
			throw new IllegalArgumentException("Client connects failed or not support");
		}
		this.c=c;
	}
	public class ServertSideSynchronizeImpl extends Thread{
		@Override
		public void run() {
			while(true) {
				try {
					EventContent content=c.recevieEvent().content;
					content.recvExec();
				} catch (IOException e) {}
			}
		}
	}
	@Override
	public void run() {
		while(true) {
			try {
				EventContent content=c.recevieEvent().content;
				if(!(content instanceof DisconnectEvent)) {
					content.recvExec();
				}
			} catch (IOException e) {}
		}
	}
}