package io.github.javaherobrine.net.event;
import io.github.javaherobrine.net.*;
public class OfflineEvent extends EventContent {
	public static final EventContent OFFLINE_EVENT=new OfflineEvent();
	{
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
}
