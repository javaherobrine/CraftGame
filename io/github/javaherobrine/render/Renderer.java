package io.github.javaherobrine.render;
import xueli.game2.lifecycle.*;
import java.io.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL45;

import xueli.utils.io.*;
public class Renderer implements RunnableLifeCycle{
    private Window win;
    private long frame=-1;
    private Shader shader;
    private VAO vao;
    public Renderer(Window window) {
		win=window;
		vao=new VAO(new float[] {
	    -0.5f,-0.5f,0,
	    -0.5f,0.5f,0,
	    0.5f,-0.5f,0,
		 0.5f,0.5f,0
		},3);
		vao.bindVBO(GL45.GL_STATIC_DRAW);
	   vao.bindIBO(new int[] {
	    0,1,3,
	    1,2,3
      }, GL45.GL_STATIC_DRAW);
		vao.attribute(0);
    }
           @Override
    public void init() {
       try {
	    	shader=new Shader(new String(Files.getResourcePackedInJarStream("/shaders/vertex.vs").readAllBytes()),
	    	    new String(Files.getResourcePackedInJarStream("/shaders/fragment.fs").readAllBytes()));
	 	 } catch (IOException e) {
	    // TODO Auto-generated catch block
	 	     e.printStackTrace();
	 	}
      win.init();
      frame=System.currentTimeMillis();
    }
    	   @Override
    public void tick() {//In something about rendering, this is called "Render" ---LovelyZeeiam
    	//compute delta time
    	long current=System.currentTimeMillis();
    	long deltaTime=current-frame;
    	frame=current;
    	//process input
		win.input();
		//render
		GL45.glClear(GL45.GL_COLOR_BUFFER_BIT);
		vao.apply();
		shader.exec();
		//process events and swap buffers
		win.tick();
    }
           @Override
    public void release() {
		win.release();
    }
	   @Override
	 public boolean isRunning() {
	   return !GLFW.glfwWindowShouldClose(win.window);
	 }
}
