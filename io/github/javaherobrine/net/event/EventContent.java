package io.github.javaherobrine.net.event;
import io.github.javaherobrine.net.*;
import java.util.*;
public abstract class EventContent {
	public Object content;
	public static EventType type;
	public int index;
	public static int eid;
	public Client getSourceClient() {
		return Server.thisServer.clients.get(index);
	}
	public abstract void sendExec(Client c);
	public abstract void recvExec();
	public abstract EventContent initFromMaps(Map map);
}
