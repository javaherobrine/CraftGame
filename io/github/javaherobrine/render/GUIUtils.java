package io.github.javaherobrine.render;
import java.nio.*;
import org.lwjgl.system.*;
public class GUIUtils {
	public static FloatBuffer storeDataInFloatBuffer(float[]data) {
		FloatBuffer buffer=MemoryUtil.memAllocFloat(data.length);
		return buffer.put(data).flip();
	}
	public static IntBuffer storeDataInIntBuffer(int[]data) {
		IntBuffer buffer=MemoryUtil.memAllocInt(data.length);
		return buffer.put(data).flip();
	}
}