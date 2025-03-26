package io.github.javaherobrine.item;
import java.io.*;
public class Item implements Externalizable{
	private static final long serialVersionUID=1;
	public ItemInfo info;
	public int number;
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		
	}
}
