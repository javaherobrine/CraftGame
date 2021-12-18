package io.github.javaherobrine.net.event;
import io.github.javaherobrine.net.*;
import java.io.*;
public abstract class EventContent implements Serializable{
	public int index;
	public static int eid;
	public transient Client c;
	public EventContent(Client c) {
		this.c=c;
	}
	public abstract void sendExec();
	public abstract void recvExec();
}
