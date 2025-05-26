package io.github.javaherobrine.net;
import java.net.*;
import io.github.javaherobrine.*;
import io.github.javaherobrine.net.event.*;
import java.io.*;
public class Client extends Thread implements Closeable{
	protected Socket client;
	protected Protocol protocol;
	protected boolean disconnected=false;
	@SuppressWarnings("resource")
	public Client(String host,int port) throws IOException {
		client=new Socket(host,port);
		//switch protocol
		boolean unaccepted=true;
		while(unaccepted) {
			Protocol p=(Protocol) TrieNode.REGISTRY.access(client.getInputStream(),'\n');
			if(p!=null) {
				client.getOutputStream().write(1);
				protocol=p;
				break;
			}
			client.getOutputStream().write(0);
		}
		//handshake
		send(LoginEvent.getInstance());
		start();
	}
	protected Client(Socket ac) throws IOException {//used in server
		client=ac;
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
		protocol.send(c);
	}
	protected EventContent recv() throws IOException{
		return protocol.next();
	}
	@Override
	public void close() throws IOException {
		disconnected=true;
		send(new DisconnectEvent("disconnect"));
		client.close();
	}
}
