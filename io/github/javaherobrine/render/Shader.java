package io.github.javaherobrine.render;
import org.lwjgl.opengl.*;
public class Shader {
    public final int program,vertex,fragment;
    public Shader(String vertexCode,String fragmentCode) {
		System.err.println("vs: "+vertexCode);
		System.err.println("fs: "+fragmentCode);
		program=GL45.glCreateProgram();
		vertex=GL45.glCreateShader(GL45.GL_VERTEX_SHADER);
		fragment=GL45.glCreateShader(GL45.GL_FRAGMENT_SHADER);
		GL45.glShaderSource(fragment, fragmentCode);
		GL45.glShaderSource(vertex, vertexCode);
		GL45.glCompileShader(vertex);
		System.err.println(GL45.glGetShaderInfoLog(vertex));
		GL45.glCompileShader(fragment);
		System.err.println(GL45.glGetShaderInfoLog(fragment));
		GL45.glAttachShader(program,vertex);
		GL45.glAttachShader(program, fragment);
		GL45.glLinkProgram(program);
		System.err.println(GL45.glGetProgramInfoLog(program));
		GL45.glDeleteShader(fragment);
		GL45.glDeleteShader(vertex);
    }
    public void exec() {
		GL45.glUseProgram(program);
    }
}
