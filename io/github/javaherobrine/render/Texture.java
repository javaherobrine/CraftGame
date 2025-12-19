package io.github.javaherobrine.render;
import io.github.javaherobrine.*;
import java.nio.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL45.*;
public class Texture {
	public final int textureID;
	public final int width,height;
	public Texture(byte[] data) throws IOException{
		textureID = glGenTextures();
		int height[] = new int[1], width[] = new int[1], alpha[] = new int[1];
		long addr=GameUtils.address(data);
		long buf = nstbi_load_from_memory(addr,data.length, height, width, alpha, 0);
		GameUtils.allowGC(addr, data);
		if(buf==0) {
			throw new IOException("Invalid Texture");
		}
		this.width=width[0];
		this.height=height[0];
		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width[0], height[0], 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
		glGenerateMipmap(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		nstbi_image_free(buf);
	}
	/*
	 * @throws IOException: If can't create texture from given file
	 */
	public Texture(InputStream in) throws IOException {
		textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureID);
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
		this.width=img.getWidth();
		this.height=img.getHeight();
		glGenerateMipmap(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, img.getWidth(), img.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
	}
	private Texture() {
		width=16;
		height=16;
		textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureID);
	}
	public void activate(int text) {
		glActiveTexture(GL_TEXTURE0 + text);
		glBindTexture(GL_TEXTURE_2D, textureID);
	}
	public static Texture error() {
		Texture text = new Texture();
		int data[] = { -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216,
				-16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -1,
				-16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961,
				-16776961, -16776961, -16776961, -1, -16777216, -16777216, -16776961, -1, -16776961, -16776961,
				-16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -1, -16776961,
				-16777216, -16777216, -16776961, -16776961, -1, -16776961, -16776961, -16776961, -16776961, -16776961,
				-16776961, -16776961, -16776961, -1, -16776961, -16776961, -16777216, -16777216, -16776961, -16776961,
				-16776961, -1, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -1, -16776961,
				-16776961, -16776961, -16777216, -16777216, -16776961, -16776961, -16776961, -16776961, -1, -16776961,
				-16776961, -16776961, -16776961, -1, -16776961, -16776961, -16776961, -16776961, -16777216, -16777216,
				-16776961, -16776961, -16776961, -16776961, -16776961, -1, -16776961, -16776961, -1, -16776961,
				-16776961, -16776961, -16776961, -16776961, -16777216, -16777216, -16776961, -16776961, -16776961,
				-16776961, -16776961, -16776961, -1, -1, -16776961, -16776961, -16776961, -16776961, -16776961,
				-16776961, -16777216, -16777216, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -1,
				-1, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16777216, -16777216, -16776961,
				-16776961, -16776961, -16776961, -16776961, -1, -16776961, -16776961, -1, -16776961, -16776961,
				-16776961, -16776961, -16776961, -16777216, -16777216, -16776961, -16776961, -16776961, -16776961, -1,
				-16776961, -16776961, -16776961, -16776961, -1, -16776961, -16776961, -16776961, -16776961, -16777216,
				-16777216, -16776961, -16776961, -16776961, -1, -16776961, -16776961, -16776961, -16776961, -16776961,
				-16776961, -1, -16776961, -16776961, -16776961, -16777216, -16777216, -16776961, -16776961, -1,
				-16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -1, -16776961,
				-16776961, -16777216, -16777216, -16776961, -1, -16776961, -16776961, -16776961, -16776961, -16776961,
				-16776961, -16776961, -16776961, -16776961, -16776961, -1, -16776961, -16777216, -16777216, -1,
				-16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961,
				-16776961, -16776961, -16776961, -1, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216,
				-16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216,
				-16777216, -16777216 };//Hard coding
		glGenerateMipmap(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 16, 16, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
		return text;
	}
	public static Texture create(InputStream in) {
		Texture text;
		try {
			text=new Texture(in);
		} catch (IOException e) {
			text=Constant.INVALID_TEXTURE_HARD_CODING;
		}
		return text;
	}
	public static Texture create(byte[] data) {
		Texture text;
		try {
			text=new Texture(data);
		}catch(IOException e) {
			text=Constant.INVALID_TEXTURE_HARD_CODING;
		}
		return text;
	}
}
