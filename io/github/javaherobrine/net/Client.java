package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class Client {
	private Socket client;
	private boolean isConnection;
	private OutputThread ot;
	private InputThread it;
	public Client() {}
	/**
	 * ����ָ���ķ�����
	 * @param url ����������������ip��ַ�����ַ�����ʾ
	 * @param port �������˿�
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void bind(String url,int port) throws UnknownHostException, IOException {
		this.client=new Socket(url,port);
		this.client.setKeepAlive(true);
		ios();
		isConnection=true;
	}
	public static Client initClientFromSocket(Socket soc) throws IOException {
		Client c=new Client();
		c.bind(soc);
		return c;
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
	public void bind(Socket soc) throws IOException {
		if(soc.isInputShutdown()||soc.isOutputShutdown()||soc.isClosed()) {
			throw new SocketException("Can't create client:I/O is closed or socket is closed");
		}
		if(soc.isConnected()) {
			client=soc;
			if(!soc.getKeepAlive()) {
				soc.setKeepAlive(true);				
			}
			ios();
			isConnection=true;
		}else {
			throw new SocketException("Can't create client:connection is not init");
		}
	}
	private void ios() throws IOException {
		ot=new OutputThread(this.client);
		ot.start();
		it=new InputThread(this.client);
		it.start();
	}
}
