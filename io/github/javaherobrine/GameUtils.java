package io.github.javaherobrine;
import java.io.*;
import java.util.Random;
import java.util.Arrays;
public class GameUtils {
	public static final Random GENERATOR=new Random();
	private GameUtils() {}
	/**
	 * read a text file
	 * @param file file
	 * @return content
	 * @throws IOException
	 */
	public static String ofFile(String file) throws IOException {
		return ofFile(file,"UTF-8");
	}
	/**
	 * read a text file in a specific charset
	 * @param file file
	 * @param charset charset
	 * @return content
	 * @throws IOException
	 */
	public static String ofFile(String file,String charset) throws IOException {
		InputStream in=new BufferedInputStream(new FileInputStream(file));
		String str=new String(in.readAllBytes(),charset);
		in.close();
		return str;
	}
	/**
	 * generate a random boolean whose possibility of true is p
	 * @param p possibility
	 * @return result
	 */
	public static boolean possibility(double p) {
		return GENERATOR.nextDouble()<p;
	}
	/**
	 * compute the prefix sum of a sequence
	 * @param arr sequence
	 */
	public static void prefixSum(double arr[]) {
		for(int i=1;i<arr.length;i++) {
			arr[i]+=arr[i-1];
		}
	}
	/**
	 * randomly select a element with possibility p[]
	 * @param p the prefix sum of the possibilities
	 * @return the index
	 */
	public static int possibilty(double p[]) {
		return Arrays.binarySearch(p,GENERATOR.nextDouble());
	}
}
