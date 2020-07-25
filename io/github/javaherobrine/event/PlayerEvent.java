package io.github.javaherobrine.event;
import io.github.javaherobrine.net.*;
import java.io.*;
import java.net.*;
public class PlayerEvent extends InternetProcessEvent {
	@Override
	public void online() {

	}
	public void offline() {

	}
	@Override
	public void process(Object obj) {
		
	}
	public PlayerEvent(Client client) {
		
	}
	public PlayerEvent(Socket soc) throws IOException {
		this(Client.initClientFromSocket(soc));
	}
}
