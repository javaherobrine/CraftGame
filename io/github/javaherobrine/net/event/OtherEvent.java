package io.github.javaherobrine.net.event;
import java.util.*;
public abstract class OtherEvent extends EventContent{
	public Object content;
	public abstract Object initContent(Map map);
}
