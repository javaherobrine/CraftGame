package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class Client {
	Socket client;
	public Client() {
	}
	/**
	 * ����ָ���ķ�����
	 * @param url ����������������ip��ַ�����ַ�����ʾ
	 * @param port �������˿�
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void linkServer(String url,int port) throws UnknownHostException, IOException {
		this.client=new Socket(url,port);
		this.client.setKeepAlive(true);
	}
	/**
	 * �Ͽ��Է�����������
	 * @throws IOException
	 */
	public void disconnection() throws IOException {
		this.client.shutdownOutput();
		this.client.shutdownInput();
		this.client.close();
	}
	/**
	 * https://raw.githubusercontent.com/SpinyOwl/repo/snapshots/org/liquidengine/legui/2.1.0/legui-2.1.0.pom
	 */
}
