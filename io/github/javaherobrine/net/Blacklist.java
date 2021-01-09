package io.github.javaherobrine.net;
import java.io.*;
import java.util.*;
public class Blacklist implements Serializable{
	HashSet<String> list;
	public Blacklist() {
		list=new HashSet<>();
	}
	public Blacklist(HashSet<String> init) {
		list=init;
	}
	public Blacklist(String...init) {
		this();
		list.addAll(init);
	}
	public void add(String name) {
		list.add(name);
	}
	public boolean isInBlacklist(String name) {
		return list.contains(name);
	}
}
