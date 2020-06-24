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
	InputStream is;
	ObjectOutputStream oos;
	private ObjectInputStream socketOis;
	private boolean init;
	private GZIPInputStream socketGZ;
	private ObjectInputStream socketGZIPOis;
	PipedOutputStream piped=new PipedOutputStream();
	public StreamType type;
	public boolean flag=true;
	public volatile int inputLength=-1;
	public volatile byte[] data=null;
	public volatile int off=0;
	public volatile int mark=0;
	public volatile ByteArrayOutputStream ais=new ByteArrayOutputStream();
	public static void main(String[] args) {
		System.out.println(Long.MAX_VALUE);
	}
	/**
	 * ����ָ���������������߳�
	 * @param is Դ������
	 * @throws IOException 
	 */
	public InputThread(InputStream is,StreamType type) throws IOException {
		this.type=type;
		this.is=is;
	}
	/**
	 * ����ָ�����׽��ֵ������������߳�
	 * @param soc �׽���
	 * @throws IOException ���getInputStream�����쳣
	 */
	public InputThread(Socket soc) throws IOException {
		this(soc.getInputStream(),StreamType.SOCKET);
	}
	/**
	 * ��������״̬
	 */
	private void reset(){
		mark=0;
		off=0;
		inputLength=-1;
		ais.reset();
	}
	/**
	 * ��һ���ֽڵ�������
	 * @throws IOException
	 */
	private void read() throws IOException {
		ais.write(is.read());
		mark++;
	}
	public void init() {
		try {
			socketOis=new ObjectInputStream(is);
			socketGZ=new GZIPInputStream(is);
			socketGZIPOis=new ObjectInputStream(socketGZ);
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
	 * ����ָ����inputLength��off������
	 * @return ����
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
	 * ͨ�����̵߳���������ָ�����ȵ�����
	 * @param length ���볤��
	 * @return ����������
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
	 * ��ȡ��������
	 * @return ����
	 */
	private synchronized byte[] getData0() {
		while(data==null) {}
		inputLength=-1;
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
	@Override
	public void close() throws IOException {
		flag=false;
		switch(type) {
		case SOCKET:
			break;
		case FILE:
			is.close();
			break;
		}
	}
}
