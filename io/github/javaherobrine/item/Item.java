package io.github.javaherobrine.item;
public abstract class Item {
	public abstract int maxNumber();
	public abstract void onLeftClick();
	public abstract void onRightClick();
	public abstract String identifier();
}
