package io.github.javaherobrine.render;
import org.joml.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
public class WindowManager {
	public static final float FOV=60;
	public static final float Z_NEAR=0.01f;
	public static final float Z_FAR=1000f;
	private final String title;
	public WindowManager(String title, int width, int height, boolean vSync) {
		super();
		this.title = title;
		this.width = width;
		this.height = height;
		this.vSync = vSync;
		projectionMatrix=new Matrix4f();
	}
	private int width,height;
	private long window;
	private boolean resize,vSync;
	private final Matrix4f projectionMatrix;
	public void init() {
		GLFWErrorCallback.createPrint(System.err);
		if(!GLFW.glfwInit()) {
			throw new IllegalStateException("GLFW isn't initialized");
		}
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_VERSION_MINOR, 2);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
		boolean maximised=false;
		if(width==0||height==0) {
			width=1920;
			height=1080;
			GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);
			maximised=true;
		}
		window=GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
		if(window==MemoryUtil.NULL) {
			throw new Error("Failed to create window");
		}
		GLFW.glfwSetFramebufferSizeCallback(window, (window,width,height)->{
			this.width=width;
			this.height=height;
			this.resize=true;
		});
		GLFW.glfwSetKeyCallback(window, (window,key,scancode,action,mods)->{
			if(key==GLFW.GLFW_KEY_ENTER&&action==GLFW.GLFW_PRESS) {
				GLFW.glfwSetWindowShouldClose(window,true);
			}
		});
		if(maximised) {
			GLFW.glfwMaximizeWindow(window);
		}else {
			GLFWVidMode vidMode=GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
			GLFW.glfwSetWindowPos(window, (vidMode.width()-width)/2, (vidMode.height()-height)/2);
		}
		GLFW.glfwMakeContextCurrent(window);
		if(vSync) {
			GLFW.glfwSwapInterval(1);
		}
		GLFW.glfwShowWindow(window);
		GL.createCapabilities();
		GL11.glClearColor(0, 0, 127, 0);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_STENCIL_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	public void update() {
		GLFW.glfwSwapBuffers(window);
		GLFW.glfwPollEvents();
	}
	public void cleanup() {
		GLFW.glfwDestroyWindow(window);
	}
	public void setClearColor(float r,float g,float b,float alpha) {
		GL11.glClearColor(r, g, b, alpha);
	}
	public boolean isKeyPressed(int keycode) {
		return GLFW.glfwGetKey(window, keycode)==GLFW.GLFW_PRESS;
	}
	public boolean windowShouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}
	public void setTitle(String title) {
		GLFW.glfwSetWindowTitle(window, title);
	}
	public String getTitle() {
		return title;
	}
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	public Matrix4f updateProjectionMatrix() {
		float aspectRatio=width/height;
		return projectionMatrix.setPerspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
	}
	public Matrix4f updateProjectionMatrix(int width,int height,Matrix4f matrix) {
		float aspectRatio=width/height;
		return matrix.setPerspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
	}
}
