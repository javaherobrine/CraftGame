package io.github.javaherobrine.net.event;
import io.github.javaherobrine.*;
import java.util.*;
public class Events implements ElementAccess<Integer, Class<? extends EventContent>>{
	public static final Events EVENTS_BEAN=new Events();
	public ArrayList<Class<? extends EventContent>> list=new ArrayList<>();
	@Override
	public void delete(Integer index) {
		list.remove(index.intValue());
	}
	@Override
	public void add(Class<? extends EventContent> v) {
		list.add(v);
	}
	@Override
	public void replace(Integer index, Class<? extends EventContent> val) {
		list.remove(index.intValue());
		list.add(index.intValue(), val);
	}
	{
		list.add(0, DisconnectEvent.class);
		list.add(1, OnlineEvent.class);
		list.add(2,OfflineEvent.class);
	}
	public void reg(Class<? extends OtherEvent> val) {
		reg(val,(val.getPackageName()+val.getSimpleName()).hashCode());
	}
	public void reg(Class<? extends OtherEvent> val,int id) {
		try {
			val.getField("eid").setInt(null, id);
		} catch (IllegalAccessException | NoSuchFieldException e) {}
		list.add(id,val);
	}
}
