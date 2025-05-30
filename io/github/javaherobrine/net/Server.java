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
	private Map<String,ServerSideClient> connected=new HashMap<String,ServerSideClient>();
	EventHandler handler=new EventHandler();
	public Server(int port) throws IOException {
		server=new ServerSocket(port);
		start();
	}
	public Client removeClient(String name) {
		synchronized(connected) {
			return connected.remove(name);
		}
	}
	@SuppressWarnings("resource")
	public void accept() throws IOException {
		ServerSideClient c=new ServerSideClient(server.accept(),this,handler);
		if(c.protocol instanceof Protocol.NullProtocol) {
			c.close();//bad protocol
			return;
		}
		//verification
		LoginEvent init=(LoginEvent)c.recv();
		if(!wh.check(init.player)) {
			c.send(new DisconnectEvent("Not allowed"));
			c.close();
			return;
		}
		if(!bl.check(init.player)) {
			c.send(new DisconnectEvent("Banned"));
			c.close();
			return;
		}
		if(!init.sync.equals(LoginEvent.getInstance().sync)) {
			c.send(new DisconnectEvent("Some loaded mods are different from the server"));
			c.close();
			return;
		}
		synchronized(connected) {
			connected.put(init.player, c);
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
		synchronized(connected) {
			connected.values().forEach(n->{
				try {
					n.send(new DisconnectEvent("server closed"));
				} catch (IOException e) {}
			});
			connected.clear();
		}
	}
	public void kick(String player) throws IOException {
		kick(player,"You are kicked by the server");
	}
	public void kick(String player,String message) throws IOException {
		Client c=removeClient(player);
		c.send(new DisconnectEvent(message));
		c.close();
		System.out.println("[INFO]Player "+player+" is kicked");
	}
	public Client getClient(String player) {
		synchronized(connected) {
			return connected.get(player);
		}
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
		wh.append(GameUtils.ofFile(new File(path)));
	}
	public void appendBL(String path) throws IOException{
		bl.append(GameUtils.ofFile(new File(path)));
	}
	public int connected() {
		synchronized(connected) {
			return connected.size();
		}
	}
	public void sendAll(EventContent ec) throws IOException{
		synchronized(connected) {
			connected.values().forEach(n->{
				try {
					n.send(ec);
				} catch (IOException e) {}
			});
		}
	}
}
