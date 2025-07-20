package io.github.javaherobrine.render;
import org.lwjgl.opengl.*;
import io.github.javaherobrine.render.entity.*;
import xueli.utils.io.*;
public class RenderManager {
	private final WindowManager window;
	private ShaderManager shader;
	public RenderManager() {
		window=Launcher.window;
	}
	public void init() throws Exception{
		shader=new ShaderManager();
		shader.createVertexShader(Files.readResourcePackedInJarAndPackedToString("/shaders/vertex.vs"));
		shader.createFragmentShader(Files.readResourcePackedInJarAndPackedToString("/shaders/fragment.fs"));
		shader.link();
		shader.createUniform("javaherobrine");
	}
	public void render(Model model) {
		clear();
		shader.bind();
		shader.setUniform("javaherobrine", 0);
		GL30.glBindVertexArray(model.getId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().id);
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
		shader.unbind();
	}
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
	}
	public void cleanup() {
		
	}
}
