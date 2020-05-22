package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import io.github.javaherobrine.ioStream.*;
public class OutputThread extends Thread {
	OutputStream os;
	public void run() {
		while(true) {
			if(!NetStatus.isWrite) {
			}
			if(NetStatus.outputData!=null&&!NetStatus.sent) {
				NetStatus.sent=true;
				try {
					os.write(IOUtils.intToByte4(NetStatus.outputData.length));
					os.flush();
					os.write(NetStatus.outputData);
					os.flush();
				}catch(SocketException e) {
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				NetStatus.sent=false;
			}
		}
	}
	public OutputThread(Socket soc) throws IOException {
		this(soc.getOutputStream());
	}
	public OutputThread(OutputStream os) {
		this.os=os;
	}
	public void write(byte[] data) {
		NetStatus.isWrite=true;
		NetStatus.outputData=data;
	}
}
