package io.github.javaherobrine.net;
import java.io.*;
public class NetStatus {
	public volatile static int inputLength=-1;
	public volatile static int mark=0;
	public volatile static byte[] outputData=null;
	public volatile static byte[] data=null;
	public volatile static int off=0;
	public volatile static boolean sent=false;
	public volatile static boolean isWrite=false;
	public synchronized static byte[] getData() {
		while(data==null) {}
		inputLength=-1;
		return data;
	}
	public static enum List implements Serializable{
		
	}
}
