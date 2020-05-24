package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.ioStream.IOUtils;
public class Server implements Closeable {
	public static HashMap<String,Socket> sockets=new HashMap<>();
	/**
	 * ServerClientInterface[0]Ϊ��������ServerClientInterface[1]Ϊ�����
	 */
	public static HashMap<Socket,ServerClientInterface[]> threads=new HashMap<>();
	public int serverPort;
	/**
	 * ΪUDPԤ��
	 */
	public DatagramSocket socket;
	public ServerSocket s;
	/**
	 * ����쿴�����������ˣ�
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
	 * Ŷ��������������
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.s.close();
	}
	/**
	 * ����craftgameʹ��
	 * @param playerName
	 * @throws IOException
	 */
	public static void disconnection(String playerName) throws IOException {
		sockets.remove(playerName);
	}
}