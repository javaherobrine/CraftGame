package io.github.javaherobrine.net;
import io.github.javaherobrine.format.*;
import io.github.javaherobrine.*;
import java.io.*;
import java.net.*;
import java.util.*;
public class JSONProtocol extends Protocol{
	static {
		TrieNode.REGISTRY.put(JSONProtocol.class.getName(), new JSONProtocol());
	}
	private JSONReader reader;
	private JSONWriter writer;
	@SuppressWarnings("unchecked")
	@Override
	public EventContent next() {
		if(hasNext()) {
			try {
				HashMap<String,Object> map=(HashMap<String,Object>)reader.nextObject();
				EventContent ec=((EventContent)TrieNode.REGISTRY.access((String)map.get("type"))).clone();
				ec.valueOf((HashMap<String,Object>)map.get("content"));
				return ec;
			} catch (Throwable e) {
				exception=e;
			}
		}
		return null;
	}
	@Override
	public void send(EventContent ec) throws IOException {
		writer.writeObject(ec);
	}
	@Override
	public Protocol clone() {
		return new JSONProtocol();
	}
	private JSONProtocol() {}
	public JSONProtocol(Socket soc) throws IOException{
		super(soc);
		setSocket(soc);
	}
	@SuppressWarnings("resource")
	@Override
	public void setSocket(Socket soc) throws IOException {
		reader=new JSONReader(soc.getInputStream());
		writer=new JSONWriter(soc.getOutputStream());
	}
}
