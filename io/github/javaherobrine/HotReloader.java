package io.github.javaherobrine;
import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.stream.*;
public final class HotReloader {
	private HotReloader() {}
	public static final void load(File modF,ModLoader ml) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(modF.isFile()) {
			JarClassLoader jcl=new JarClassLoader(modF.toURI().toURL());
			Class.forName(jcl.getMainClassName()).getMethod("main", String[].class).invoke(null, null);
		}else {
			Stream.of(modF.listFiles()).forEach(f->{
				try {
					load(f,ml);
				} catch (MalformedURLException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
			});
		}
	}
}
