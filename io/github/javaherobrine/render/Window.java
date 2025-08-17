package io.github.javaherobrine.render;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import xueli.game2.lifecycle.*;
public class Window implements LifeCycle{
    public final long window;
    public Window() {
    	GLFW.glfwInit();
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 5);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
		window=GLFW.glfwCreateWindow(1920, 1080, "CraftGame", 0, 0);
		GLFW.glfwSetWindowSizeCallback(window,(win,width,height)->{
		    GL45.glViewport(0, 0, width, height);
		});
    }
    	   @Override
    public void init() {
    	GLFW.glfwMakeContextCurrent(window);
    	GL45.glViewport(0, 0, 1920, 1080);
    }
    	   @Override
    public void tick() {
		GLFW.glfwSwapBuffers(window);
		GLFW.glfwPollEvents();
    }
    	   @Override
    public void release() {
    	GLFW.glfwDestroyWindow(window);
    	GLFW.glfwTerminate();
    }
}
