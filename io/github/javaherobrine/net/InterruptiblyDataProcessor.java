package io.github.javaherobrine.net;
import java.io.*;
import io.github.javaherobrine.ioStream.*;
public class InterruptiblyDataProcessor implements DataProcessor{
	@Override
	public void write(OutputStream os, byte[] source) throws IOException ,InterruptedException{
		int length=0;
		while(length<source.length) {
			if(Thread.interrupted()) {
				throw new InterruptedException();
			}
			os.write(source[length]);
			length++;
		}
	}
	@Override
	public byte[] read(InputStream is) throws IOException, InterruptedException {
		int i=IOUtils.byte4ToInt(is.readNBytes(4),0);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		int len=0;
		if(len<i) {
			if(Thread.interrupted()) {
				throw new InterruptedException();
			}
			baos.write(is.read());
		}
		return baos.toByteArray();
	}
}