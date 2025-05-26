package io.github.javaherobrine.net;
import java.io.*;
import io.github.javaherobrine.format.*;
public abstract class EventContent implements Serializable,JSONSerializable,Cloneable{
	private static final long serialVersionUID = 1;
	//If it takes long time,it had better be interruptible
	public abstract void recvExec(boolean serverside) throws Exception;
	public EventWrapper wrap() {
		return new EventWrapper(getClass().getName(),this);
	}
	public transient Client recver;
	@Override
	public abstract EventContent clone();
}
