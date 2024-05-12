package io.github.javaherobrine.net;
import java.net.*;
import io.github.javaherobrine.net.event.*;
import java.io.*;
public class Client extends Thread implements Closeable{
	protected Socket client;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	protected boolean disconnected=false;
	public Client(String host,int port) throws IOException {
		client=new Socket(host,port);
		in=new ObjectInputStream(client.getInputStream());
		out=new ObjectOutputStream(client.getOutputStream());
		send(LoginEvent.getInstance());
		start();
	}
	protected Client(Socket ac) throws IOException {//used in server
		client=ac;
		in=new ObjectInputStream(client.getInputStream());
		out=new ObjectOutputStream(client.getOutputStream());
	}
	@Override
	public void run() {
		while(!disconnected) {
			try {
				EventContent ec=recv();
				ec.recver=this;
				try {
					ec.recvExec(false);
				}catch(Exception e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				//that is to say,the connection is broken
				//TODO show a message that the connection is broken
				break;
			} 
		}
	}
	public void send(EventContent c) throws IOException {
		out.writeObject(c);
	}
	protected EventContent recv() throws IOException{
		try {
			return (EventContent)in.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException(e);
		}
	}
	@Override
	public void close() throws IOException {
		send(new DisconnectEvent("disconnect"));
		client.close();
	}
}
