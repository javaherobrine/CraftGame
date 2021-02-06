package io.github.javaherobrine.net.sync;
import java.io.*;
import io.github.javaherobrine.net.*;
import io.github.javaherobrine.net.event.*;
public class ClientSideSynchronizeImpl extends SynchronizeImpl implements Runnable{
	Client c;
	boolean server;
	@Override
	public void offline() throws IOException {
		c.sendEvent(OfflineEvent.OFFLINE_EVENT);
	}
	@Override
	public void online() throws IOException {
		c.sendEvent(OnlineEvent.ONLINE_EVENT);
	}
	public ClientSideSynchronizeImpl(Client c,boolean server){
		if(c.msg.id==-1) {
			throw new IllegalArgumentException("Client connects failed or not support");
		}
		this.c=c;
		this.server=server;
	}
	public class ServertSideSynchronizeImpl extends SynchronizeImpl implements Runnable{
		public ServertSideSynchronizeImpl(){
			if(!server) {
				throw new RuntimeException("Cannnot create an impl",new IllegalAccessException("This impl isn't a server"));
			}
		}
		@Override
		public void run() {
			while(true) {
				try {
					c.recevieEvent();
				} catch (IOException e) {
					//TODO Connection reset or pipe broken and so on.You must do something to process this error
					break;
				}
			}
		}
		@Override
		public void offline() throws Exception {
			ClientSideSynchronizeImpl.this.offline();
		}
		@Override
		public void online() throws Exception {
			ClientSideSynchronizeImpl.this.online();
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
			} catch (IOException e) {
				//TODO Connection reset or pipe broken and so on.You must do something to process this error
				break;
			}
		}
	}
	public Client getClient() {
		return c;
	}
}
