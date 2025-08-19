package io.github.javaherobrine.render;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import xueli.game2.lifecycle.*;
import java.util.*;
public class Window implements LifeCycle{
    public final long window;
    public final ArrayList<KeyBinding> bindings=new ArrayList<>();
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
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();
    }
    	   @Override
    public void init() {
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
    public void input() {
		Iterator<KeyBinding> iter=bindings.iterator();
		while(iter.hasNext()) {
		    KeyBinding binding=iter.next();
		    if(GLFW.glfwGetKey(window,binding.key())==(binding.click()?GLFW.GLFW_PRESS:GLFW.GLFW_RELEASE)){
				binding.callback().run();
		    }
		}
    }
}
