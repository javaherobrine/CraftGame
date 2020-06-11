package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import io.github.javaherobrine.ioStream.*;
import java.util.zip.*;
/**
 * ����߳�
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
	 * ����ָ�����׽��ֵ��������������߳�
	 * @param soc �׽���
	 * @throws IOException ���getOutputStream����
	 */
	public OutputThread(Socket soc) throws IOException {
		this(soc.getOutputStream(),StreamType.SOCKET);
	}
	/**
	 * ����ָ��������������߳�
	 * @param os �����
	 * @throws IOException 
	 */
	public OutputThread(OutputStream os,StreamType type) throws IOException {
		this.type=type;
		this.os=new GZIPOutputStream(os);
	}
	/**
	 * ����̵߳����������Ԥ���TCP��ʽд����
	 * @param data д������
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
	 * ֱ��д����
	 * @param data д������
	 * @throws IOException ���д�����
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
