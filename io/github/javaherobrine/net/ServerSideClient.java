package io.github.javaherobrine.net;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.Map;

import io.github.javaherobrine.net.event.EventContent;
import io.github.javaherobrine.net.event.Events;
import io.github.javaherobrine.net.event.OtherEvent;
public abstract class ServerSideClient extends Client {
	public Server server;
	public ServerSideClient(Socket soc,Server s) throws IOException {
		super(soc);
		this.server=s;
	}
	public void sendEvent(EventContent event) throws IOException {
		event.index=msg.id;
		event.serverSendExec();
		out.writeObject(event);
	}
	public EventContent receiveEvent() throws IOException {
		try {
			EventContent obj=null;
			if(in instanceof ObjectInputStream) {
				obj=(EventContent)in.readObject();
				obj.c=this;
			}else {
				Map m=(Map)in.readObject();
				obj=Events.EVENTS_BEAN.list.get((int)((Map)m).get("eid")).getConstructor(Client.class).newInstance(this);
				if(obj instanceof OtherEvent) {
					((OtherEvent)obj).content=((OtherEvent)obj).initContent((Map)((Map)m.get("content")));
				}
			}
			obj.serverRecvExec();
			return obj;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			return null;
		}
	}
}
