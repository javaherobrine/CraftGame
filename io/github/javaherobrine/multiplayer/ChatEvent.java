package io.github.javaherobrine.multiplayer;
import java.util.*;
import io.github.javaherobrine.net.*;
import io.github.javaherobrine.net.event.*;
import io.github.javaherobrine.render.*;
public class ChatEvent extends OtherEvent{
	public ChatEvent(Client c) {
		super(c);
	}
	@Override
	public Object initContent(Map map) {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public void sendExec() {
			
	}
	@Override
	public void recvExec() {
		// TODO 自动生成的方法存根
	}
	@Override
	public void serverSendExec() {
		// TODO 自动生成的方法存根
	}
	@Override
	public void serverRecvExec() {
		System.out.println("[CHAT]:"+content);
	}
}
