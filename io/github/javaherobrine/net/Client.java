package io.github.javaherobrine.net;
import java.io.*;
import io.github.javaherobrine.*;
import java.net.*;
public class Client {
	private Socket client;
	private boolean isConnection;
	private OutputThread ot;
	public ShakeHandsMessage msg=new ShakeHandsMessage();
	TCPInputStream tis;
	TCPOutputStream tos;
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
		it=new InputThread(this.client);
		tis=it.is;
		tos=ot.tos;
	}
	public void thread() {
		ot.start();
		it.start();
	}
	public void shakeHands() throws IOException{
		if(!isConnection) {
			throw new SocketException("No connection");
		}
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(tos,"UTF-8"));
		BufferedReader br=new BufferedReader(new InputStreamReader(tis,"UTF-8"));
		DataInputStream dis=new DataInputStream(tis);
		DataOutputStream dos=new DataOutputStream(tos);
		TransmissionFormat[] tf=TransmissionFormat.values();
		for(TransmissionFormat f:tf) {
			bw.write(f.toString());
			bw.newLine();
			bw.flush();
			try{
				if(TransmissionStatus.valueOf(br.readLine())==TransmissionStatus.ACCEPTED) {
					msg.format=f;
					break;
				}
			}
			catch(Exception e) {}
		}
		int id=dis.readInt();
		if(id==-1) {
			msg.connected=false;
			return;
		}
		msg.id=id;
		switch(msg.format) {
		case JSON:
			try {
				Class.forName("io.github.javaherobrine.JavaScript");
			} catch (ClassNotFoundException e) {}
		case OBJECT:
			break;
		case XML:
			break;
		default:
			break;
		}
	}
}
