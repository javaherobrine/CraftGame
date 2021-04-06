package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class ServerSideClient extends Client {
	public Server server;
	public ServerSideClient(Socket soc,Server s) throws IOException {
		super(soc);
		this.server=s;
	}
}
