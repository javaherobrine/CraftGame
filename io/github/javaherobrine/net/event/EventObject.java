package io.github.javaherobrine.net.event;
import java.io.*;
public class EventObject implements Serializable{
	public EventContent content;
	private static final long serialVersionUID = 1L;
	public EventObject(EventContent content) {
		this.content=content;
	}
}
