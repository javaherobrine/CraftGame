package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class Client {
	Socket client;
	public Client() {
	}
	/**
	 * 连接指定的服务器
	 * @param url 服务器的域名或者ip地址，用字符串表示
	 * @param port 服务器端口
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void linkServer(String url,int port) throws UnknownHostException, IOException {
		this.client=new Socket(url,port);
		this.client.setKeepAlive(true);
	}
	/**
	 * 断开对服务器的连接
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
