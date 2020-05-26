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
	public ServerSocket s;
	/**
	 * ����쿴�����������ˣ�
	 * @throws IOException
	 */
	public void open() throws IOException {
		this.s = new ServerSocket(this.serverPort);
	}
	/**
	 * ����ָ���Ķ˿ڴ���������
	 * @param port �˿ں�
	 */
	public Server(int port) {
		this.serverPort = port;
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