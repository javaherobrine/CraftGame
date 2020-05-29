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
	public ServerSocket s;
	/**
	 * 妈妈快看，服务器开了！
	 * @throws IOException
	 */
	public void open() throws IOException {
		this.s = new ServerSocket(this.serverPort);
	}
	/**
	 * 根据指定的端口创建服务器
	 * @param port 端口号
	 */
	public Server(int port) {
		this.serverPort = port;
	}
	/**
	 * 哦不，服务器关了
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.s.close();
	}
	/**
	 * 使用ServerThread创建的套接字才可以使用此方法
	 */
	public synchronized static void disconnection(String playerName) throws IOException {
		ServerClientInterface[] ss=threads.remove(sockets.remove(playerName));
		ss[0].close();
		ss[1].close();
	}
}