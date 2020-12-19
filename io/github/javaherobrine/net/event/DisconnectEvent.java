package io.github.javaherobrine.net.event;
import java.util.*;
import io.github.javaherobrine.net.*;
public class DisconnectEvent extends EventContent{
	static {
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
	@Override
	public EventContent initFromMaps(Map map) {
		return DISCONNECT;
	}
}
