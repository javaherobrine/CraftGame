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
	 * 仅限craftgame使用
	 * @param playerName
	 * @throws IOException
	 */
	public synchronized static void disconnection(String playerName) throws IOException {
		sockets.remove(playerName);
	}
	public static void main(String[] args) throws IOException {
		Server s=new Server(80);
		s.open();
		Socket soc=s.s.accept();
		System.out.println("accepted HTTP protocol link");
		InputThread it=new InputThread(soc);
		it.start();
		System.out.println(it.is.readAllBytes());
		OutputThread ot=new OutputThread(soc);
		ot.start();
		ot.write("Hello <br /> user".getBytes());
		System.out.println("write");
	}
}