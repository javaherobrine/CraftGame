package io.github.javaherobrine.render;
import io.github.javaherobrine.*;
import java.nio.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL45.*;
public class Texture {
	public final float[] coords;
	public final int textureID;
	@Deprecated
	public Texture(float[] coords, byte[] data) {
		textureID = glGenTextures();
		this.coords = coords;
		int height[] = new int[1], width[] = new int[1], alpha[] = new int[1];
		ByteBuffer buf = stbi_load_from_memory(GameUtils.memcpy(data), height, width, alpha, 0);
		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width[0], height[0], 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
		glGenerateMipmap(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		stbi_image_free(buf);
	}
	public Texture(float[] coords, InputStream in) throws IOException {
		this.coords = coords;
		textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureID);
		/*
		 * Code from LovelyZeeiam, but optimized alpha red green blue -> alpha blue
		 * green red
		 */
		BufferedImage img = ImageIO.read(in);
		int[] data = new int[img.getWidth() * img.getHeight()];
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), data, 0, img.getWidth());
		for (int i = 0; i < data.length; ++i) {
			int temp = data[i];
			temp &= 0xFF00FF00;
			temp |= (data[i] & 0xFF) << 16;
			temp |= (data[i] >> 16) & 0xFF;
			data[i] = temp;
		}
		glGenerateMipmap(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, img.getWidth(), img.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
	}
	public void activate() {
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, textureID);
	}
}
