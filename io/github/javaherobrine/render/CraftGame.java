package io.github.javaherobrine.render;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL11;
import io.github.javaherobrine.render.entity.*;
public class CraftGame implements ILogic{
	private int direction=0;
	private float color=0;
	private final ObjectLoader loader;
	private final RenderManager renderer;
	private final WindowManager window;
	private Model model;
	public CraftGame() {
		renderer=new RenderManager();
		window=Launcher.window;
		loader=new ObjectLoader();
	}
	@Override
	public void init() throws Exception {
		renderer.init();
		float[]vertices= {
			-0.5f,0.5f,0f,
			-0.5f,-0.5f,0f,
			0.5f,-0.5f,0f,
			0.5f,-0.5f,0f,
			0.5f,0.5f,0f,
			-0.5f,0.5f,0f
		};
		int[] indices= {
			0,1,3,
			3,1,2
		};
		float[] textureCoords= {
			0,0,
			0,1,
			1,1,
			1,0
		};
		model=loader.loadModel(vertices,textureCoords,indices);
		model.setTexture(new Texture(loader.loadTexture("textures/grassblock.png")));
	}
	@Override
	public void input() {
		if(window.isKeyPressed(GLFW.GLFW_KEY_W)) {
			direction=1;
		}else if(window.isKeyPressed(GLFW.GLFW_KEY_S)) {
			direction=-1;
		}else {
			direction=0;
		}
	}
	@Override
	public void update() {
		color+=direction*0.001f;
		if(color>1) {
			color=1;
		}else if(color<0) {
			color=0;
		}
	}
	@Override
	public void render() {
		if(window.resize) {
			GL11.glViewport(0, 0, window.width, window.height);
			window.resize=true;
		}
		window.setClearColor(color, color, color, 0);
		renderer.render(model);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
		loader.cleanup();
	}
}
