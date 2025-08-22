package io.github.javaherobrine.render;
import io.github.javaherobrine.*;
import java.nio.*;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL45.*;
public class Texture {
    public final float[] coords;
    public final int textureID;
    public Texture(float[] coords, byte[] data) {
		textureID=glGenTextures();
		this.coords=coords;
		int height[]=new int[1],width[]=new int[1],alpha[]=new int[1];
		ByteBuffer buf=stbi_load_from_memory(GameUtils.memcpy(data), height, width, alpha, 0);
		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width[0], height[0], 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
		glGenerateMipmap(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		stbi_image_free(buf);
    }
    public void activate() {
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D,textureID);
    }
}
