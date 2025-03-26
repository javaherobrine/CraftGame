package io.github.javaherobrine.item;
import java.io.*;
public class ItemInfo implements Externalizable{
	private static final long serialVersionUID=1;
	/**the unique ID of the item*/
	public int ID;
	/**the extension which the item is introduced*/
	public int MOD;
	/**the index of the item(if an item is registered by different extensions but refers to the same thing)*/
	public int index;
	/**the max number of the items can be placed in a single slot*/
	public int limit;
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		in.read();
	}
}
