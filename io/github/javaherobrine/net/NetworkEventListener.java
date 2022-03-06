package io.github.javaherobrine.net;
public interface NetworkEventListener {//please use singleton pattern
	void connect(Client c);//do operations when a client connects
	void disconnect(Client c);//do operations when a client disconnects
}
