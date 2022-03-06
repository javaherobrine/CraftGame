package io.github.javaherobrine.net;
import io.github.javaherobrine.*;
public class GameNetworkEventListener implements NetworkEventListener {
	private GameNetworkEventListener() {}
	public static final GameNetworkEventListener LISTENER=new GameNetworkEventListener();
	static {
		Constants.REGISTERED_NETWORK_EVENT_LISTENER.add(LISTENER);
	}
	@Override
	public void connect(Client c) {
		// TODO client implement is required	
	}
	@Override
	public void disconnect(Client c) {
		// TODO client implement is required	
	}
}
