package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.zip.*;
import io.github.javaherobrine.ioStream.*;
/**
 * 创建输入线程
 * @author Java_Herobrine
 */
public class InputThread extends Thread implements ServerClientInterface,Closeable,AutoCloseable{
	TCPInputStream is;
	private ObjectInputStream socketOis;
	private boolean init;
	public volatile boolean ready=false;
	ObjectOutputStream oos;
	Socket soc=null;
	PipedOutputStream piped=new PipedOutputStream();
	public boolean flag=true;
	public volatile byte[] data=null;
	public volatile int off=0;
	public static void main(String[] args) {
		System.out.println(Long.MAX_VALUE);
	}
	/**
	 * 根据指定的输入流创建线程
	 * @param is 源输入流
	 * @throws IOException 
	 */
	
	public InputThread(InputStream is) throws IOException {
		this.is=new TCPInputStream(new BufferedInputStream(is));
	}
	public OutputThread getOutput() throws IOException {
		if(soc!=null) {
			return new OutputThread(soc);
		}
		return null;
	}
	/**
	 * 根据指定的套接字的输入流创建线程
	 * @param soc 套接字
	 * @throws IOException 如果getInputStream发生异常
	 */
	public InputThread(Socket soc) throws IOException {
		this(soc.getInputStream());
		this.soc=soc;
	}
	/**
	 * 重置所有状态
	 */
	/**
	 * 读一个字节到缓冲区
	 * @throws IOException
	 */
	private void read() throws IOException {
		
	}
	public void init() {
		try {
			socketOis=new ObjectInputStream(is);
			init=true;
		} catch (IOException e) {
			init=false;
		}
	}
	public void initPiped() {
		try {
			oos=new ObjectOutputStream(piped);
		} catch (IOException e) {
		}
	}
	@Override
	/**
	 * 线程主体，IO都在这里了
	 */
	public void run() {
		while(flag) {
			if(!ready) {
				try {
					sleep(Long.MAX_VALUE);
				} catch (InterruptedException e) {
					if(!ready) {
						continue;
					}
				}
			}
			try {
				data=getData();
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
		while(data==null);
		return data;
	}
	/**
	 * 通过该线程的输入流读指定长度的数据
	 * @param length 输入长度
	 * @return 读到的数据
	 */
	public synchronized byte[] readNBytes(int length){
		ready=true;
		data=null;
		interrupt();
		byte[] temp=getData0();
		return temp;
	}
	/**
	 * 读取所有数据
	 * @return 数据
	 */
	private synchronized byte[] getData0() {
		while(data==null) {}
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
	public Object object() throws ClassNotFoundException, IOException {
		return socketOis.readObject();
	}
	@Override
	public void close() throws IOException {
		is.close();
	}
}
