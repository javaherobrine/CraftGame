package io.github.javaherobrine.ioStream;
import java.io.*;
import io.github.javaherobrine.*;
public class JSONOutputStream implements ObjectOutput{
	private OutputStream dest;
	public JSONOutputStream(OutputStream dest) {
		this.dest=dest;
	}
	public void writeBoolean(boolean v) throws IOException {}
	public void writeByte(int v) throws IOException {}
	public void writeShort(int v) throws IOException {}
	public void writeChar(int v) throws IOException {}
	public void writeInt(int v) throws IOException {}
	public void writeLong(long v) throws IOException {}
	public void writeFloat(float v) throws IOException {}
	public void writeDouble(double v) throws IOException {}
	public void writeBytes(String s) throws IOException {}
	public void writeChars(String s) throws IOException {}
	public void writeUTF(String s) throws IOException {}
	public void writeObject(Object obj) throws IOException {
		byte[] data=JavaScript.json(obj).getBytes("UTF-8");
		dest.write(IOUtils.intToByte4(data.length));
		dest.write(data);
	}
	public void write(int b) throws IOException {}
	public void write(byte[] b) throws IOException {}
	public void write(byte[] b, int off, int len) throws IOException {}
	public void flush() throws IOException {}
	public void close() throws IOException {
		dest.close();
	}
}
