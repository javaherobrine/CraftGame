package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import io.github.javaherobrine.ioStream.*;
public class Client implements Closeable{
	Socket soc;
	InputStream is;
	OutputStream os;
	ObjectInput in;
	ObjectOutput out;
	ShakeHandsMessage msg=new ShakeHandsMessage();
	public Client(Socket soc) throws IOException{
		this.soc=soc;
		this.is=soc.getInputStream();
		this.os=soc.getOutputStream();
	}
	public void shakeHands() throws IOException {
		boolean accepted=false;
		TransmissionFormat[] allFormats=TransmissionFormat.values();
		for(int i=0;i<allFormats.length;i++) {
			os.write((allFormats[i].toString()+"\n").getBytes("UTF-8"));
			os.flush();
			int code=IOUtils.byte4ToInt(is.readNBytes(4), 0);
			if(code==0) {
				accepted=true;
				msg.connected=true;
				msg.format=allFormats[i];
				msg.status=TransmissionStatus.ACCEPTED;
				msg.id=IOUtils.byte4ToInt(is.readNBytes(4), 0);
				break;
			}
		}
	}
	@Override
	public void close() throws IOException {
		soc.close();
	}
}
