package io.github.javaherobrine.net.event;
import io.github.javaherobrine.net.*;
import java.util.*;
public class LoginEvent extends EventContent{
	private static final long serialVersionUID=1;
	private static LoginEvent instance=null;
	public String player;
	public HashSet<String> sync=new HashSet<>();
	@Override
	public void recvExec(boolean serverside) {}
	private LoginEvent(){}
	public static LoginEvent getInstance() {
		if(instance==null) {
			instance=new LoginEvent();
		}
		return instance;
	}
}
