package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.*;
import io.github.javaherobrine.ioStream.IOUtils;
public class Server extends ServerClientInterface implements Closeable {
	public static HashMap<String,Socket> sockets=new HashMap<>();
	public int serverPort;
	public DatagramSocket socket;
	public ServerSocket s;
	public boolean isTCP;
	/**
	 * ����쿴�����������ˣ�
	 * 
	 * @throws IOException
	 */
	public void open() throws IOException {
		if (isTCP) {
			this.s = new ServerSocket(this.serverPort);
		} else {
			socket = new DatagramSocket(serverPort);
		}
	}
	public Server(int port, boolean isTCP) {
		this.serverPort = port;
	}
	public synchronized void linkClient() {
		
	}
	/**
	 * Ŷ��������������
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.s.close();
	}
	static Socket socket0;
	/**
	 * �����ݵķ�ʽ���޸�NetStatus.outputDataΪ����<br />
	 * �������ݵķ�ʽ���޸�NetStatus.inputLengthΪ���ճ��ȣ�Ȼ��NetStatus.getData();��������ɺ�NetStatus.inputLength��Ϊ-1��NetStatus.data��Ϊnull
	 * @param args 2333
	 * @throws IOException 2333
	 */
	public static void main(String[] args) throws IOException{
		ServerSocket server=new ServerSocket(8888);
		System.out.println("Server created");
		socket0=server.accept();
		socket0.setKeepAlive(true);
		System.out.println("someone is linking this server");
		InputThread it=new InputThread(socket0);
		it.start();
		System.out.println("Get InputStream Object");
		OutputThread ot=new OutputThread(socket0);
		ot.start();
	}
	public void linkToUDP(InetAddress ip, int port) throws IOException {
		byte[] data = new byte[4];
		DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress(ip, port));
		packet.setData(Constants.version.getBytes());
		socket.send(packet);
	}
}