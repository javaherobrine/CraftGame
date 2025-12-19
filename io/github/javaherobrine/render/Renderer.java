package io.github.javaherobrine.render;
import xueli.game2.lifecycle.*;
import java.io.*;
import io.github.javaherobrine.math.MatrixHelper;
import io.github.javaherobrine.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL45.*;
import xueli.utils.io.*;
import org.joml.*;
public class Renderer implements RunnableLifeCycle {
	private Window win;
	private long frame = -1;
	private Shader shader;
	private Texture[] loaded= {
			Texture.create(Files.getResourcePackedInJarStream("/textures/andesite.png")),
			Texture.create(Files.getResourcePackedInJarStream("/textures/grassblock.png")),
			Constant.INVALID_TEXTURE_HARD_CODING};
	private VAO vao;
	private Matrix4f f=MatrixHelper.perspective(1920, 1080, 90,0.1f,100);
	public Renderer(Window window) {
		win = window;
		vao=VAO.blockVAO(new Vector2f(0,1), new Vector2f(0,0),new Vector2f(1,1), new Vector2f(1,0),GL_STATIC_DRAW);
		vao.bindVBO(GL_STATIC_DRAW);
		vao.attribute(0, 3);
		vao.attribute(1, 2, 3);
	}
	@Override
	public void init() {
		System.err.println(f);
		try {
			shader = new Shader(new String(Files.getResourcePackedInJarStream("/shaders/vertex.vs").readAllBytes()),
					new String(Files.getResourcePackedInJarStream("/shaders/fragment.fs").readAllBytes()));
			shader.exec();
			glUniform1i(3, 0);
			glUniform1i(4, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		win.init();
		frame = System.currentTimeMillis();
	}
	@Override
	public void tick() {// In something about rendering, this is called "Render" ---LovelyZeeiam
		// compute delta time
		long current = System.currentTimeMillis();
		long deltaTime = current - frame;
		frame = current;
		// process input
		win.input(deltaTime);
		// render
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		Constant.INVALID_TEXTURE_HARD_CODING.activate(0);
		shader.exec();
		shader.uniform(1,win.camera.lookAt());
		shader.uniform(2, f);
		for(int i=0;i<3;++i) {
			shader.exec();
			loaded[i].activate(0);
			for(int j=0;j<10;++j) {
				shader.uniform(0,new Matrix4f().translate(i, 0, j));
				Constant.BREAKING_BLOCKS[j].activate(1);
				vao.apply();
			}
		}
		// process events and swap buffers
		win.tick();
	}
	@Override
	public void release() {
		win.release();
	}
	@Override
	public boolean isRunning() {
		return !glfwWindowShouldClose(win.window);
	}
}
