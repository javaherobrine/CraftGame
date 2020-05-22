package io.github.javaherobrine;
import java.io.*;
public class CGMSFile extends File implements Serializable{
	public CGMSFile(String pathname) throws CGMSException {
		super(pathname);
		if(!pathname.toLowerCase().endsWith(".cgms")) {
			throw new CGMSException("这不是CGMS的文件");
		}
	}
	public void readScript() {
		
	}
}
