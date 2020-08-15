package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.zip.*;
import io.github.javaherobrine.ioStream.*;
/**
 * ���������߳�
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
	 * ����ָ���������������߳�
	 * @param is Դ������
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
	 * ����ָ�����׽��ֵ������������߳�
	 * @param soc �׽���
	 * @throws IOException ���getInputStream�����쳣
	 */
	public InputThread(Socket soc) throws IOException {
		this(soc.getInputStream());
		this.soc=soc;
	}
	/**
	 * ��������״̬
	 */
	/**
	 * ��һ���ֽڵ�������
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
	 * �߳����壬IO����������
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
	 * ����ָ����inputLength��off������
	 * @return ����
	 * @throws IOException
	 */
	private byte[] getData() throws IOException {
		while(data==null);
		return data;
	}
	/**
	 * ͨ�����̵߳���������ָ�����ȵ�����
	 * @param length ���볤��
	 * @return ����������
	 */
	public synchronized byte[] readNBytes(int length){
		ready=true;
		data=null;
		interrupt();
		byte[] temp=getData0();
		return temp;
	}
	/**
	 * ��ȡ��������
	 * @return ����
	 */
	private synchronized byte[] getData0() {
		while(data==null) {}
		return data;
	}
	/**
	 * ����������ʹ�ã���Ȼ�������������ȡ����
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
