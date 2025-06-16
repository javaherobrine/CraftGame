package io.github.javaherobrine.world;
import java.io.*;
import io.github.javaherobrine.net.*;
import io.github.javaherobrine.net.event.*;
import io.github.javaherobrine.*;
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
		Int2Pair pair=new Int2Pair(x,y);
		try {
			connection.send(e);
			while((!Thread.interrupted())&&loaded.get(pair)==null) {}
			return loaded.get(pair);
		} catch (IOException e1) {}//network error
		return null;
	}
	@Override
	public Chunk unload(int x,int y) {
		Chunk chk=super.unload(x, y);
		ChunkLoadEvent e=new ChunkLoadEvent();
		e.x=x;
		e.y=y;
		e.dimension=dimension;
		e.unload=true;
		try {
			connection.send(e);
		}catch(IOException e1) {}
		return chk;
	}
}
