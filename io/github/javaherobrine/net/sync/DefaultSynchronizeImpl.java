package io.github.javaherobrine.net.sync;
import java.io.*;
import io.github.javaherobrine.net.*;
import io.github.javaherobrine.net.event.*;
public class DefaultSynchronizeImpl extends SynchronizeImpl implements Runnable{
	Client c;
	public DefaultSynchronizeImpl(Client c){
		if(c.msg.id==-1) {
			throw new IllegalArgumentException("Client connects failed or not support");
		}
		this.c=c;
	}
	@Override
	public void run() {
		while(true) {
			try {
				c.receiveEvent();
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
