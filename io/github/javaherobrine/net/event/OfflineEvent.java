package io.github.javaherobrine.net.event;
import java.util.*;
import io.github.javaherobrine.net.*;
public class OfflineEvent extends EventContent {
	public static final EventObject OFFLINE_EVENT=new EventObject(new OfflineEvent());
	static{
		type=EventType.NETWORK_EVENT;
		eid=2;
	}
	@Override
	public void sendExec(Client c) {
		c.msg.status=TransmissionStatus.PAUSED;
	}
	@Override
	public void recvExec() {
		sendExec(getSourceClient());	
	}
	@Override
	public EventContent initFromMaps(Map map) {
		return OFFLINE_EVENT.content;
	}
}
