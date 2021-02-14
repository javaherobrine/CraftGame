package io.github.javaherobrine;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.stream.*;
import java.util.*;
public class ModLoader {
	public HashMap<String,JarClassLoader> MODS_LOADERS=new HashMap<>();
	public static ModLoader loader=new ModLoader();
	private String[] args;
	private File src;
	public StringBuilder SC_SYNC=new StringBuilder();
	private ModLoader() {}
	public ModLoader(File src,String[] args) {
		this.src=src;
		this.args=args;
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
				if(loader.getSync()) {
					SC_SYNC.append(loader.getID());
					SC_SYNC.append(",");
				}
				main.getMethod("main", String[].class).invoke(null, args);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {}
		});
		loader=this;
	}
	@Override
	public String toString() {
		return SC_SYNC.toString();
	}
	public void reload() {
		loader=new ModLoader(src,args);
	}
}
