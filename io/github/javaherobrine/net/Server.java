package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
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
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.s.close();
	}
	public static void main(String[] args) throws IOException{
		ServerSocket server=new ServerSocket(8888);
		System.out.println("Server created");
		Socket socket0=server.accept();
		socket0.setKeepAlive(true);
		System.out.println("someone is linking this server");
		InputThread it=new InputThread(socket0);
		it.start();
		System.out.println("Get InputStream Object");
		System.out.println(new String(it.readNBytes(IOUtils.byte4ToInt(it.readNBytes(4),0))));
		OutputThread ot=new OutputThread(socket0);
		ot.write("sdacds;lzchofosldss".getBytes());
		ot.start();
	}
}