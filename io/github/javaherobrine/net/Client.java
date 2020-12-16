package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import io.github.javaherobrine.net.event.*;
import io.github.javaherobrine.ioStream.*;
public class Client implements Closeable{
	Socket soc;
	InputStream is;
	OutputStream os;
	ObjectInput in;
	ObjectOutput out;
	public ShakeHandsMessage msg=new ShakeHandsMessage();
	public Client(Socket soc) throws IOException{
		this.soc=soc;
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
				msg.connected=true;
				msg.format=allFormats[i];
				msg.status=TransmissionStatus.ACCEPTED;
				msg.id=IOUtils.byte4ToInt(is.readNBytes(4), 0);
				return;
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
		Client c=new Client(new Socket(host,port));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(c.os,"UTF-8"));
		bw.write(TransmissionFormat.RECONNECT.toString());
		return c;
	}
	public EventObject recevieEvent() throws IOException{
		try {
			EventObject obj=(EventObject)in.readObject();
			obj.content.recvExec();
			return obj;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}
