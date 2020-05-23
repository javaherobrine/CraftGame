package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import io.github.javaherobrine.ioStream.*;
public class OutputThread extends Thread {
	OutputStream os;
	public volatile byte[] outputData=null;
	public void run() {
		while(true) {
			if(outputData!=null) {
				try {
					os.write(IOUtils.intToByte4(outputData.length));
					os.flush();
					os.write(outputData);
					os.flush();
				}catch(SocketException e) {
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public OutputThread(Socket soc) throws IOException {
		this(soc.getOutputStream());
	}
	public OutputThread(OutputStream os) {
		this.os=os;
	}
	public synchronized void write(byte[] data) {
		outputData=data;
	}
}
