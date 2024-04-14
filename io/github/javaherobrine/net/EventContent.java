package io.github.javaherobrine.net;
import java.io.*;
public abstract class EventContent implements Serializable{
	private static final long serialVersionUID = 1;
	//If it takes long time,it had better be interruptible
	public abstract void recvExec(boolean serverside) throws Exception;
}
