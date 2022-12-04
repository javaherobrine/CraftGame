package io.github.javaherobrine.render;
import org.lwjgl.glfw.*;
import io.github.javaherobrine.*;
public class EngineManager {
	public static final long NANOSECOND=1000000000L;
	public static final float FRAMERATE=1000;
	private static int fps;
	private static float frametime=1f/FRAMERATE;
	private boolean isRunning;
	private WindowManager window;
	private ILogic gameLogic;
	private GLFWErrorCallback errorCallback;
	private void init() throws Exception{
		GLFW.glfwSetErrorCallback(errorCallback=GLFWErrorCallback.createPrint(System.err));
		gameLogic=Launcher.game;
		window=Launcher.window;
		window.init();
		gameLogic.init();
	}
	public void start() throws Exception{
		init();
		if(isRunning) {
			return;
		}
		run();
	}
	public void run() {
		isRunning=true;
		int frames=0;
		long frameCounter=0;
		long lastTime=System.nanoTime();
		double unprocessedTime=0;
		while(isRunning) {
			boolean render=false;
			long startTime=System.nanoTime();
			long passedTime=startTime-lastTime;
			lastTime=startTime;
			unprocessedTime+=(double)passedTime/NANOSECOND;
			frameCounter+=passedTime;
			input();
			while(unprocessedTime>frametime) {
				render=true;
				unprocessedTime-=frametime;
				if(window.windowShouldClose()) {
					stop();
				}
				if(frameCounter>=NANOSECOND) {
					fps=frames;
					window.setTitle(""+fps);
					frames=0;
					frameCounter=0;
				}
			}
			if(render) {
				update();
				render();
				frames++;
			}
		}
		cleanup();
	}
	private void stop() {
		if(!isRunning) {
			return;
		}
		isRunning=false;
	}
	private void input() {
		gameLogic.input();
	}
	private void update() {
		gameLogic.update();
	}
	private void cleanup() {
		window.cleanup();
		gameLogic.cleanup();
		errorCallback.free();
		GLFW.glfwTerminate();
	}
	private void render() {
		gameLogic.render();
		window.update();
	}
}
