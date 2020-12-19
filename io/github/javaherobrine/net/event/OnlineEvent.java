package io.github.javaherobrine.net.event;
import java.util.Map;

import io.github.javaherobrine.net.*;
public class OnlineEvent extends EventContent {
	public static final EventObject ONLINE_EVENT=new EventObject(new OnlineEvent());
	static {
		type=EventType.NETWORK_EVENT;
		eid=1;
	}
	@Override
	public void sendExec(Client c) {
		c.msg.status=TransmissionStatus.ACCEPTED;
	}
	@Override
	public void recvExec() {
		sendExec(getSourceClient());
	}
	@Override
	public EventContent initFromMaps(Map map) {
		return ONLINE_EVENT.content;
	}
}
