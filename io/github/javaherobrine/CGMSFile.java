package io.github.javaherobrine;
import java.io.*;
public class CGMSFile extends File implements Serializable{
	public CGMSFile(String pathname) throws CGMSException {
		super(pathname);
		String[] temp=pathname.split(".");
		if(!temp[temp.length-1].toLowerCase().equals("cgms")) {
			throw new CGMSException("这不是CGMS的文件");
		}
	}
}
