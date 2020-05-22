package io.github.javaherobrine;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.*;
public class JarMod {
	public static ArrayList<String> temp=new ArrayList<>();
	public static void loadMods(File modFileDir) throws IOException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		File[] mods=modFileDir.listFiles();
		boolean isModCanLoad=false;
		Class<?> c=null;
		String s;
		File f;
		BufferedReader br;
		for(int i=0;i<mods.length;i++) {
			s=mods[i].getAbsolutePath();
			System.setProperty("java.class.path",s);
			f=getInfoFile(new ZipFile(s),Integer.toString(i));
			br=new BufferedReader(new FileReader(f));
			if(s.startsWith("mod-id:")) {
				temp.add(new StringBuilder().delete(0,6).toString().trim());
			}
			for(s=br.readLine();s!=null;s=br.readLine()) {
				s.trim();
				if(s.isEmpty()) {
					continue;
				}
				s.toLowerCase();
				if(s.startsWith("type:")){
					if(s.endsWith("lib")||s.startsWith("ext")) {
						continue;
					}else{
						isModCanLoad=true;
						continue;
					}
				}
				if(s.startsWith("main-class:")&&isModCanLoad) {
					c=Class.forName(new StringBuilder(s).delete(0,9).toString().trim());
					continue;
				}
				if(s.startsWith("main-method")&&c!=null) {
					s=new StringBuilder(s).delete(0,10).toString().trim();
					if(s.isEmpty()) {
						c.getMethod("run",null).invoke(null,null);
					}else {
						c.getMethod(s,null).invoke(null,null);
					}
					continue;
				}
				isModCanLoad=false;
				c=null;
			}
			br.close();
		}
	}
	public static File getInfoFile(ZipFile modFile,String fileName) throws IOException {
		ZipInputStream zis=new ZipInputStream(modFile.getInputStream(modFile.getEntry("options.txt")));
		File f=new File(fileName);
		f.createNewFile();
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(f));
		for(int i=zis.read();i!=-1;i=zis.read()) {
			bos.write(i);
		}
		bos.flush();
		bos.close();
		zis.close();
		modFile.close();
		return f;
	}
}
