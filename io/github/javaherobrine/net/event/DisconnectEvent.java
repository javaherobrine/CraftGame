package io.github.javaherobrine.net.event;
import io.github.javaherobrine.net.*;
import java.io.*;
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
	public void recvExec(boolean serverside) {
		try {
			recver.close();
		}catch(IOException e) {
			//do nothing,because the client is closed
		}
		if(serverside) {
			System.out.println("[INFO] A Player Disconnected");
		}else {
			System.err.println("[INFO] "+message);
		}
	}
	public DisconnectEvent(String msg) {
		message=msg;
	}
}
