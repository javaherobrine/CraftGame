package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.ioStream.IOUtils;
public class Server implements Closeable {
	public static HashMap<String,Socket> sockets=new HashMap<>();
	/**
	 * ServerClientInterface[0]为输入流，ServerClientInterface[1]为输出流
	 */
	public static HashMap<Socket,ServerClientInterface[]> threads=new HashMap<>();
	public int serverPort;
	/**
	 * 为UDP预留
	 */
	public DatagramSocket socket;
	public ServerSocket s;
	/**
	 * 妈妈快看，服务器开了！
	 * @throws IOException
	 */
	public void open() throws IOException {
		this.s = new ServerSocket(this.serverPort);
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
	/**
	 * 仅限craftgame使用
	 * @param playerName
	 * @throws IOException
	 */
	public static void disconnection(String playerName) throws IOException {
		sockets.remove(playerName);
	}
}