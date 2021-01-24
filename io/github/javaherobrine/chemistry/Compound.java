package io.github.javaherobrine.chemistry;
import io.github.javaherobrine.*;
import io.github.javaherobrine.utils.*;
import java.util.*;
public abstract class Compound extends Chemical{
	public static Class[] compounds=new Class[]{DefaultCompound.class};
	@ReactionMethod
	public Compound(Element...elements) {}
}
