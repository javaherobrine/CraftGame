package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;

import io.github.javaherobrine.ioStream.IOUtils;
/**
 * 创建输入线程
 * @author Java_Herobrine
 */
public class InputThread extends Thread implements ServerClientInterface,Closeable,AutoCloseable{
	private InputStream is;
	public StreamType type;
	public boolean flag=true;
	public volatile int inputLength=-1;
	public volatile byte[] data=null;
	public volatile int off=0;
	public volatile int mark=0;
	public volatile ByteArrayOutputStream ais=new ByteArrayOutputStream();
	/**
	 * 根据指定的输入流创建线程
	 * @param is 源输入流
	 */
	public InputThread(InputStream is,StreamType type) {
		this.type=type;
		this.is=is;
	}
	/**
	 * 根据指定的套接字的输入流创建线程
	 * @param soc 套接字
	 * @throws IOException 如果getInputStream发生异常
	 */
	public InputThread(Socket soc) throws IOException {
		this(soc.getInputStream(),StreamType.SOCKET);
	}
	/**
	 * 重置所有状态
	 */
	private void reset(){
		mark=0;
		off=0;
		inputLength=-1;
		ais.reset();
	}
	/**
	 * 读一个字节到缓冲区
	 * @throws IOException
	 */
	private void read() throws IOException {
		ais.write(is.read());
		mark++;
	}
	@Override
	/**
	 * 线程主体，IO都在这里了
	 */
	public void run() {
		while(flag) {
			if(inputLength==-1) {
				try {
					sleep(Long.MAX_VALUE);
				} catch (InterruptedException e) {
					if(inputLength==-1) {
						continue;
					}
				}
			}
			try {
				if(mark>=inputLength){
					data=getData();
					if(mark==inputLength) {
						reset();
					}
				}
				read();
			}catch(SocketException e) {
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据指定的inputLength和off读数据
	 * @return 数据
	 * @throws IOException
	 */
	private byte[] getData() throws IOException {
		ByteArrayInputStream bis=new ByteArrayInputStream(ais.toByteArray());
		off+=inputLength;
		byte[] bs=new byte[off];
		bis.read(bs);
		return bs;
	}
	/**
	 * 通过该线程的输入流读指定长度的数据
	 * @param length 输入长度
	 * @return 读到的数据
	 */
	public synchronized byte[] readNBytes(int length){
		inputLength=length;
		data=null;
		interrupt();
		byte[] temp=getData0();
		inputLength=-1;
		return temp;
	}
	/**
	 * 读取所有数据
	 * @return 数据
	 */
	private synchronized byte[] getData0() {
		while(data==null) {}
		inputLength=-1;
		return data;
	}
	/**
	 * 仅限网络编程使用，不然会无限阻塞或读取错误
	 * @return
	 * @throws IOException
	 */
	public byte[] readAPacket() throws IOException{
		return readNBytes(IOUtils.byte4ToInt(readNBytes(4),0));
	}
	@Override
	public void close() throws IOException {
		flag=false;
		switch(type) {
		case SOCKET:
			break;
		case BYTE_ARRAY:
			ByteArrayInputStream bais=(ByteArrayInputStream)is;
			bais.reset();
			is=bais;
		case FILE:
			is.close();
			break;
		}
	}
}
