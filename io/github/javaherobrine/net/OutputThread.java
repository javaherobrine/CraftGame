package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import io.github.javaherobrine.ioStream.*;
import java.util.zip.*;
/**
 * 输出线程
 * @author Java_Herobrine
 */
public class OutputThread extends Thread implements ServerClientInterface,Closeable,AutoCloseable{
	GZIPOutputStream os;
	private volatile boolean canWrite=true;
	StreamType type;
	public boolean flag=true;
	public volatile byte[] outputData=null;
	public void run() {
		while(flag) {
			if(outputData!=null) {
				try {
					System.out.println("stream locked");
					os.write(IOUtils.intToByte4(outputData.length));
					os.flush();
					os.write(outputData);
					os.flush();
					outputData=null;
					canWrite=true;
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
	/**
	 * 根据指定的套接字的输出流创建输出线程
	 * @param soc 套接字
	 * @throws IOException 如果getOutputStream出错
	 */
	public OutputThread(Socket soc) throws IOException {
		this(soc.getOutputStream(),StreamType.SOCKET);
	}
	/**
	 * 根据指定的输出流创建线程
	 * @param os 输出流
	 * @throws IOException 
	 */
	public OutputThread(OutputStream os,StreamType type) throws IOException {
		this.type=type;
		this.os=new GZIPOutputStream(os);
	}
	/**
	 * 向该线程的输出流按照预设的TCP格式写数据
	 * @param data 写的数据
	 * @throws IOException 
	 */
	public synchronized void write(byte[] data) throws IOException {
		if(type!=StreamType.SOCKET) {
			write0(data);
		}
		while(!canWrite) {}
		canWrite=false;
		System.out.println("data locked");
		outputData=data;
		interrupt();
	}
	/**
	 * 直接写数据
	 * @param data 写的数据
	 * @throws IOException 如果写入错误
	 */
	public synchronized void write0(byte[] data) throws IOException {
		os.write(data);
		os.flush();
	}
	@Override
	public void close() throws IOException {
		flag=false;
		switch(type) {
		case SOCKET:
			break;
		case FILE:
			os.close();
			break;
		}
	}
}
