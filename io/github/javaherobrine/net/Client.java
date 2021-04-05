package io.github.javaherobrine.net;
import java.io.*;
import java.util.*;
import java.net.*;
import io.github.javaherobrine.net.event.*;
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
			if(client) sendEvent(DisconnectEvent.DISCONNECT);
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
					/*if(msg.format==TransmissionFormat.OBJECT) {
						out=new ObjectOutputStream(os);
						in=new ObjectInputStream(is);
					}else */if(msg.format==TransmissionFormat.JSON){
						out=new JSONOutputStream(os);
						in=new JSONInputStream(is);
					}
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
	public void sendEvent(EventContent event) throws IOException {
		event.index=msg.id;
		event.sendExec(this);
		out.writeObject(event);
	}
	public static Client reconnectToServer(String host,int port) throws IOException{
		Client c=new Client(new Socket(host,port),true);
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(c.os,"UTF-8"));
		bw.write(TransmissionFormat.RECONNECT.toString());
		return c;
	}
	public EventContent receiveEvent() throws IOException {
		try {
			EventContent obj=null;
			if(in instanceof ObjectInputStream) {
				obj=(EventContent)in.readObject();
			}else {
				System.out.println("prepare to recv event");
				Map m=(Map)in.readObject();
				System.out.println("JSON received:"+m==null);
				obj=Events.EVENTS_BEAN.list.get((int)((Map)m).get("eid")).newInstance();
				if(obj instanceof OtherEvent) {
					((OtherEvent)obj).content=((OtherEvent)obj).initContent((Map)((Map)m.get("content")));
				}
				System.out.println(obj);
			}
			obj.recvExec();
			return obj;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
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
	public int hashCode() {
		return soc.hashCode();
	}
}
