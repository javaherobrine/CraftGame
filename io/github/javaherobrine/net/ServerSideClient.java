package io.github.javaherobrine.net;
import java.net.*;
import java.io.*;
class ServerSideClient extends Client{
	EventHandler handler;
	public String player;
	protected ServerSideClient(Socket sc) throws IOException {
		super(sc);
	}
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
	}
	@Override
	public void close() throws IOException{
		disconnected=true;
		client.close();
	}
}
