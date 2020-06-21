package io.github.javaherobrine;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.*;
public class SavePacket {
	private File packFileDir;
	private Class<?>c;
	private String cp;
	private BufferedReader br;
	private String[] str;
	public static final FileClassLoader LOAD_CLASS_FROM_FILE=new FileClassLoader();
	public SavePacket(File packFileDir) {
		this.packFileDir=packFileDir;
	}
	public void loadMods() {
		if(!packFileDir.exists()|packFileDir.isFile()|packFileDir.listFiles().length==0) {
			return;
		}
		File[] savepacks=packFileDir.listFiles();
		Stream.of(savepacks).filter(f->{
			return (!packFileDir.exists()|packFileDir.isFile()|packFileDir.listFiles().length==0);
		}).forEach(f->{
			Stream.of(f.listFiles()).forEach(ff->{
				if(ff.getName().equals("options.txt")) {
					try {
						br=new BufferedReader(new FileReader(ff));
						str=br.readLine().split(":");
						cp=str[1].strip().replace('.','/');
						c=LOAD_CLASS_FROM_FILE.loadClass(ff.getParentFile().getAbsolutePath()+cp);
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
