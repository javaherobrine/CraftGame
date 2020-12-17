package io.github.javaherobrine;
import java.io.*;
import java.util.jar.*;
import java.net.*;
public class JarClassLoader extends URLClassLoader {
	private String url;
	public JarClassLoader(URL url) {
		super(new URL[] {url});
		this.url=url.toString();
	}
	public String getMainClassName(){
		try {
			URL newURL=new URL("jar:"+url+"!/");
			JarURLConnection juc=(JarURLConnection)newURL.openConnection();
			return juc.getMainAttributes()==null?null:juc.getMainAttributes().getValue(Attributes.Name.MAIN_CLASS);
		} catch (IOException e) {
			return null;
		}
	}
	public boolean getSync() {
		try {
			URL newURL=new URL("jar:"+url+"!/");
			JarURLConnection juc=(JarURLConnection)newURL.openConnection();
			return juc.getMainAttributes()==null?true:Boolean.parseBoolean(juc.getMainAttributes().getValue("resource"));
		} catch (IOException e) {
			return true;
		}
	}
	public String getID() {
		try {
			URL newURL=new URL("jar:"+url+"!/");
			JarURLConnection juc=(JarURLConnection)newURL.openConnection();
			return juc.getMainAttributes()==null?null:juc.getMainAttributes().getValue("mod-id");
		} catch (IOException e) {
			return null;
		}
	}
}
