package io.github.javaherobrine.world;
import java.io.*;
import io.github.javaherobrine.net.*;
import io.github.javaherobrine.net.event.*;
public final class NetworkChunkManager extends ChunkManager{
	private Client connection;
	public NetworkChunkManager(Client c) {
		connection=c;
	}
	@Override
	public Chunk getUnloadedChunk(int x, int y) {
		ChunkLoadEvent e=new ChunkLoadEvent();
		e.dimension=dimension;
		e.x=x;
		e.y=y;
		try {
			connection.send(e);
		} catch (IOException e1) {}//network error
		return null;
	}
}
