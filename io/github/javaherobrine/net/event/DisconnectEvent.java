package io.github.javaherobrine.net.event;
import io.github.javaherobrine.net.*;
public class DisconnectEvent extends EventContent{
	private static final long serialVersionUID = 1;
	String message;
	/**
	 * to disconnect correctly
	 * @param
	 * serverside: if the value is true,a client is disconnected
	 * otherwise it is kicked
	 */
	@Override
	public void recvExec(boolean serverside) throws Exception {
		
	}
	public DisconnectEvent(String msg) {
		message=msg;
	}
}
