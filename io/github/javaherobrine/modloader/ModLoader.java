package io.github.javaherobrine.modloader;
import java.io.*;
import static io.github.javaherobrine.modloader.DependenceDAG.GraphNode;
import java.util.*;
public abstract class ModLoader {
	public static ArrayList<JarClassLoader> classLoaders=new ArrayList<>();
	public static String loaded="";
	public static ArrayList<GraphNode> root=new ArrayList<>();
	public static void loadModsFrom(File f) throws IOException{
		if(f.exists()) {
			if(f.isFile()) {
				classLoaders.add(JarClassLoader.getLoaderFromFile(f));
			}else {
				for(File f0:f.listFiles((f0)->{
					return f0.toString().endsWith(".jar");
				})) {
					JarClassLoader loader=JarClassLoader.getLoaderFromFile(f0);
					if(loader.valid()) {
						classLoaders.add(loader);
						loaded+=(loader.getID()+",");
					}else {
						loader.close();
					}
				}
			}
			classLoaders.stream().forEach(cl->{
				String[] libs=cl.getLibraries();
				GraphNode n=DependenceDAG.getNodeByID(cl.getID());
				if(libs.length==0) {
					root.add(n);
				}else {
					Arrays.stream(libs).forEach(str->{
						DependenceDAG.getNodeByID(str).link(n);
					});
				}
			});
		}
	}
}
