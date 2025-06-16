package io.github.javaherobrine.world;
import io.github.javaherobrine.net.*;
public final class ServerChunkManager extends LocalChunkManager{
	private Server server;
	public int[] dimensionCount=new int[Dimension.INDEX];
	public ServerChunkManager(LocalChunkManager s,Server server) {
		super(s.sav);
		this.server=server;
	}
}
