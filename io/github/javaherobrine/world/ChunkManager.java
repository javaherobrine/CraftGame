package io.github.javaherobrine.world;
import java.util.*;
import io.github.javaherobrine.*;
public sealed abstract class ChunkManager permits LocalChunkManager, NetworkChunkManager{
	protected HashMap<Int2Pair,Chunk> loaded;
	protected int dimension=-1;
	public Chunk unload(int x,int y) {
		return loaded.remove(new Int2Pair(x,y));
	}
	public Chunk getChunk(int x,int y) {
		Chunk c=loaded.get(new Int2Pair(x,y));
		if(c==null) {
			c=getUnloadedChunk(x,y);
			loadChunk(x,y,c);
		}
		return c;
	}
	public abstract Chunk getUnloadedChunk(int x,int y);
	public void changeDimension(int d) {
		if(d==dimension) {
			return;
		}
		dimension=d;
		loaded.clear();
	}
	public void loadChunk(int x,int y,Chunk chk) {
		if(chk!=null) {
			loaded.put(new Int2Pair(x,y), chk);
		}
	}
	public static ChunkManager manager;
}
