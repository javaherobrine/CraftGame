package io.github.javaherobrine.item;
import io.github.javaherobrine.*;
public abstract class Item {
	public static final TrieNode ITEM=new TrieNode();
	public abstract void maxNumber();
	public abstract void onLeftClick();
	public abstract void onRightClick();
}
