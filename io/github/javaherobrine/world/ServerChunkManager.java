package io.github.javaherobrine.world;
import io.github.javaherobrine.net.*;
import io.github.javaherobrine.*;
import java.util.*;
public final class ServerChunkManager extends LocalChunkManager{
	public final Server server;
	public HashMap<Int3Pair,Integer> count;
	public ServerChunkManager(Save s,Server server) {
		super(s);
		this.server=server;
	}
	public ServerChunkManager(LocalChunkManager localChunkManager, Server server2) {
		this(localChunkManager.sav,server2);
		loaded=localChunkManager.loaded;
	}
	public void unload(int dimension,int x,int y) {
		Int3Pair pair=new Int3Pair(dimension,x,y);
		count.compute(pair, (k,v)->{
			if(v==null) {
				throw new Error("panic");
			}
			--v;
			if(v==0) {
				loaded.remove(pair);
				return null;
			}
			return v;
		});
	}
	public Chunk load(int dimension,int x,int y) {
		Int3Pair pair=new Int3Pair(dimension,x,y);
		count.compute(pair, (k,v)->{
			if(v==null) {
				loaded.put(pair,getUnloadedChunk(dimension,x,y));
				return 1;
			}
			return v+1;
		});
		return loaded.get(pair);
	}
}
