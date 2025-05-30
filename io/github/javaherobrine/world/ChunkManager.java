package io.github.javaherobrine.world;
public abstract class ChunkManager {
	public abstract Chunk getChunk(int dimension,int x,int y);
	public abstract void loadChunk(int x,int y,Chunk chk);
	public static ChunkManager manager;
}
