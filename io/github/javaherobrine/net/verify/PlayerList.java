package io.github.javaherobrine.net.verify;
public abstract class PlayerList{
	public abstract void add(String player);
	public abstract void remove(String player);
	public abstract boolean check(String player);
	public PlayerList(String content){
		append(content);
	}
	public abstract void append(String pl);
}