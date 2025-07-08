package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.net.verify.*;
import io.github.javaherobrine.net.event.*;
import io.github.javaherobrine.world.*;
public class Server extends Thread implements Closeable{
	private ServerSocket server;
	private Map<String,ServerSideClient> connected=new HashMap<String,ServerSideClient>();
	public static final ArrayList<PlayerChecker> CHECKERS=new ArrayList<>();
	EventHandler handler=new EventHandler();
	public Server(int port) throws IOException {
		ChunkManager.manager=new ServerChunkManager((LocalChunkManager)ChunkManager.manager,this);
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
		for(PlayerChecker checker:CHECKERS) {
			if(checker.enabled&&checker.check(init.player)) {
				c.send(new DisconnectEvent("Refused"));
				return;
			}
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
					n.close();
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
