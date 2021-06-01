package io.github.javaherobrine.modloader;
import java.io.*;
import java.util.*;
public class ModLoader {
	public static ArrayList<JarClassLoader> classLoaders=new ArrayList<>();
	public static final String[] NO_ARGUMENT=new String[] {};
	public static void loadModsFrom(File f,String[] args) throws IOException,ModException{
		if(f.exists()) {
			if(f.isFile()) {
				classLoaders.add(JarClassLoader.getLoaderFromFile(f));
			}else {
				for(File f0:f.listFiles((f0)->{
					return f0.toString().endsWith(".jar");
				})) {
					classLoaders.add(JarClassLoader.getLoaderFromFile(f0));
				}
			}
			classLoaders.forEach(loader->{
				try {
					loader.loadMainClass().getMethod("main", String[].class);
				} catch (ClassNotFoundException | NoSuchMethodException e) {}
			});
		}
	}
	public static void loadModsFrom(File f) throws IOException, ModException {
		loadModsFrom(f,NO_ARGUMENT);
	}
}
