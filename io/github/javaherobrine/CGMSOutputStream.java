package io.github.javaherobrine;
import java.io.*;
import io.github.javaherobrine.ioStream.*;
public class CGMSOutputStream extends OutputStream {
	private CGMSFile cgms;
	private ObjectOutputStream oos;
	public CGMSOutputStream(CGMSFile cgms) {
		this.cgms=cgms;
	}
	@Override
	@Deprecated
	public void write(int b) throws IOException{
		close();
		throw new EOFException("你用错误的方式写了CGMS，CGMS文件关闭");
	}
	public void write(Script scr) throws IOException {
		openOOS();
		oos.writeObject(scr);
	}
	private void openOOS() throws IOException {
		oos=new ObjectOutputStream(new FileOutputStream(cgms));
	}
	public void write(byte[] script) throws IOException {
		openOOS();
		try {
			Script scr=(Script)IOUtils.byteArrayToObject(script);
			oos.writeObject(scr);
		} catch (ClassNotFoundException e) {
		}
	}
	public void write(String script) throws IOException, CGMSException {
		openOOS();
		CGMSGrammerCheck.check(script);
		oos.writeUTF(script);
	}
	public void write(ByteArrayOutputStream bytes) throws IOException {
		write(bytes.toByteArray());
	}
}
