package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import io.github.javaherobrine.ioStream.*;
/**
 * ����߳�
 * @author Java_Herobrine
 */
public class OutputThread extends Thread implements ServerClientInterface,Closeable,AutoCloseable{
	OutputStream os;
	StreamType type;
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
	 */
	public OutputThread(OutputStream os,StreamType type) {
		this.os=os;
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
		case BYTE_ARRAY:
			ByteArrayOutputStream baos=(ByteArrayOutputStream)os;
			baos.reset();
			os=baos;
		case FILE:
			os.close();
			break;
		}
	}
}
