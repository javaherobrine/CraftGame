package io.github.javaherobrine.item;
import java.util.*;
public class Item {
	public ItemModel modal;
	public static ArrayList<ItemModel> items;
	public Item(ItemModel modal) {
		this.modal=modal;
		items.add(modal);
	}
	public static void regdit(ItemModel i) {
		items.add(i);
	}
}
