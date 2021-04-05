package io.github.javaherobrine.net.event;
import io.github.javaherobrine.net.*;
import java.io.*;
public abstract class EventContent implements Serializable{
	public EventType type;
	public int index;
	public int eid;
	public Client getSourceClient() {
		return Server.thisServer.clients.get(index);
	}
	public abstract void sendExec(Client c);
	public abstract void recvExec();
}
