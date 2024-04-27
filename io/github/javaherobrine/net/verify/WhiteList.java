package io.github.javaherobrine.net.verify;
import java.util.*;
public class WhiteList extends PlayerList{
	private HashSet<String> list=new HashSet<>();
	public WhiteList(String content){
		super(content);
		//TODO init
	}
	@Override
	public void add(String player){
		list.add(player);
	}
	@Override
	public void remove(S	tring player){
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
		//TODO store the list
		return "";
	}
}