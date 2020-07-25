package io.github.javaherobrine.net;
import java.io.*;
import java.util.*;
import io.github.javaherobrine.event.*;
public class AutoSync<T> extends Thread{
	OutputThread ot;
	ArrayList<ObjectInputStream> list=new ArrayList<>();
	HashMap<ObjectInputStream,Boolean> map=new HashMap<>();
	InternetProcessEvent processor;
	public void run() {
		int i;
		int ii=0;
		while(true) {
				i=list.size();
				ObjectInputStream pis=list.get(ii);
				if(map.get(pis)) {
					try {
						processor.process(pis.readObject());
					} catch (ClassNotFoundException e) {
					} catch (IOException e) {
					}
				}
				if(!(ii<i)) {
					ii=0;
				}else {
					ii++;
				}
			}
		}
	public AutoSync(OutputThread ot,InternetProcessEvent eventproc) {
		this.ot=ot;
		this.processor=eventproc;
	}
	public void online(int pipedNo) {
		map.put(list.get(pipedNo),true);
	}
	public void offline(int pipedNo) {
		map.put(list.get(pipedNo),false);
	}
}
