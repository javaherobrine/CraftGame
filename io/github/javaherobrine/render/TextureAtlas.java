package io.github.javaherobrine.render;
import java.io.IOException;
import java.io.InputStream;
public class TextureAtlas extends Texture{
	public TextureAtlas(byte[] data) throws IOException {
		super(data);
	}
	public TextureAtlas(InputStream in) throws IOException {
		super(in);
	}
}
