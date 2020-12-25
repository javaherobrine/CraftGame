package io.github.javaherobrine.net;
import java.io.*;
public interface DataProcessor {
	void write(OutputStream os,byte[] source) throws IOException,InterruptedException;
	byte[] read(InputStream is) throws IOException,InterruptedException;
}
