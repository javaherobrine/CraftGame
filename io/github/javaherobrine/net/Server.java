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
	 * 妈妈快看，服务器开了！
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
	 * 哦不，服务器关了
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.s.close();
	}
	static Socket socket0;
	/**
	 * 发数据的方式：修改NetStatus.outputData为数据<br />
	 * 接收数据的方式：修改NetStatus.inputLength为接收长度，然后NetStatus.getData();，接收完成后NetStatus.inputLength设为-1，NetStatus.data设为null
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