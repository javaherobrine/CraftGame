package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import io.github.javaherobrine.ioStream.*;
/**
 * 输出线程
 * @author Java_Herobrine
 */
public class OutputThread extends Thread implements ServerClientInterface {
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
					outputData=null;
				}catch(SocketException e) {
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				try {
					sleep(Long.MAX_VALUE);
				} catch (InterruptedException e) {
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
	/**
	 * 向该线程的输出流写数据
	 * @param data 写的数据
	 */
	public synchronized void write(byte[] data) {
		outputData=data;
		interrupt();
	}
}
