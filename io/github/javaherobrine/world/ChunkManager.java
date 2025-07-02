package io.github.javaherobrine.world;
import java.util.*;
import io.github.javaherobrine.*;
public sealed abstract class ChunkManager permits LocalChunkManager, NetworkChunkManager{
	public HashMap<Int3Pair,Chunk> loaded;
	protected int dimension=-1;
	public Chunk unload(int x,int y) {
		return loaded.remove(new Int3Pair(dimension,x,y));
	}
	public Chunk getChunk(int x,int y) {
		return loaded.computeIfAbsent(new Int3Pair(dimension,x,y), k->getUnloadedChunk(dimension,x,y));
	}
	public abstract Chunk getUnloadedChunk(int dimension,int x,int y);
	public void changeDimension(int d) {
		if(d==dimension) {
			return;
		}
		dimension=d;
		loaded.clear();
	}
	public void loadChunk(int x,int y,Chunk chk) {
		if(chk!=null) {
			loaded.put(new Int3Pair(dimension,x,y), chk);
		}
	}
	public static ChunkManager manager;
}
