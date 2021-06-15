package io.github.javaherobrine.modloader;
import java.io.*;
import java.util.*;
public abstract class ModLoader {
	public static ArrayList<JarClassLoader> classLoaders=new ArrayList<>();
	public static final String[] NO_ARGUMENT=new String[] {};
	public static String loaded="";
	public static String versions="";
	public static void loadModsFrom(File f,String[] args) throws IOException{
		if(f.exists()) {
			if(f.isFile()) {
				classLoaders.add(JarClassLoader.getLoaderFromFile(f));
			}else {
				for(File f0:f.listFiles((f0)->{
					return f0.toString().endsWith(".jar");
				})) {
					JarClassLoader loader=JarClassLoader.getLoaderFromFile(f0);
					classLoaders.add(loader);
					loaded+=(loader.getID()+",");
				}
			}
			classLoaders.forEach(loader->{
				try {
					loader.loadMainClass().getMethod("main", String[].class);
				} catch (ClassNotFoundException | NoSuchMethodException e) {}
			});
		}
	}
	public static void loadModsFrom(File f) throws IOException{
		loadModsFrom(f,NO_ARGUMENT);
	}
}
