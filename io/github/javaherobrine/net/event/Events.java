package io.github.javaherobrine.net.event;
import java.util.*;
public class Events{
	public static final Events EVENTS_BEAN=new Events();
	public ArrayList<Class<? extends EventContent>> list=new ArrayList<>();
	public void delete(Integer index) {
		list.remove(index.intValue());
	}
	public void add(Class<? extends EventContent> v) {
		list.add(v);
	}
	public void replace(Integer index, Class<? extends EventContent> val) {
		list.remove(index.intValue());
		list.add(index.intValue(), val);
	}
	public void reg(Class<? extends EventContent> val,int id) {
		list.add(id,val);
		try {
			val.getField("eid").setInt(null, id);
		} catch (IllegalAccessException | NoSuchFieldException e) {}
	}
	public int nextEID() {
		return list.size();
	}
}
