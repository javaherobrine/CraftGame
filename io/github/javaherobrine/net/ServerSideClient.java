package io.github.javaherobrine.net;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.*;
import java.io.*;
public class ServerSideClient extends Client{
	private EventHandler handler;
	public String player;
	private Server s;
	protected ServerSideClient(Socket sc,Server server,EventHandler handle) throws IOException {
		super(sc);
		s=server;
		handler=handle;
		if(askProtocol(ObjectStreamProtocol.class)) {
			protocol=new ObjectStreamProtocol(sc);
			return;
		}
		if(askProtocol(JSONProtocol.class)) {
			protocol=new JSONProtocol(sc);
			return;
		}
		LinkedList<Class<?>> list=Protocol.SUPPORTED_PROTOCOLS;
		Iterator<Class<?>> iter=list.iterator();
		while(iter.hasNext()) {
			Class<?> pro=iter.next();
			if(askProtocol(pro)) {
				protocol=((Protocol)TrieNode.REGISTRY.access(pro.getName())).clone();
				protocol.setSocket(sc);
				return;
			}
		}
		askProtocol(Protocol.class);
		protocol=(Protocol)(TrieNode.REGISTRY.access(Protocol.class.getName()));
	}
	@SuppressWarnings("resource")
	private boolean askProtocol(Class<?> protocol) throws IOException{
		client.getOutputStream().write((protocol.getName()+'\n').getBytes());
		return client.getInputStream().read()==1;
	}
	@SuppressWarnings("resource")
	@Override
	public void run() {
		while(!disconnected) {
			try {
				handler.push(recv());
			} catch (IOException | InterruptedException e) {//the connection was closed
				e.printStackTrace();
				break;
			}
		}
		s.removeClient(player);
	}
	@Override
	public void close() throws IOException{
		disconnected=true;
		client.close();
	}
}
