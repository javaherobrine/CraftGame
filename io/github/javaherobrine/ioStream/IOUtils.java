package io.github.javaherobrine.ioStream;
import java.io.*;
import java.util.*;
public abstract class IOUtils {
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
	public static String decode(String src) {
		try {
			return new String(Base64.getDecoder().decode(src), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return src;
		}
	}
	public static int byte4ToInt(byte[] bytes, int off) {
		int b0 = bytes[off] & 0xFF;
		int b1 = bytes[off + 1] & 0xFF;
		int b2 = bytes[off + 2] & 0xFF;
		int b3 = bytes[off + 3] & 0xFF;
		return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
	}
	public static byte[] intToByte4(int i) {
		byte[] targets = new byte[4];
		targets[3] = (byte) (i & 0xFF);
		targets[2] = (byte) (i >> 8 & 0xFF);
		targets[1] = (byte) (i >> 16 & 0xFF);
		targets[0] = (byte) (i >> 24 & 0xFF);
		return targets;
	}
}
