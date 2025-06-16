package io.github.javaherobrine.net.event;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.math.*;
import io.github.javaherobrine.world.*;
import io.github.javaherobrine.net.*;
public class ChunkLoadEvent extends EventContent{
	private static final long serialVersionUID = 1L;
	public Chunk chk=null;
	public boolean unload=false;
	public int dimension;
	public int x;
	public int y;
	@SuppressWarnings("unchecked")
	@Override
	public SimpleEntry<String, Object>[] values() {
		return new SimpleEntry[] {
				new SimpleEntry<String,Object>("dimension",dimension),
				new SimpleEntry<String,Object>("x",x),
				new SimpleEntry<String,Object>("y",y),
				new SimpleEntry<String,Object>("chunk",chk)
		};
	}
	@Override
	public void valueOf(Map<String, Object> input) {
		x=((BigInteger)input.get("x")).intValue();
		y=((BigInteger)input.get("y")).intValue();
		dimension=((BigInteger)input.get("dimension")).intValue();
		chk=(Chunk)input.get("chunk");
	}
	@Override
	public void recvExec(boolean serverside) throws Exception {
		if(serverside) {
			chk=ChunkManager.manager.getChunk(x, y);
			recver.send(this);
		}else {
			ChunkManager.manager.changeDimension(dimension);
			ChunkManager.manager.loadChunk(x,y,chk);
		}
	}
	@Override
	public EventContent clone() {
		return new ChunkLoadEvent();
	}
}
