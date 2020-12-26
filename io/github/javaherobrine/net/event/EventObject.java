package io.github.javaherobrine.net.event;
import java.io.*;
public class EventObject implements Serializable{
	public EventContent content;
	private static final long serialVersionUID = 1L;
	public EventObject(EventContent content) {
		this.content=content;
	}
	@Override
	public int hashCode() {
		return content.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		try {
			return ((EventObject)obj).content.equals(content);
		}catch(Exception e) {
			return false;
		}
	}
}
