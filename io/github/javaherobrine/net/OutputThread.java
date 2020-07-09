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
	BufferedOutputStream os;
	private ObjectOutputStream thisOos;
	private OutputStream now;
	public volatile boolean canWrite=true;
	StreamType type;
	private Socket thisSoc=null;
	public boolean flag=true;
	public volatile byte[] outputData=null;
	public void run() {
		while(flag) {
			if(outputData!=null) {
				try {
					synchronized(now) {
						now.write(IOUtils.intToByte4(outputData.length));
						now.flush();
						now.write(outputData);
						now.flush();
					}
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
	public void init() throws IOException {
		thisOos=new ObjectOutputStream(os);
	}
	/**
	 * ����ָ�����׽��ֵ��������������߳�
	 * @param soc �׽���
	 * @throws IOException ���getOutputStream����
	 */
	public OutputThread(Socket soc) throws IOException {
		this(soc.getOutputStream(),StreamType.SOCKET);
		thisSoc=soc;
	}
	/**
	 * ����ָ��������������߳�
	 * @param os �����
	 * @throws IOException 
	 */
	public OutputThread(OutputStream os,StreamType type) throws IOException {
		this.type=type;
		this.os=new BufferedOutputStream(os);
	}
	public void object(Object obj) throws IOException {
		thisOos.writeObject(obj);
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
		outputData=data;
		interrupt();
	}
	/**
	 * ֱ��д����
	 * @param data д������
	 * @throws IOException ���д�����
	 */
	public synchronized void write0(byte[] data) throws IOException {
		synchronized(now) {
			now.write(data);
			now.flush();
		}
	}
	public void setOutputStream(OutputStream os) {
		synchronized(now) {
			now=os;
		}
	}
	public void defaultOutputStream() {
		setOutputStream(os);
	}
	@Override
	public void close() throws IOException {
		flag=false;
		switch(type) {
		case SOCKET:
			thisSoc.shutdownOutput();
			break;
		case FILE:
			os.close();
			break;
		}
	}
}
