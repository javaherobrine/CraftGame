package io.github.javaherobrine.world;
import io.github.javaherobrine.net.*;
public final class ServerChunkManager extends LocalChunkManager{
	private Server server;
	public ServerChunkManager(Save s,Server server) {
		super(s);
		this.server=server;
	}
}
