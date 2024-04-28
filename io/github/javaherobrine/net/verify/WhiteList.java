package io.github.javaherobrine.net.verify;
import java.util.*;
public class WhiteList extends PlayerList{
	private HashSet<String> list=new HashSet<>();
	public WhiteList(String content){
		super(content);
	}
	public WhiteList() {
		super("");
	}
	@Override
	public void add(String player){
		list.add(player);
	}
	@Override
	public void remove(String player){
		if(check(player)){
			list.remove(player);
		}
	}
	@Override
	public boolean check(String player){
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
	@Override
	public void append(String pl) {
		String[] a=pl.split("\n");
		for(String str:a) {
			list.add(str);
		}
	}
}