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
	Socket soc=null;
	public volatile boolean canWrite=true;
	StreamType type;
	private Socket thisSoc=null;
	public boolean flag=true;
	public volatile byte[] outputData=null;
	public void run() {
		while(flag) {
			if(outputData!=null) {
				try {
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
	public InputThread getInput() throws IOException {
		if(soc!=null) {
			return new InputThread(soc);
		}
		return null;
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
			return;
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
		os.write(data);
		os.flush();
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
