package io.github.javaherobrine;
import java.io.*;
import java.net.*;
import java.lang.reflect.*;
import java.util.stream.*;
public class SavePacket {
	private File packFileDir;
	private Class<?>c;
	private String cp;
	private BufferedReader br;
	private String[] str;
	URLClassLoader loader;
	public SavePacket(File packFileDir) {
		this.packFileDir=packFileDir;
	}
	public void loadMods() {
		if(!packFileDir.exists()|packFileDir.isFile()|packFileDir.listFiles().length==0) {
			return;
		}
		File[] savepacks=packFileDir.listFiles();
		Stream<File> stream=Stream.of(savepacks).filter(f->{
			return (!f.exists()||f.isFile()||f.listFiles().length==0);
		});
		URL[] urls=new URL[stream.toArray().length];
		stream.forEach(f->{
			Stream.of(f.listFiles()).forEach(ff->{
				if(ff.getName().equals("options.txt")) {
					try {
						br=new BufferedReader(new FileReader(ff));
						str=br.readLine().split(":");
						
						str=br.readLine().split(":");
						new Thread(()-> {
							try {
								this.c.getMethod(str[1].strip(), null).invoke(null, null);
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException| NoSuchMethodException | SecurityException e) {
							}
						}).start();
						br.close();
					} catch (IOException e) {
					}
				}
			});
		});;
		str=null;
		cp=null;
		System.gc();
	}
}
