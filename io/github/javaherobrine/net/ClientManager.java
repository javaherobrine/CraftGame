package io.github.javaherobrine.net;
import java.io.*;
import io.github.javaherobrine.net.event.*;
public final class ClientManager {
	private ClientManager() {}
	private static ThreadLocal<Client> connected=new ThreadLocal<>();
	public static Client currentClient() {
		return connected.get();
	}
	public static boolean disconnect() {
		try {
			connected.get().sendEvent(new EventObject(new DisconnectEvent()));
			connected.get().close();
			connected.remove();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public static void add(Client c) {
		connected.set(c);
	}
}
