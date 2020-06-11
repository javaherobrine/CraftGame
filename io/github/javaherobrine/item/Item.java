package io.github.javaherobrine.item;
import java.util.*;
public class Item {
	public ItemModel model;
	public static ArrayList<ItemModel> items;
	public Item(ItemModel model) {
		this.model=model;
		items.add(model);
	}
	public static void regedit(ItemModel i) {
		items.add(i);
	}
}
