package io.github.javaherobrine.net.event;
import io.github.javaherobrine.net.*;
import java.io.*;
import io.github.javaherobrine.*;
public class DisconnectEvent extends EventContent{
	public DisconnectEvent(Client c) {
		super(c);
	}
	@Override
	public void sendExec() {
		Constants.REGISTERED_NETWORK_EVENT_LISTENER.forEach(listener->{
			listener.disconnect(c);
		});
	}
	@Override
	public void recvExec() {
		if(confirmed) {
			try {
				c.close();
			} catch (IOException e) {}
		}
	}
	@Override
	public void serverSendExec() {
		//do nothing
	}
	@Override
	public void serverRecvExec()  {
		Constants.REGISTERED_NETWORK_EVENT_LISTENER.forEach(listener->{
			listener.disconnect(c);
		});
		confirmed=true;
		try {
			c.sendEvent(this);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
