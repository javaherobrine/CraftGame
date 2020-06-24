package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class Client {
	private Socket client;
	private boolean isConnection;
	private OutputThread ot;
	private InputThread it;
	public Client() {
	}
	/**
	 * 连接指定的服务器
	 * @param url 服务器的域名或者ip地址，用字符串表示
	 * @param port 服务器端口
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void autoLink(String url,int port) throws UnknownHostException, IOException {
		this.client=new Socket(url,port);
		this.client.setKeepAlive(true);
		ot=new OutputThread(this.client);
		ot.start();
		it=new InputThread(this.client);
		it.start();
		isConnection=true;
	}
	/**
	 * 断开对服务器的连接
	 * @throws IOException
	 */
	public void disconnection() throws IOException {
		if(!isConnection) {
			throw new SocketException("无法断开连接：连接不存在");
		}
		it.close();
		ot.close();
		this.client.shutdownOutput();
		this.client.shutdownInput();
		this.client.close();
	}
	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, ExecutionException {
		NetUtils.smtp("smtp.qq.com", "javaherobrine@qq.com", "javaherobrine@qq.com", "CraftGame工作室", "这是伪造发件人", "javaherobrine@qq.com","wvecgijiwyutbfbj", "2333");
	}
}
