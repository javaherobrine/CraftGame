package io.github.javaherobrine.net;
import java.io.*;
public class InputThread extends Thread implements Closeable,AutoCloseable{
	TCPInputStream in;
	private byte[] now=null;
	boolean live=true;
	@Override
	/**
	 * 结束线程并关闭流
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
	 * 命令输入线程开始一次输入操作
	 */
	public void ready() {
		interrupt();
	}
	public InputThread(TCPInputStream in) {
		this.in=in;
	}
	/**
	 * 读取数据的时候会给数据加锁，此操作会在读取数据之后返回（也有加锁）
	 * @return 数据
	 */
	public byte[] get() {
		synchronized(now){
			return now;
		}
	}
}
