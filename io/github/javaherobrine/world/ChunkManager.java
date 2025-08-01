package io.github.javaherobrine.world;
import java.util.*;
import io.github.javaherobrine.*;
public sealed abstract class ChunkManager permits LocalChunkManager, NetworkChunkManager{
	public HashMap<SIITuple,Chunk> loaded;
	protected String dimension="";
	public Chunk unload(int x,int y) {
		return loaded.remove(new SIITuple(dimension,x,y));
	}
	public Chunk getChunk(int x,int y) {
		return loaded.computeIfAbsent(new SIITuple(dimension,x,y), k->getUnloadedChunk(dimension,x,y));
	}
	public abstract Chunk getUnloadedChunk(String dimension,int x,int y);
	public void changeDimension(String d) {
		if(d.equals(dimension)) {
			return;
		}
		dimension=d;
		loaded.clear();
	}
	public void loadChunk(int x,int y,Chunk chk) {
		if(chk!=null) {
			loaded.put(new SIITuple(dimension,x,y), chk);
		}
	}
	public static ChunkManager manager;
}
