package io.github.javaherobrine.net.event;
import io.github.javaherobrine.net.*;
public class DisconnectEvent extends EventContent{
	{
		type=EventType.NETWORK_EVENT;
		eid=0;
	}
	public static final DisconnectEvent DISCONNECT=new DisconnectEvent();
	@Override
	public void sendExec(Client c) {
		System.exit(0);
	}
	@Override
	public void recvExec() {
		Server.thisServer.clients.remove(getSourceClient().msg.id);
	}
}
