package io.github.javaherobrine.net;
import java.io.*;
import java.util.concurrent.*;
public class AsyncDataProcessor implements DataProcessor{
	DataProcessor dp;
	@Override
	public void write(OutputStream os, byte[] source) throws IOException, InterruptedException {
		CompletableFuture.runAsync(()->{
			try {
				dp.write(os, source);
			}catch(IOException | InterruptedException e) {}
		});
	}
	@Override
	@Deprecated
	public byte[] read(InputStream is) throws IOException, InterruptedException {
		//TODO 
		return null;
	}	
}
