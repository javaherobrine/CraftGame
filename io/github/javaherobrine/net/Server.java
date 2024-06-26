package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.net.verify.*;
import io.github.javaherobrine.net.event.*;
import io.github.javaherobrine.*;
public class Server extends Thread implements Closeable{
	private ServerSocket server;
	private WhiteList wh=new WhiteList();
	private Blacklist bl=new Blacklist();
	private Map<String,ServerSideClient> connected=Collections.synchronizedMap(new HashMap<String,ServerSideClient>());
	EventHandler handler=new EventHandler();
	public Server(int port) throws IOException {
		server=new ServerSocket(port);
		start();
	}
	public void accept() throws IOException {
		ServerSideClient c=new ServerSideClient(server.accept());
		c.handler=handler;
		LoginEvent init=(LoginEvent)c.recv();
		if(!wh.check(init.player)) {
			c.send(new DisconnectEvent("Not allowed"));
			c.close();
		}
		if(!bl.check(init.player)) {
			c.send(new DisconnectEvent("Banned"));
			c.close();
		}
		if(!init.sync.equals(LoginEvent.getInstance().sync)) {
			c.send(new DisconnectEvent("Some loaded mods are different from the server"));
			c.close();
		}
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
		handler.close();
	}
	public void kick(String player) throws IOException {
		kick(player,"You are kicked by the server");
	}
	public void kick(String player,String message) throws IOException {
		Client c=getClient(player);
		c.send(new DisconnectEvent(message));
		c.close();
		System.out.println("[INFO]Player "+player+" is kicked");
	}
	public Client getClient(String player) {
		return connected.get(player);
	}
	public void ban(String player) throws IOException{
		ban(player,Long.MAX_VALUE);
	}
	public void ban(String player,long time) throws IOException {
		bl.ban(player,time);
		kick(player,"You are banned for "+time+" seconds");
	}
	public void permit(String player) {
		wh.add(player);
	}
	public void unban(String player) {
		bl.unban(player);
	}
	public void setWhiteList(boolean enable) {
		wh.enabled=enable;
	}
	public void setBlackList(boolean enable) {
		bl.enabled=enable;
	}
	public void appendWL(String path) throws IOException{
		wh.append(GameUtils.ofFile(path));
	}
	public void appendBL(String path) throws IOException{
		bl.append(GameUtils.ofFile(path));
	}
	public int connected() {
		return connected.size();
	}
}
