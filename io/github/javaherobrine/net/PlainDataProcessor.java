package io.github.javaherobrine.net;
import java.io.*;
import io.github.javaherobrine.ioStream.*;
public class PlainDataProcessor implements DataProcessor{
	private PlainDataProcessor() {}
	public static final PlainDataProcessor DEFAULT_PROCESSOR=new PlainDataProcessor();
	@Override
	public void write(OutputStream os,byte[] source) throws IOException {
		os.write(IOUtils.intToByte4(source.length));
		os.write(source);
	}
	@Override
	public byte[] read(InputStream is) throws IOException {
		return is.readNBytes(IOUtils.byte4ToInt(is.readNBytes(4),0));
	}
}
