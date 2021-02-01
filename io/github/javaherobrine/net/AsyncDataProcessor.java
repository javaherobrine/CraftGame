package io.github.javaherobrine.net;
import java.io.*;
import java.util.function.*;
import java.util.concurrent.*;
@Deprecated
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
		return dp.read(is);
	}
	public CompletableFuture<byte[]> readAsync(InputStream is){
		return CompletableFuture.supplyAsync(new Supplier<byte[]>(){
			@Override
			public byte[] get() {	
				try {
					return dp.read(is);
				} catch (IOException | InterruptedException e) {
					return null;
				}
			} 
		 });
	}
}
