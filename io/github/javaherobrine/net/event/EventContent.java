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
	@Override
	public int hashCode() {
		return content.hashCode()*index*eid/type.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		try {
			return ((EventContent)obj).content.equals(content)&&((EventContent)obj).eid==eid&&((EventContent)obj).index==index;
		}catch(Exception e) {
			return false;
		}
	}
}
