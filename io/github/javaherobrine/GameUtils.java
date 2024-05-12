package io.github.javaherobrine;
import java.io.*;
public class GameUtils {
	private GameUtils() {}
	public static String ofFile(String file) throws IOException {
		return ofFile(file,"UTF-8");
	}
	public static String ofFile(String file,String charset) throws IOException {
		InputStream in=new BufferedInputStream(new FileInputStream(file));
		String str=new String(in.readAllBytes(),charset);
		in.close();
		return str;
	}
}
