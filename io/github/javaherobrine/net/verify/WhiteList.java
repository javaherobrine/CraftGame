package io.github.javaherobrine.net.verify;
import java.util.*;
public class WhiteList {
	private HashSet<String> list=new HashSet<>();
	public boolean enabled=false;
	public WhiteList(String content){
		append(content);
	}
	public WhiteList() {}
	public void add(String player){
		enabled=true;
		list.add(player);
	}
	public void remove(String player){
		if(list.contains(player)){
			list.remove(player);
		}
	}
	public boolean check(String player){
		if(!enabled) {
			return true;
		}
		return list.contains(player);
	}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(String str:list) {
			sb.append(str);
			sb.append("\n");
		}
		return sb.toString();
	}
	public void append(String pl) {
		enabled=true;
		String[] a=pl.split("\n");
		for(String str:a) {
			list.add(str);
		}
	}
}