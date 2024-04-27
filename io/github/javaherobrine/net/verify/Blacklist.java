package io.github.javaherobrine.net.verify;
import java.util.*;
public class Blacklist {
	private HashMap<String,ListItem> banned;
	private class ListItem{
		long start,duration;
		public ListItem(long s,long d) {
			start=s;
			duration=d;
		}
		public boolean end() {
			return System.currentTimeMillis()-start>=duration;
		}
	}
	public void ban(String player) {//ban a player forever
		ban(player,Long.MAX_VALUE);
	}
	public void ban(String player,long duration) {//ban a player for a while
		ListItem item=new ListItem(System.currentTimeMillis(),duration);
		banned.put(player,item);
	}
	public boolean check(String player) {//check whether the player is banned
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
}
