package io.github.javaherobrine.modloader;
import java.io.*;
import java.util.jar.*;
import java.net.*;
public class JarClassLoader extends URLClassLoader {
	public Attributes attr;
	public JarClassLoader(URL url) {
		super(new URL[] {url});
		try {
			attr=((JarURLConnection)(new URL("jar:"+url+"!/").openConnection())).getAttributes();
		} catch (IOException e) {}
	}
	public String getMainClassName(){
		return attr==null?null:attr.getValue(Attributes.Name.MAIN_CLASS);
	}
	public boolean getSync() {
		return attr==null?true:Boolean.parseBoolean(attr.getValue("resource"));
	}
	public String getID() {
		return attr==null?null:attr.getValue("mod-id");
	}
	public String[] getLibraries(){
		if(attr==null) {
			return new String[] {};
		}else {
			String str=attr.getValue("libraries");
			if(str==null) {
				return new String[] {};
			}else {
				str.trim();
				if(str.equals("")) {
					return new String[] {};
				}else {
					return str.split(",");
				}
			}
		}
	}
}
