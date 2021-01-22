package io.github.javaherobrine.chemistry;
import io.github.javaherobrine.*;
import io.github.javaherobrine.utils.*;
import java.util.*;
public abstract class Compound extends Chemical{
	public static Class[] compounds=new Class[]{DefaultCompound.class};
	static {
		ModLoader.loader.MODS_LOADERS.values().forEach(v->{
			try {
				((Vector<Class<?>>)Constants.CLASSES.get(v)).stream().forEach(c->{
					try{
						compounds=ArrayUtils.merge(compounds, c.asSubclass(Compound.class));
					}catch(ClassCastException e) {}
				});;
			} catch (IllegalAccessException e) {}
		});
	}
	@ReactionMethod
	public Compound(Element...elements) {}
}
