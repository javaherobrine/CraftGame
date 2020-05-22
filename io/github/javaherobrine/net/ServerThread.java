package io.github.javaherobrine.net;
import java.net.*;
import java.io.*;
import java.util.*;
import io.github.javaherobrine.*;
public class ServerThread extends Thread{
	public boolean isConnet=true;
	public Socket s;
	public InputStream is;
	public OutputStream os;
	@Override
	public void run() {
		try {
			InputStream is=s.getInputStream();
			OutputStream os=s.getOutputStream();
			while(isConnet) {
			}
		}catch(EOFException e) {
			String ss=null;
			Set<Map.Entry<String,Socket>> s=Server.sockets.entrySet();
			for(Map.Entry<String,Socket> entry:s) {
				if(entry.getValue().equals(s)) {
					ss=entry.getKey();
				}
			}
			System.out.println(ss+"断开了连接");
			Server.sockets.remove(ss,this.s);
		} catch (IOException e) {
		}
	}
	public ServerThread(String name,InputStream is,OutputStream os) {
		super();
		this.s=Server.sockets.get(name);
	}
	public void close() {
		isConnet=false;
	}
}
