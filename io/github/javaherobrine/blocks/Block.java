package io.github.javaherobrine.blocks;
import io.github.javaherobrine.*;
import io.github.javaherobrine.format.*;
import java.util.*;
public abstract class Block implements Cloneable, JSONSerializable {
	public Block() {
	}
	public Block(String str) {
		valueOf(str);
	}
	public abstract void valueOf(String str);
	@Override
	public abstract String toString();
	public static Block load(String str) {
		String[] temp = str.split(" ", 2);
		Block b = (Block) TrieNode.REGISTRY.access(temp[0]);
		if (b == null) {
			return null;
		}
		b = b.clone();
		b.valueOf(temp[1]);
		return b;
	}
	@Override
	public Block clone() {
		try {
			return (Block) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error("panic");// oops, JVM isn't reliable, or code has been illegally modified!
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public AbstractMap.SimpleEntry<String, Object>[] values() {
		return new AbstractMap.SimpleEntry[] {
				new AbstractMap.SimpleEntry<String, Object>("type", getClass().getName()),
				new AbstractMap.SimpleEntry<String, Object>("value", toString()) };
	}
	@Override
	public void valueOf(Map<String, Object> input) {
		valueOf((String) input.get("value"));
	}
}
