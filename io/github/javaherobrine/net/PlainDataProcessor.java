package io.github.javaherobrine.net;
import java.io.*;
import io.github.javaherobrine.ioStream.*;
@Deprecated
public class PlainDataProcessor implements DataProcessor{
	private PlainDataProcessor() {}
	public static final PlainDataProcessor DEFAULT_PROCESSOR=new PlainDataProcessor();
	public static final DataProcessor NO_PROCESS=new DataProcessor() {
		@Override
		public void write(OutputStream os, byte[] source) throws IOException {
			os.write(source);
		}
		@Override
		public byte[] read(InputStream is) throws IOException {
			return is.readNBytes(1);
		}
	};
	@Override
	public void write(OutputStream os,byte[] source) throws IOException {
		os.write(IOUtils.intToByte4(source.length));
		os.write(source);
	}
	@Override
	public byte[] read(InputStream is) throws IOException,InterruptedException{
		return is.readNBytes(IOUtils.byte4ToInt(is.readNBytes(4),0));
	}
}
