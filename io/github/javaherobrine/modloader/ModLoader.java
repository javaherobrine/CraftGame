package io.github.javaherobrine.modloader;
import java.io.*;
import java.util.*;
public abstract class ModLoader {
	public static ArrayList<JarClassLoader> classLoaders=new ArrayList<>();
	public static final String[] NO_ARGUMENT=new String[] {};
	public static HashSet<String> libs=new HashSet<>();
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
					String str=loader.attr.getValue("libs");
					if(str!=null&&!str.trim().equals("")) {
						for(String s:str.split(",")) {
							
						}
					}
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
