package io.github.javaherobrine.ioStream;
import java.io.*;
import java.util.*;
/**
 * 一些会用在IO流的工具
 * @author Java_Herobrine
 */
public class IOUtils {
	public static final ByteArrayOutputStream baos=new ByteArrayOutputStream();
	public static final ObjectOutputStream oos=get();
	protected IOUtils() {
	}
	/**
	 * Base64加密字符串
	 * @param data 字符串
	 * @return 加密结果
	 */
	private static ObjectOutputStream get() {
		try {
			return new ObjectOutputStream(baos);
		} catch (IOException e) {
			return null;
		}
	}
	public static String encode(String data) {
		String s = data;
		byte[] b;
		try {
			b = s.getBytes("UTF-8");
			Base64.Encoder e = Base64.getEncoder();
			return e.encodeToString(b);
		} catch (UnsupportedEncodingException e1) {
			return s;
		}
	}
	/**
	 * Base64解密字符串
	 * @param src 加密后的字符串
	 * @return 解密结果
	 */
	public static String decode(String src) {
		try {
			return new String(Base64.getDecoder().decode(src), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return src;
		}
	}
	/**
	 * 将对象转字节数组
	 * @param obj 对象
	 * @return 字节数组
	 * @throws IOException
	 */
	public static byte[] objectToByteArray(Object obj) throws IOException {
		oos.writeObject(obj);
		return baos.toByteArray();
	}
	/**
	 * 反向转换
	 * @param data 字节数组
	 * @return 对象
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object byteArrayToObject(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bais=new ByteArrayInputStream(data);
		ObjectInputStream ois=new ObjectInputStream(bais);
		return ois.readObject();
	}
	/**
	 * Java8的readAllBytes()支持
	 * @param is 源输入流
	 * @return 读到的字节
	 * @throws IOException
	 */
	public static byte[] readAllBytesInJava8(InputStream is) throws IOException {
		byte[] data=new byte[is.available()];
		is.read(data);
		return data;
	}
	/**
	 * 将字节数组转成int类型
	 * @param bytes 字节数组
	 * @param off 偏移量
	 * @return 转换的int
	 */
	public static int byte4ToInt(byte[] bytes, int off) {
		int b0 = bytes[off] & 0xFF;
		int b1 = bytes[off + 1] & 0xFF;
		int b2 = bytes[off + 2] & 0xFF;
		int b3 = bytes[off + 3] & 0xFF;
		return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
	}
	/**
	 * 将int转换为字节数组
	 * @param i 源int类型数
	 * @return 字节数组
	 */
	public static byte[] intToByte4(int i) {
		byte[] targets = new byte[4];
		targets[3] = (byte) (i & 0xFF);
		targets[2] = (byte) (i >> 8 & 0xFF);
		targets[1] = (byte) (i >> 16 & 0xFF);
		targets[0] = (byte) (i >> 24 & 0xFF);
		return targets;
	}
}
