package io.github.javaherobrine;
import java.io.*;
import java.util.*;
@Deprecated
public final class CGMSClass {
	private static ArrayList<String> paths=new ArrayList<>();
	CGMSClass() {}
	private CGMSField[] fields;
	private CGMSMethod[] methods;
	private String name;
	public static CGMSClass define(String className,CGMSField[] fields,CGMSMethod[] methods) {
		CGMSClass c=new CGMSClass();
		c.fields=fields;
		c.methods=methods;
		c.name=className;
		return c;
	}
	public static void setPathFromFile(File f) throws IOException{
		BufferedReader br=new BufferedReader(new FileReader(f));
		String s;
		while((s=br.readLine())!=null) {
			paths.add(s);
		}
		br.close();
	}
	public static void forName(String name) {
		for(int i=0;i<paths.size();i++) {
			try {
				forFile(name,new CGMSFile(paths.get(i)));
			} catch (CGMSException | IOException | ClassNotFoundException e) {
				
			}
		}
	}
	public static CGMSClass forFile(String className,CGMSFile file) throws IOException, ClassNotFoundException {
		int length=(int)file.length();
		BufferedReader br=new BufferedReader(new FileReader(file));
		char[] cbuf=new char[length];
		br.read(cbuf);
		br.close();
		int index=-1,end=-1;
		boolean isIndex=false;
		String files=new String(cbuf);
		StringBuilder builder=new StringBuilder(files);
		for(int i=0;i<length;i++) {
			if(!isIndex) {
				if(files.charAt(i)=='\'') {
					index=i;
				}
			}else {
				if(files.charAt(i)=='\'') {
					end=i;
				}
			}
			if(index!=-1&end!=-1) {
				builder.delete(index, end);
				index=-1;
				end=-1;
			}
		}
		files=builder.toString();
		String[] classes=files.split("class ");
		String[] cs;
		for(int i=1;i<classes.length;i++) {
			cs=classes[i].split(" ");
			if(cs[0].equals(className)) {
				CGMSClass c=new CGMSClass();
				c.init();
				return c;
			}
		}
		throw new ClassNotFoundException("在文件"+file+"找不到类"+className);
	}
	public void init() {
		
	}
}
