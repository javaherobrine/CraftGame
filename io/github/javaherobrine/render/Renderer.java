package io.github.javaherobrine.render;
import xueli.game2.lifecycle.*;
import java.io.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL45.*;
import xueli.utils.io.*;
public class Renderer implements RunnableLifeCycle{
    private Window win;
    private long frame=-1;
    private Shader shader;
    private Texture text;
    private VAO vao;
    public Renderer(Window window) {
		win=window;
		vao=new VAO(new float[] {
	    -0.5f,-0.5f,0,1,0,0,1,1,
	    -0.5f,0.5f,0,0,0,1,0,1,
	    0.5f,-0.5f,0,0,1,0,1,0,
		 0.5f,0.5f,0,1,0,0,0,0
		},8);
		vao.bindVBO(GL_STATIC_DRAW);
	   vao.bindIBO(new int[] {
	    0,1,3,
	    0,2,3
      }, GL_STATIC_DRAW);
		vao.attribute(0,3);
		vao.attribute(1,3,3);
		vao.attribute(2,2,6);
    }
           @Override
    public void init() {
       try {
	   	text=new Texture(new float[] {
	   		0,0,
	   		0,1,
	   		1,0,
	   		1,1},Files.getResourcePackedInJarStream("/textures/grassblock.png").readAllBytes());
	    	shader=new Shader(new String(Files.getResourcePackedInJarStream("/shaders/vertex.vs").readAllBytes()),
	    	    new String(Files.getResourcePackedInJarStream("/shaders/fragment.fs").readAllBytes()));
	    	glUniform1i(glGetAttribLocation(shader.program, "tex"), GL_TEXTURE0);
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
		glClear(GL_COLOR_BUFFER_BIT);
		vao.apply();
		text.activate();
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
	   return !glfwWindowShouldClose(win.window);
	 }
}
