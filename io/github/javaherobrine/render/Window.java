package io.github.javaherobrine.render;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL45.*;
import org.lwjgl.opengl.GL;
import xueli.game2.lifecycle.*;
import java.util.*;
public class Window implements LifeCycle {
	public final long window;
	public final ArrayList<KeyBinding> bindings = new ArrayList<>();
	public Window() {
		glfwInit();
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		window = glfwCreateWindow(1920, 1080, "CraftGame", 0, 0);
		glfwSetWindowSizeCallback(window, (win, width, height) -> {
			glViewport(0, 0, width, height);
		});
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
	}
	@Override
	public void init() {
		glViewport(0, 0, 1920, 1080);
	}
	@Override
	public void tick() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}
	@Override
	public void release() {
		glfwDestroyWindow(window);
		glfwTerminate();
	}
	public void input() {
		Iterator<KeyBinding> iter = bindings.iterator();
		while (iter.hasNext()) {
			KeyBinding binding = iter.next();
			if (glfwGetKey(window, binding.key()) == (binding.click() ? GLFW_PRESS : GLFW_RELEASE)) {
				binding.callback().run();
			}
		}
	}
}
