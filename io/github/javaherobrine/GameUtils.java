package io.github.javaherobrine;
import java.io.*;
import java.util.Random;
import java.util.Arrays;
import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryUtil;
public final class GameUtils {
	public static final Random GENERATOR = new Random();
	private GameUtils() {
	}
	/**
	 * read a text file
	 * 
	 * @param file file
	 * @return content
	 * @throws IOException
	 */
	public static String ofFile(File file) throws IOException {
		return ofFile(file, "UTF-8");
	}
	/**
	 * read a text file in a specific charset
	 * 
	 * @param file    file
	 * @param charset charset
	 * @return content
	 * @throws IOException
	 */
	public static String ofFile(File file, String charset) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		String str = new String(in.readAllBytes(), charset);
		in.close();
		return str;
	}
	/**
	 * generate a random boolean whose possibility of true is p
	 * 
	 * @param p possibility
	 * @return result
	 */
	public static boolean possibility(double p) {
		return GENERATOR.nextDouble() < p;
	}
	/**
	 * compute the prefix sum of a sequence
	 * 
	 * @param arr sequence
	 */
	public static void prefixSum(double arr[]) {
		for (int i = 1; i < arr.length; i++) {
			arr[i] += arr[i - 1];
		}
	}
	/**
	 * randomly select a element with possibility p[]
	 * 
	 * @param p the prefix sum of the possibilities
	 * @return the index
	 */
	public static int possibilty(double p[]) {
		return Arrays.binarySearch(p, GENERATOR.nextDouble());
	}
	public static int readUint(Reader r) throws IOException {
		int ch = r.read();
		int res = 0;
		while ((ch < '0' || ch > '9') && ch != -1) {
			ch = r.read();
		}
		while (ch >= '0' && ch <= '9') {
			res = (res << 3) + (res << 1);
			res += ch ^ '0';
			ch = r.read();
		}
		return res;
	}
	/**
	 * A strange implementation of memcpy in stdlib.h, but new memory is
	 * automatically allocated
	 * 
	 * @param b memory to copy
	 * @return allocated new memory
	 */
	public static ByteBuffer memcpy(byte[] b) {
		ByteBuffer res = MemoryUtil.memAlloc(b.length);
		res.put(b);
		res.flip();
		return res;
	}
}
