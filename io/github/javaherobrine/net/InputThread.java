package io.github.javaherobrine.net;
import java.io.*;
public class InputThread extends Thread implements Closeable,AutoCloseable{
	TCPInputStream in;
	private byte[] now=null;
	boolean live=true;
	@Override
	/**
	 * �����̲߳��ر���
	 */
	public void close() throws IOException {
		in.close();
		live=false;
	}
	@Override
	public void run() {
		while(live) {
			try {
				sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
				try {
					synchronized(now) {
						now=in.readData();
					}
				} catch (IOException ee) {
				}
			}
		}
	}
	/**
	 * ���������߳̿�ʼһ���������
	 */
	public void ready() {
		interrupt();
	}
	public InputThread(TCPInputStream in) {
		this.in=in;
	}
	/**
	 * ��ȡ���ݵ�ʱ�������ݼ������˲������ڶ�ȡ����֮�󷵻أ�Ҳ�м�����
	 * @return ����
	 */
	public byte[] get() {
		synchronized(now){
			return now;
		}
	}
}
