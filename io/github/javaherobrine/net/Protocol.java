package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.*;
public abstract class Protocol implements Iterator<EventContent>,Cloneable {
	public static final LinkedList<Class<?>> SUPPORTED_PROTOCOLS=new LinkedList<>();
	static {
		TrieNode.REGISTRY.put(Protocol.class.getName(), new NullProtocol());
		SUPPORTED_PROTOCOLS.addFirst(Protocol.class);
	}
	protected Throwable exception=null;
	protected InputStream in;
	protected OutputStream out;
	public abstract void send(EventContent ec) throws IOException;
	public Protocol(Socket soc) throws IOException {
		setSocket(soc);
	}
	protected Protocol() {}
	@Override
	public boolean hasNext() {
		return exception==null;
	}
	public Throwable getException() {
		return exception;
	}
	@Override
	public abstract Protocol clone();
	public void setSocket(Socket soc) throws IOException {
		in=soc.getInputStream();
		out=soc.getOutputStream();
	}
	//define a invalid protocol, to indicate protocol not supported
	static class NullProtocol extends Protocol{
		@Override
		public EventContent next() {
			return null;
		}
		@Override
		public void send(EventContent ec) throws IOException {}
		@Override
		public Protocol clone() {
			return null;
		}
	}
}
