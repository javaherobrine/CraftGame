package io.github.javaherobrine.net;
import java.io.*;
import java.net.http.*;
import java.net.http.HttpResponse.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.net.*;
public class Client {
	private Socket client;
	private boolean isConnection;
	private OutputThread ot;
	private InputThread it;
	public Client() {
	}
	/**
	 * ����ָ���ķ�����
	 * @param url ����������������ip��ַ�����ַ�����ʾ
	 * @param port �������˿�
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
	 * �Ͽ��Է�����������
	 * @throws IOException
	 */
	public void disconnection() throws IOException {
		if(!isConnection) {
			throw new SocketException("�޷��Ͽ����ӣ����Ӳ�����");
		}
		it.close();
		ot.close();
		this.client.shutdownOutput();
		this.client.shutdownInput();
		this.client.close();
	}
	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, ExecutionException {
		Socket soc=new Socket("localhost",8888);
		soc.close();
	}
}
