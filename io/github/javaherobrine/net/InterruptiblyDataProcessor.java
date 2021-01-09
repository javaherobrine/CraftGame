package io.github.javaherobrine.net;
import java.io.*;
import io.github.javaherobrine.ioStream.*;
@Deprecated
public class InterruptiblyDataProcessor implements DataProcessor{
	@Override
	public void write(OutputStream os, byte[] source) throws IOException ,InterruptedException{
		int length=0;
		boolean writed=false;
		byte[] length1=IOUtils.intToByte4(source.length);
		while(length<source.length) {
			if(!writed) {
				if(Thread.interrupted()) {
					throw new InterruptedException();
				}
				os.write(length1[0]);
				if(Thread.interrupted()) {
					throw new InterruptedException();
				}
			}
			if(Thread.interrupted()) {
				throw new InterruptedException();
			}
			os.write(source[length]);
			if(Thread.interrupted()) {
				throw new InterruptedException();
			}
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
