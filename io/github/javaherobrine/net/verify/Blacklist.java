package io.github.javaherobrine.net.verify;
import java.util.*;
public class Blacklist {
	private HashMap<String,ListItem> banned;
	public boolean enabled=false;
	private class ListItem{
		long start,duration;
		public ListItem(long s,long d) {
			start=s;
			duration=d;
		}
		protected ListItem(String str) {
			String[] strs=str.split(" ");
			start=Long.parseLong(strs[0]);
			duration=Long.parseLong(strs[1]);
		}
		public boolean end() {
			return System.currentTimeMillis()-start>=duration;
		}
		@Override
		public String toString() {
			return Long.toString(start)+" "+Long.toString(duration);
		}
	}
	public void ban(String player) {//ban a player forever
		ban(player,Long.MAX_VALUE);
	}
	public void ban(String player,long duration) {//ban a player for a while
		enabled=true;
		ListItem item=new ListItem(System.currentTimeMillis(),duration);
		banned.put(player,item);
	}
	public boolean check(String player) {//check whether the player is banned
		if(!enabled) {
			return true;
		}
		if(banned.containsKey(player)) {
			ListItem item=banned.get(player);
			if(item.end()) {
				banned.remove(player);
				return true;
			}
			return false;
		}
		return true;
	}
	public void unban(String player) {//unban a player
		if(banned.containsKey(player)) {
			banned.remove(player);
		}
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		banned.forEach((K,V)->{
			sb.append(K);
			sb.append("\n");
			sb.append(V.toString());
			sb.append("\n");
		});
		return sb.toString();
	}
	public void append(String str) {
		enabled=true;
		str.trim();
		String[] strs=str.split("\n");
		int size=strs.length/2;
		for(int i=0;i<size;++i) {
			banned.put(strs[i<<1],new ListItem(strs[(i<<1)|1]));
		}
	}
}
