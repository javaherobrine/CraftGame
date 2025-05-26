package io.github.javaherobrine.blocks;
import io.github.javaherobrine.*;
public abstract class Block implements Cloneable{
	public Block() {}
	public Block(String str) {
		init(str);
	}
	public abstract void init(String str);
	public static Block load(String str) {
		String[] temp=str.split(" ",2);
		Block b=(Block) TrieNode.REGISTRY.access(temp[0]);
		if(b==null) {
			return null;
		}
		b=b.clone();
		b.init(temp[1]);
		return b;
	}
	@Override
	protected Block clone() {
		try {
			return (Block) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error("panic");//oops, JVM isn't reliable, or code has been illegally modified!
		}
	}
}
