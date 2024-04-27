package io.github.javaherobrine.net;
import java.net.*;
import java.io.*;
public class Client extends Thread implements Closeable{
	private Socket client;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean disconnected=false;
	public Client(String host,int port) throws IOException {
		client=new Socket(host,port);
		in=new ObjectInputStream(client.getInputStream());
		out=new ObjectOutputStream(client.getOutputStream());
		EventHandler.handler=new EventHandler();
		EventHandler.handler.serverside=false;
		EventHandler.handler.start();
		//TODO check
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
				EventHandler.handler.push((EventContent)in.readObject());
			} catch (ClassNotFoundException e) {
				//that is to say,the extensions are different
				//TODO show an error and then close the connection
			} catch (IOException e) {
				//that is to say,the connection is broken
				//TODO show a message that the connection is broken
			}
		}
	}
	public void send(EventContent c) throws IOException {
		out.writeObject(c);
	}
	@Override
	public void close() throws IOException {
		//TODO Send disconnect event
		client.close();
	}
}
