package io.github.javaherobrine;
import java.io.*;
public class FileClassLoader extends ClassLoader {
	@Override
	public Class<?> loadClass(String path){
		try {
			FileInputStream fi=new FileInputStream(path);
			Class<?> c=loadClass(fi);
			fi.close();
			return c;
		} catch (IOException e) {
			return null;
		}
	}
	public Class<?> loadClass(File cf){
		return loadClass(cf.getAbsolutePath());
	}
	public Class<?> loadClass(byte[] bs){
		return defineClass(null,bs,0,bs.length);
	}
	public Class<?> loadClass(InputStream is) throws IOException{
		return loadClass(is.readAllBytes());
	}
}
