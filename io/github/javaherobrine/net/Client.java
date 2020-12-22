package io.github.javaherobrine.net;
import java.io.*;
import java.util.*;
import java.net.*;
import io.github.javaherobrine.net.event.*;
import io.github.javaherobrine.net.event.EventObject;
import io.github.javaherobrine.*;
import io.github.javaherobrine.net.sync.*;
import io.github.javaherobrine.ioStream.*;
public class Client implements Closeable{
	Socket soc;
	InputStream is;
	OutputStream os;
	ObjectInput in;
	ObjectOutput out;
	boolean client;
	Thread hook=new Thread(()->{
		try {
			if(client) sendEvent(new EventObject(DisconnectEvent.DISCONNECT));
			soc.close();
		} catch (IOException e) {}
	}) ;
	{
		Runtime.getRuntime().addShutdownHook(hook);
	}
	public ShakeHandsMessage msg=new ShakeHandsMessage();
	public Client(Socket soc,boolean client) throws IOException{
		this.soc=soc;
		this.client=client;
		this.is=soc.getInputStream();
		this.os=soc.getOutputStream();
	}
	public void shakeHands() throws IOException {
		TransmissionFormat[] allFormats=TransmissionFormat.values();
		for(int i=0;i<allFormats.length;i++) {
			os.write((allFormats[i].toString()+"\n").getBytes("UTF-8"));
			os.flush();
			int code=IOUtils.byte4ToInt(is.readNBytes(4), 0);
			if(code==0) {
				os.write((ModLoader.loader.toString()+"\n").getBytes("UTF-8"));
				code=IOUtils.byte4ToInt(is.readNBytes(4), 0);
				if(code==1) {
					msg.connected=true;
					msg.format=allFormats[i];
					msg.status=TransmissionStatus.ACCEPTED;
					msg.id=IOUtils.byte4ToInt(is.readNBytes(4), 0);
					msg.mods=ModLoader.loader.toString().split(",");
					return;
				}else if(code==-1) {
					break;
				}
			}else if(code==-10) {
				break;
			}
		}
		msg.connected=false;
		msg.format=TransmissionFormat.FINISH;
		msg.status=TransmissionStatus.CONTINUE;
		msg.id=-1;
	}
	@Override
	public void close() throws IOException {
		soc.close();
	}
	public void sendEvent(EventObject event) throws IOException {
		event.content.index=msg.id;
		event.content.sendExec(this);
		out.writeObject(event);
	}
	public static Client reconnectToServer(String host,int port) throws IOException{
		Client c=new Client(new Socket(host,port),true);
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(c.os,"UTF-8"));
		bw.write(TransmissionFormat.RECONNECT.toString());
		return c;
	}
	public EventObject recevieEvent() throws IOException {
		try {
			EventObject obj=null;
			if(in instanceof ObjectInputStream) {
				obj=(EventObject)in.readObject();
			}else {
				obj=new EventObject(Events.EVENTS_BEAN.list.get((int)((Map)in.readObject()).get("eid")).newInstance());
			}
			obj.content.recvExec();
			return obj;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new IOException(e);
		}
	}
	public ClientSideSynchronizeImpl getImpl() {
		return new ClientSideSynchronizeImpl(this, false);
	}
	@Override
	public void finalize() throws IOException{
		Runtime.getRuntime().removeShutdownHook(hook);
		soc.close();
	}
}
