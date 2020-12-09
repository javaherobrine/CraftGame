package io.github.javaherobrine;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.stream.*;
import java.util.*;
public class ModLoader {
	public HashMap<String,JarClassLoader> MODS_LOADERS=new HashMap<>();
	public ModLoader(File src,String[] args) {
		File[] mods=src.listFiles(f->{
			return f.toString().endsWith(".jar");
		});
		Stream.of(mods).forEach(f->{
			try {
				MODS_LOADERS.put(f.getName(), new JarClassLoader(f.toURI().toURL()));
			} catch (MalformedURLException e) {}
		});
		MODS_LOADERS.values().stream().forEach(loader->{
			try {
				Class<?> main=loader.loadClass(loader.getMainClassName());
				main.getMethod("main", String[].class).invoke(null, args);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {}
		});
	}
}
