package io.github.javaherobrine.net;
import java.io.IOException;
import java.net.Socket;
public class ServerImpl extends Server {
    public Protocol protocol;
    public ServerImpl(int port,Protocol p) throws IOException {
		super(port);
		protocol=p;
    }
           @Override
    public ServerSideClient accept(Socket socket) {
		return null;
    }
}
