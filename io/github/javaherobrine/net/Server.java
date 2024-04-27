package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.net.event.*;
public class Server extends Thread implements Closeable{
	private ServerSocket server;
	private HashMap<String,Client> connected;
	public Server(int port) throws IOException {
		server=new ServerSocket(port);
		EventHandler.handler=new EventHandler();
		EventHandler.handler.serverside=true;
		EventHandler.handler.start();
	}
	public void accept() throws IOException {
		Client c=new Client(server.accept());
		//TODO check
		c.start();
	}
	@Override
	public void run() {
		while(!server.isClosed()) {
			try {
				accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void close() throws IOException{
		server.close();
	}
	public void kick(String player) throws IOException {
		kick(player,"You are kicked by the server");
	}
	public void kick(String player,String message) throws IOException {
		Client c=getClient(player);
		c.send(new DisconnectEvent("message"));
		c.close();
	}
	public Client getClient(String player) {
		return connected.get(player);
	}
}
