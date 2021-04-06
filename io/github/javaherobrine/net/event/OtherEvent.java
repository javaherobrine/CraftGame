package io.github.javaherobrine.net.event;
import java.util.*;
import io.github.javaherobrine.net.*;
public abstract class OtherEvent extends EventContent{
	public OtherEvent(Client c) {
		super(c);
	}
	public Object content;
	public abstract Object initContent(Map map);
}
