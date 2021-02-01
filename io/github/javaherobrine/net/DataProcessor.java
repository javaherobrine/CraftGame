package io.github.javaherobrine.net;
import java.io.*;
@Deprecated
public interface DataProcessor {
	void write(OutputStream os,byte[] source) throws IOException,InterruptedException;
	byte[] read(InputStream is) throws IOException,InterruptedException;
}
