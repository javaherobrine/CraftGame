package io.github.javaherobrine.net;
import java.io.*;
import java.util.*;
public class AutoSync<T> extends Thread{
	ArrayList<PipedInputStream> list=new ArrayList<>();
	HashMap<PipedInputStream,Boolean> map=new HashMap<>();
	public void run() {
		while(true) {
				
			}
		}
	public AutoSync() {}
	public void online(int pipedNo) {
		map.put(list.get(pipedNo),true);
	}
	public void offline(int pipedNo) {
		map.put(list.get(pipedNo),false);
	}
}
